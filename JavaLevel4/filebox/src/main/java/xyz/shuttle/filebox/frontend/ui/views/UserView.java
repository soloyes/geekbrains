package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.frontend.model.FileServiceImpl;
import xyz.shuttle.filebox.frontend.services.AuthenticationService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@SpringView(name = "user_view")
public class UserView extends VerticalLayout implements View {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FilterComponent filterComponent;

    private Grid<File> gridFiles = new Grid<>();

    private StringBuilder deleteName = new StringBuilder();

    private HorizontalLayout gridLayout = new HorizontalLayout();
    private VerticalLayout uploadLayout = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();
    private HorizontalLayout filterLayout = new HorizontalLayout();

    private Button btnDownload = new Button("Download");
    private Button btnLogout = new Button("Logout");
    private Button btnDelete = new Button("Delete");

    private Upload uploadFile = new Upload("Upload", fileService);
    private FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        gridFiles.addColumn(File::getName).setCaption("File");
        gridFiles.addColumn(File::length).setCaption("Size, b");
        gridFiles.setSizeFull();
        gridFiles.setItems(fileService.getFileList());

        uploadFile.setImmediateMode(false);
        uploadFile.setReceiver(fileService);
        uploadFile.addSucceededListener(fileService);
        uploadFile.addFinishedListener(finishedEvent -> gridFiles.setItems(fileService.getFileList()));
        uploadFile.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        btnDelete.addClickListener(clickEvent -> {
            try {
                deleteName.setLength(0);
                deleteName.append(
                        gridFiles
                                .getSelectedItems()
                                .iterator()
                                .next()
                                .getName());
                try {
                    gridFiles.deselectAll();
                    Files.delete(Paths.get(fileService.getFileByName(deleteName.toString()).toURI()));
                    gridFiles.setItems(fileService.getFileList());
                    Notification.show("File deleted!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchElementException e) {
                Notification.show("Select a file!", Notification.Type.ERROR_MESSAGE);
            }
        });

        btnLogout.addClickListener((Button.ClickListener) clickEvent -> {
            authenticationService.logout();
            getUI().getPage().setLocation("/login");
        });

        gridFiles.addItemClickListener(itemClick -> {
            String fileName = itemClick.getItem().getName();
            StreamResource resource = new StreamResource((StreamResource.StreamSource) () -> {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(
                            new File(fileService.getFileByName(fileName).toURI()));
                } catch (IOException e) {
                    Notification.show("Select a file!", Notification.Type.WARNING_MESSAGE);
                    e.printStackTrace();
                }
                return fileInputStream;
            }, fileName);
            fileDownloader.setFileDownloadResource(resource);
            fileDownloader.extend(btnDownload);
        });

        gridFiles.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        gridLayout.setSizeFull();
        btnLayout.setSizeFull();
        filterLayout.setSizeFull();

        gridLayout.addComponent(gridFiles);
        uploadLayout.addComponents(uploadFile);
        btnLayout.addComponents(btnDelete, btnDownload, btnLogout);

        //
        filterLayout.addComponents(filterComponent);
        filterComponent.apply(gridFiles);
        //

        uploadLayout.setComponentAlignment(uploadFile, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDelete, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDownload, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnLogout, Alignment.MIDDLE_CENTER);

        this.addComponents(uploadLayout, gridLayout, btnLayout, filterLayout);
    }
}