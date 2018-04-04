package xyz.shuttle.filebox.frontend.services;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.frontend.model.SaveFileServiceImpl;
import xyz.shuttle.filebox.frontend.ui.GridOutput;

import java.io.*;

@Service
public class SaveFileService implements Upload.Receiver, Upload.SucceededListener {
    @Autowired
    SaveFileServiceImpl fileService;

    @Autowired
    GridOutput gridFiles;

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            Notification.show("Upload start!");
            return fileService.getFileOutputStream(filename);
        } catch (IOException e) {
            Notification.show("Upload failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        gridFiles.calcGrid();
    }
}
