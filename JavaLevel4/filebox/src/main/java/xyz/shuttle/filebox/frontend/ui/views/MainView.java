package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xyz.shuttle.filebox.frontend.services.SaveFileService;
import xyz.shuttle.filebox.frontend.services.auth.AuthenticationService;
import xyz.shuttle.filebox.frontend.ui.GridOutput;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

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

    private HorizontalLayout gridLayout;
    private VerticalLayout uploadLayout;
    private HorizontalLayout btnLayout;

    private Button btnDownload;
    private Button btnLogout;
    private Button btnDelete;

    private Upload uploadFile;
    private FileDownloader fileDownloader;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        gridLayout = new HorizontalLayout();
        uploadLayout = new VerticalLayout();
        btnLayout = new HorizontalLayout();

        btnDownload = new Button("Download");
        btnLogout = new Button("Logout");
        btnDelete = new Button("Delete");

        uploadFile = new Upload("Upload", saveFileService);
        fileDownloader = new FileDownloader((Resource) () -> null);

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
        gridLayout.addComponent(gridFiles.getGridFiles());
        uploadLayout.addComponents(uploadFile);
        btnLayout.addComponents(btnDelete, btnDownload, btnLogout);

        uploadLayout.setComponentAlignment(uploadFile, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDelete, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDownload, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnLogout, Alignment.MIDDLE_CENTER);
        this.addComponents(uploadLayout, gridLayout, btnLayout);
    }
}