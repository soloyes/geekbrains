package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.shuttle.filebox.frontend.model.user.UserService;

import java.io.File;
import java.net.URI;
import java.util.Date;

@org.springframework.stereotype.Component
@Scope("prototype")
public class MyPopup extends VerticalLayout {
    @Autowired
    private UserService userService;

    private URI uri;
    private File file;

    private TextField textField = new TextField();
    private Label shareLabel = new Label();
    private StringBuilder link = new StringBuilder();

    private Button linkBtn = new Button("Link", clickEvent -> {
        makeLink();
        showLink();
    });

    public MyPopup() {
        this.addComponents(textField, linkBtn, shareLabel);
        this.setComponentAlignment(linkBtn, Alignment.TOP_CENTER);
        this.setComponentAlignment(textField, Alignment.TOP_CENTER);
        this.setComponentAlignment(shareLabel, Alignment.TOP_CENTER);

        this.setWidth(1024, Unit.PIXELS);
    }

    private void makeLink() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(uri.getScheme())
                .append("://")
                .append(uri.getAuthority())
                .append("/share/")
                .append(
                        new BCryptPasswordEncoder().encode(
                                file.getName() +
                                        ":user:" +
                                        textField.getValue() +
                                        ":timestamp:" +
                                        new Date()
                        )
                );
        link.setLength(0);
        link.append(stringBuilder);
    }

    private void showLink() {
        if (
                !textField.getValue()
                        .isEmpty() &&
                !textField.getValue()
                        .equals("admin") &&
                !textField.getValue()
                        .equals(
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getName()
                        )
                ) {
            shareLabel.setValue(link.toString());
            try {
                userService.loadUserByUsername(textField.getValue());
            } catch (UsernameNotFoundException e) {
                shareLabel.setValue("User not found!");
            }
        }
        else
            clearLink();
    }

    private void clearLink() {
        shareLabel.setValue("");
        textField.setValue("");
    }

    public void popup(URI uri, File file) {
        clearLink();
        this.uri = uri;
        this.file = file;
    }
}
