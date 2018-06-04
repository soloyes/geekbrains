package xyz.shuttle.filebox.basis.ui.components;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import xyz.shuttle.filebox.basis.model.share.ShareService;
import xyz.shuttle.filebox.basis.model.share.ShareServiceImpl;
import xyz.shuttle.filebox.basis.model.user.UserService;

import java.io.File;
import java.net.URI;
import java.util.Date;

@Component
public class SharePopupComponent extends VerticalLayout {
    @Autowired
    private UserService userService;

    @Autowired
    private ShareServiceImpl shareService;

    private URI uri;
    private File file;

    private TextField textField = new TextField();
    private Label shareLabel = new Label();
    private String link;

    private Button linkBtn = new Button("Link", clickEvent -> {
        makeLink();
        showLink();
    });

    public SharePopupComponent() {
        this.addComponents(textField, linkBtn, shareLabel);
        this.setComponentAlignment(linkBtn, Alignment.TOP_CENTER);
        this.setComponentAlignment(textField, Alignment.TOP_CENTER);
        this.setComponentAlignment(shareLabel, Alignment.TOP_CENTER);

        this.setWidth(1024, Unit.PIXELS);
    }

    private void makeLink() {
        link = String.join("",
                uri.getScheme(),
                "://",
                uri.getAuthority(),
                "/share/",
                new BCryptPasswordEncoder()
                        .encode(
                                String.join("",
                                        file.getName(),
                                        ":user:",
                                        textField.getValue(),
                                        ":timestamp:",
                                        new Date().toString())
                        )
        );
    }

    private void showLink() {
        String userFrom = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!textField.getValue().isEmpty() &&
                !textField.getValue().equals("admin") &&
                !textField.getValue().equals(userFrom)) {
            try {
                userService.loadUserByUsername(textField.getValue());
                shareLabel.setValue(link);
                shareService.save(userFrom, textField.getValue(), file.getName());
            } catch (UsernameNotFoundException e) {
                shareLabel.setValue("User not found!");
            }
        } else
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
