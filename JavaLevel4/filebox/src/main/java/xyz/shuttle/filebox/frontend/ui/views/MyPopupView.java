package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.ui.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URI;
import java.util.Date;

public class MyPopupView extends VerticalLayout {
    private URI uri;
    private File file;

    private TextField textField = new TextField();
    private Label shareLabel = new Label();
    private StringBuilder link = new StringBuilder();
    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    private Button linkBtn = new Button("Link", clickEvent -> {
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
        shareLabel.setValue(link.toString());
    });

    private Button shareBtn = new Button("Share");

    public MyPopupView() {
        horizontalLayout.addComponents(linkBtn, shareBtn);
        this.addComponents(textField, horizontalLayout, shareLabel);
        this.setWidth(1024, Unit.PIXELS);
    }

    public void makeLink(URI uri, File file) {
        this.uri = uri;
        this.file = file;
    }
}
