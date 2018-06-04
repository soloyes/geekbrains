package xyz.shuttle.filebox.basis.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.shuttle.filebox.basis.model.files.FSServiceImpl;
import xyz.shuttle.filebox.basis.model.auth.AuthenticationService;
import xyz.shuttle.filebox.basis.model.files.FileServiceImpl;
import xyz.shuttle.filebox.basis.model.share.ShareServiceImpl;
import xyz.shuttle.filebox.basis.ui.components.FilterComponent;
import xyz.shuttle.filebox.basis.ui.components.SharePopupComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

@SpringView(name = "user_view")
@Scope(value = "request")
public class UserView extends VerticalLayout implements View {

    @Autowired
    private FSServiceImpl fsService;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    ShareServiceImpl shareService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private FilterComponent filterComponent;

    @Autowired
    private SharePopupComponent sharePopupComponent;

    private Grid<File> gridFiles = new Grid<>();

    private HorizontalLayout gridLayout = new HorizontalLayout();
    private VerticalLayout uploadLayout = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();
    private HorizontalLayout filterLayout = new HorizontalLayout();
    private HorizontalLayout shareLayout = new HorizontalLayout();

    private Button btnDelete = new Button("Delete");
    private Button btnDownload = new Button("Download");
    private Button btnShare = new Button("Share");
    private Button btnLogout = new Button("Logout");

    private Label shareLabel = new Label();

    private PopupView sharePopup;

    private Upload uploadFile = new Upload("Upload", fsService);
    private FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        gridFiles.addColumn(File::getName).setCaption("File");
        gridFiles.addColumn(File::length).setCaption("Size, b");
        gridFiles.setSizeFull();
        gridFiles.setItems(fsService.getFileList(username));

        uploadFile.setImmediateMode(false);
        uploadFile.setReceiver(fsService);
        uploadFile.addSucceededListener(fsService);
        uploadFile.addFinishedListener(finishedEvent -> gridFiles.setItems(fsService.getFileList(username)));
        uploadFile.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        btnDelete.addClickListener(clickEvent -> {
            try {
                String filename = gridFiles
                        .getSelectedItems()
                        .iterator()
                        .next()
                        .getName();
                try {
                    gridFiles.deselectAll();
                    fsService.delete(filename);
                    fileService.delete(
                            username,
                            filename
                    );
                    shareService.deleteByFile(username, filename);
                    gridFiles.setItems(fsService.getFileList(username));
                    Notification.show("File deleted!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchElementException e) {
                Notification.show("Select a file!");
            }
        });

        sharePopup = new PopupView(null, sharePopupComponent);

        btnShare.addClickListener(clickEvent -> {
            try {
                sharePopupComponent.popup(
                        getUI().getPage().getLocation(),
                        gridFiles
                                .getSelectedItems()
                                .iterator()
                                .next()
                );

                sharePopup.setPopupVisible(true);

            } catch (NoSuchElementException e) {
                Notification.show("Select a file!");
            }
        });

        btnLogout.addClickListener((Button.ClickListener) clickEvent -> {
            authenticationService.logout();
            getUI().getPage().setLocation("/login");
        });

        gridFiles.addItemClickListener(itemClick -> {
            String fileName = itemClick.getItem().getName();
            StreamResource resource = new StreamResource((StreamResource.StreamSource) () -> {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(
                            new File(fsService.getFileByName(fileName).toURI()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fileInputStream;
            }, fileName);

            fileDownloader.setFileDownloadResource(resource);
            fileDownloader.extend(btnDownload);
        });

        gridFiles.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        gridLayout.setSizeFull();
        btnLayout.setSizeFull();
        filterLayout.setSizeFull();

        uploadLayout.addComponents(uploadFile);
        gridLayout.addComponent(gridFiles);
        btnLayout.addComponents(btnDelete, btnDownload, btnShare, btnLogout);

        //
        shareLayout.addComponents(shareLabel);
        shareLayout.setComponentAlignment(shareLabel, Alignment.MIDDLE_CENTER);
        //

        //
        filterLayout.addComponents(filterComponent);
        filterComponent.apply(gridFiles);
        //

        uploadLayout.setComponentAlignment(uploadFile, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDelete, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDownload, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnShare, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnLogout, Alignment.MIDDLE_CENTER);

        this.addComponents(sharePopup, uploadLayout, gridLayout, btnLayout, shareLayout, filterLayout);
        this.setComponentAlignment(sharePopup, Alignment.TOP_CENTER);
    }
}