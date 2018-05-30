package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.frontend.model.files.FSServiceImpl;
import xyz.shuttle.filebox.frontend.model.auth.AuthenticationService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@SpringView(name = "user_view")
public class UserView extends VerticalLayout implements View {

    @Autowired
    private FSServiceImpl fsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private FilterComponent filterComponent;

    @Autowired
    private MyPopup myPopup;

    private Grid<File> gridFiles = new Grid<>();

    private StringBuilder sbName = new StringBuilder();

    private HorizontalLayout gridLayout = new HorizontalLayout();
    private VerticalLayout uploadLayout = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();
    private HorizontalLayout filterLayout = new HorizontalLayout();
    private HorizontalLayout shareLayout = new HorizontalLayout();

    private Button btnDelete = new Button("Delete");
    private Button btnDownload = new Button("Download");
    private Button btnShare = new Button("Share");
    private Button btnLogout = new Button("Logout");

    private Label shareLabel = new Label();

    private PopupView popupView;

    private Upload uploadFile = new Upload("Upload", fsService);
    private FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        gridFiles.addColumn(File::getName).setCaption("File");
        gridFiles.addColumn(File::length).setCaption("Size, b");
        gridFiles.setSizeFull();
        gridFiles.setItems(fsService.getFileList());

        uploadFile.setImmediateMode(false);
        uploadFile.setReceiver(fsService);
        uploadFile.addSucceededListener(fsService);
        uploadFile.addFinishedListener(finishedEvent -> gridFiles.setItems(fsService.getFileList()));
        uploadFile.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        btnDelete.addClickListener(clickEvent -> {
            try {
                sbName.setLength(0);
                sbName.append(
                        gridFiles
                                .getSelectedItems()
                                .iterator()
                                .next()
                                .getName());
                try {
                    gridFiles.deselectAll();
                    Files.delete(Paths.get(fsService.getFileByName(sbName.toString()).toURI()));
                    gridFiles.setItems(fsService.getFileList());
                    Notification.show("File deleted!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchElementException e) {
                Notification.show("Select a file!", Notification.Type.ERROR_MESSAGE);
            }
        });

        popupView = new PopupView(null, myPopup);

        btnShare.addClickListener(clickEvent -> {
            try {
                myPopup.popup(
                        getUI().getPage().getLocation(),
                        gridFiles
                                .getSelectedItems()
                                .iterator()
                                .next());

                popupView.setPopupVisible(true);

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
                            new File(fsService.getFileByName(fileName).toURI()));
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

        uploadLayout.addComponents(uploadFile);
        gridLayout.addComponent(gridFiles);
        btnLayout.addComponents(btnDelete, btnDownload, btnShare, btnLogout);

        //
        shareLayout.addComponents(shareLabel);
        shareLayout.setComponentAlignment(shareLabel, Alignment.MIDDLE_CENTER);
        //

        //
        filterLayout.addComponents(filterComponent);
        filterComponent.apply(gridFiles);
        //

        uploadLayout.setComponentAlignment(uploadFile, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDelete, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDownload, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnShare, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnLogout, Alignment.MIDDLE_CENTER);

        this.addComponents(popupView, uploadLayout, gridLayout, btnLayout, shareLayout, filterLayout);
        this.setComponentAlignment(popupView, Alignment.TOP_CENTER);
    }
}