package xyz.shuttle.filebox.basis.ui.components;

import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import xyz.shuttle.filebox.basis.model.share.ShareServiceImpl;
import xyz.shuttle.filebox.basis.model.user.UserService;

import java.io.File;
import java.net.URI;
import java.util.Base64;

@Component
public class SharePopupComponent extends VerticalLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private ShareServiceImpl shareService;

    private URI uri;
    private File file;

    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private TextField textField = new TextField();
    private TextArea shareArea = new TextArea();

    private String link;

    private Button linkBtn = new Button("Link", clickEvent -> {
        makeLink();
        showLink();
    });

    private Button unLinkBtn = new Button("Unlink", clickEvent -> shareService.delete(
            SecurityContextHolder.getContext().getAuthentication().getName(),
            textField.getValue(),
            file.getName()
    ));

    public SharePopupComponent() {
        horizontalLayout.addComponents(linkBtn, unLinkBtn);
        horizontalLayout.setComponentAlignment(linkBtn, Alignment.TOP_LEFT);
        horizontalLayout.setComponentAlignment(unLinkBtn, Alignment.TOP_RIGHT);

        this.addComponents(textField, horizontalLayout, shareArea);
        this.setComponentAlignment(textField, Alignment.TOP_CENTER);
        this.setComponentAlignment(shareArea, Alignment.TOP_CENTER);
        this.setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);

        shareArea.setReadOnly(true);
        shareArea.setWordWrap(true);
        shareArea.setRows(5);
    }

    private void makeLink() {
        link = String.join("",
                uri.getScheme(),
                "://",
                uri.getAuthority(),
                "/share/",
                new String(Base64.getEncoder().encode(
                        String.join("",
                                "uf/",
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getName(),
                                "/ut/",
                                textField.getValue(),
                                "/fn/",
                                file.getName()
                        ).getBytes())
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
                shareArea.setValue(link);
                shareService.save(userFrom, textField.getValue(), file.getName());
            } catch (UsernameNotFoundException e) {
                shareArea.setValue("User not found!");
            }
        } else
            clearLink();
    }

    private void clearLink() {
        shareArea.setValue("");
        textField.setValue("");
    }

    public void popup(URI uri, File file) {
        clearLink();
        this.uri = uri;
        this.file = file;
    }
}