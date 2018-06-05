package xyz.shuttle.filebox.basis.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.shuttle.filebox.basis.model.files.FSServiceImpl;
import xyz.shuttle.filebox.basis.model.share.ShareServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@SpringView(name = "share")
@Scope(value = "request")
public class ShareView extends VerticalLayout implements View {

    @Autowired
    ShareServiceImpl shareService;

    @Autowired
    FSServiceImpl fsService;

    private String[] array;
    private Link link = new Link();

    private FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        array = new String(
                Base64.getDecoder().decode(
                        getUI()
                                .getPage()
                                .getLocation()
                                .getPath()
                                .replaceAll("/share/", "")))
                .split("/");

        //:todo Поменять на что-то нормальное
        if (array.length == 6) {
            String userTo = SecurityContextHolder.getContext().getAuthentication().getName();
            if (array[3].equals(userTo)) {
                if (shareService.findShare(array[1], array[3], array[5])) {
                    link.setCaption(array[5]);

                    StreamResource resource = new StreamResource((StreamResource.StreamSource) () -> {
                        FileInputStream fileInputStream = null;
                        try {
                            fileInputStream = new FileInputStream(
                                    new File(fsService.getFileByNameAndUsername(array[1], array[5]).toURI()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return fileInputStream;
                    }, array[5]);

                    fileDownloader.setFileDownloadResource(resource);
                    fileDownloader.extend(link);
                }
            }
        }

        this.addComponent(link);
        this.setComponentAlignment(link, Alignment.TOP_CENTER);
    }
}
