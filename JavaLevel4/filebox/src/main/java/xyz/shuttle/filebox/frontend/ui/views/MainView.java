package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import xyz.shuttle.filebox.frontend.services.SaveFileService;
import xyz.shuttle.filebox.frontend.services.auth.AuthenticationService;
import xyz.shuttle.filebox.frontend.ui.GridOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@ViewScope
@SpringView(name = "main")
public class MainView extends VerticalLayout implements View {

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    SpringNavigator navigator;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    GridOutput gridFiles;

    private StringBuilder deleteName = new StringBuilder();

    private HorizontalLayout gridLayout = new HorizontalLayout();
    private VerticalLayout uploadLayout = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();
    private HorizontalLayout filterLayout = new HorizontalLayout();

    private Button btnDownload = new Button("Download");
    private Button btnLogout = new Button("Logout");
    private Button btnDelete = new Button("Delete");
    private Button btnFilter = new Button("Filter");

    private Upload uploadFile = new Upload("Upload", saveFileService);
    private FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        uploadFile.setImmediateMode(false);
        uploadFile.setReceiver(saveFileService);
        uploadFile.addSucceededListener(saveFileService);
        uploadFile.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        btnDelete.addClickListener(clickEvent -> {
            try {
                deleteName.setLength(0);
                deleteName.append(
                        gridFiles
                                .getGridFiles()
                                .getSelectedItems()
                                .iterator()
                                .next()
                                .getFilename());
                try {
                    Files.delete(Paths.get(filePath + deleteName));
                    gridFiles.calcGrid();
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
            navigator.navigateTo("login");
        });

        gridFiles.getGridFiles().addItemClickListener(itemClick -> {
            String fileName = itemClick.getItem().getFilename();
            StreamResource resource = new StreamResource((StreamResource.StreamSource) () -> {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(
                            new File(filePath + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fileInputStream;
            }, fileName);
            fileDownloader.setFileDownloadResource(resource);
            fileDownloader.extend(btnDownload);
        });

        gridFiles.getGridFiles().setErrorHandler((ErrorHandler) errorEvent -> {
        });

        gridLayout.setSizeFull();
        btnLayout.setSizeFull();
        filterLayout.setSizeFull();

        gridLayout.addComponent(gridFiles.getGridFiles());
        uploadLayout.addComponents(uploadFile);
        btnLayout.addComponents(btnDelete, btnDownload, btnLogout);

        //
        FilterComponent filterComponent = new FilterComponent();
        filterLayout.addComponents(filterComponent);
        //

        uploadLayout.setComponentAlignment(uploadFile, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDelete, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDownload, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnLogout, Alignment.MIDDLE_CENTER);

        this.addComponents(uploadLayout, gridLayout, btnLayout, filterLayout);
    }
}