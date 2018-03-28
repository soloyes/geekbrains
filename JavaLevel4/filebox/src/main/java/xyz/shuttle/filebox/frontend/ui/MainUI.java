package xyz.shuttle.filebox.frontend.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.backend.model.SavedFile;
import xyz.shuttle.filebox.backend.service.AuthenticationService;

@SpringUI
public class MainUI extends UI {
    @Autowired
    AuthenticationService authenticationService;

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();

        Grid<SavedFile> gridFiles = new Grid<>();
        gridFiles.setSizeFull();

        Panel pnlAuthenticate = new Panel();
        pnlAuthenticate.setSizeUndefined();
        Panel pnlActions = new Panel();
        pnlActions.setSizeUndefined();

        Upload uploadFile = new Upload();
        uploadFile.setButtonCaption("Upload");
        Button btnDelete = new Button("Delete");
        Button btnDownload = new Button("Download");

        TextField loginField = new TextField("Login:");
        PasswordField passwordField = new PasswordField("Password:");
        Label loginLabel = new Label();
        Button btnLogin = new Button("Login", clickEvent -> {
            if (authenticationService.login(loginField.getValue(), passwordField.getValue())) {
                loginLabel.setValue("Login success!");
            }
            else {
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

        layoutSource.addComponents(gridFiles, pnlAuthenticate, pnlActions);
        this.setContent(layoutSource);
    }
}
