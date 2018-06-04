package xyz.shuttle.filebox.basis.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import sun.misc.BASE64Decoder;
import xyz.shuttle.filebox.basis.model.share.ShareServiceImpl;

import java.io.IOException;

@SpringView(name = "share")
@Scope(value = "request")
public class ShareView extends VerticalLayout implements View {

    @Autowired
    ShareServiceImpl shareService;

    private String[] array;
    private Label label = new Label();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            array = new String(
                    new BASE64Decoder().decodeBuffer(
                            getUI()
                                    .getPage()
                                    .getLocation()
                                    .getPath()
                                    .replaceAll("/share/", "")))
                    .split(":");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (array.length == 8)
            label.setValue(array[5]);

        this.addComponent(label);
        this.setComponentAlignment(label, Alignment.TOP_CENTER);
    }
}
