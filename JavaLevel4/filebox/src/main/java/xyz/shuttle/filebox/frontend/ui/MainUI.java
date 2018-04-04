package xyz.shuttle.filebox.frontend.ui;

import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.shuttle.filebox.frontend.model.SaveFile;
import xyz.shuttle.filebox.frontend.services.auth.AuthenticationService;
import xyz.shuttle.filebox.frontend.services.SaveFileService;

import javax.validation.Path;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SpringUI
public class MainUI extends UI {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    GridOutput gridFiles;

    private StringBuilder deleteName = new StringBuilder();
    private final String filePath = "/home/sol/storage/";

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();

        Panel pnlAuthenticate = new Panel();
        pnlAuthenticate.setSizeUndefined();
        Panel pnlActions = new Panel();
        pnlActions.setSizeUndefined();
        pnlActions.setVisible(false);

        Upload uploadFile = new Upload();
        uploadFile.setButtonCaption("Upload");
        uploadFile.setImmediateMode(false);
        uploadFile.setReceiver(saveFileService);
        uploadFile.addSucceededListener(saveFileService);
        uploadFile.setErrorHandler(new ErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent errorEvent) {
            }
        });

        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
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
                }
                catch (NoSuchElementException e){
                    Notification.show("Select a file!");
                }
            }
        });

        Button btnDownload = new Button("Download");

        FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);
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

        gridFiles.getGridFiles().setErrorHandler(new ErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent errorEvent) {

            }
        });


        TextField loginField = new TextField("Login:");
        PasswordField passwordField = new PasswordField("Password:");
        Label loginLabel = new Label();
        Button btnLogin = new Button("Login", clickEvent -> {
            if (authenticationService.login(loginField.getValue(), passwordField.getValue())) {
                pnlAuthenticate.setVisible(false);
                pnlActions.setVisible(true);
            } else {
                loginLabel.setValue("Login failure!");
            }
        });


        HorizontalLayout layoutActions = new HorizontalLayout();
        layoutActions.setSizeUndefined();
        VerticalLayout loginLayout = new VerticalLayout();
        HorizontalLayout loginBtnLayout = new HorizontalLayout();
        loginBtnLayout.addComponents(btnLogin, loginLabel);

        loginLayout.addComponents(loginField, passwordField, loginBtnLayout);

        pnlActions.setContent(layoutActions);
        layoutActions.addComponents(uploadFile, btnDelete, btnDownload);
        pnlAuthenticate.setContent(loginLayout);

        layoutSource.addComponents(gridFiles.getGridFiles(), pnlAuthenticate, pnlActions);
        this.setContent(layoutSource);
    }
}
