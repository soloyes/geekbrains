package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.frontend.services.SaveFileService;
import xyz.shuttle.filebox.frontend.ui.GridOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@SpringView(name = "main")
public class MainView extends VerticalLayout implements View {
    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    GridOutput gridFiles;

    private StringBuilder deleteName = new StringBuilder();

    private final String filePath = "/home/sol/storage/";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        HorizontalLayout gridLayout = new HorizontalLayout();
        VerticalLayout uploadLayout = new VerticalLayout();
        HorizontalLayout btnLayout = new HorizontalLayout();

        Upload uploadFile = new Upload("Upload", saveFileService);
        uploadFile.setImmediateMode(false);
        uploadFile.setReceiver(saveFileService);
        uploadFile.addSucceededListener(saveFileService);
        uploadFile.setErrorHandler((ErrorHandler) errorEvent -> {
        });

        Button btnDelete = new Button("Delete", (Button.ClickListener) clickEvent -> {
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

        FileDownloader fileDownloader = new FileDownloader((Resource) () -> null);

        Button btnDownload = new Button("Download");

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

        gridLayout.setSizeFull();
        btnLayout.setSizeFull();
        gridLayout.addComponent(gridFiles.getGridFiles());
        uploadLayout.addComponents(uploadFile);
        btnLayout.addComponents(btnDelete, btnDownload);

        uploadLayout.setComponentAlignment(uploadFile, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDelete, Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(btnDownload, Alignment.MIDDLE_CENTER);
        this.addComponents(uploadLayout, gridLayout, btnLayout);
    }
}
