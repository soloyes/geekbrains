package xyz.shuttle.filebox.frontend.model.files;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.frontend.dao.FSDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FSServiceImpl implements FSService, Upload.Receiver, Upload.SucceededListener {

    @Autowired
    FSServiceImpl fsService;

    @Autowired
    FileServiceImpl fileService;

    @Autowired
    FSDao fsDao;

    @Override
    public List<File> getFileList() {
        return fsDao.getFileList();
    }

    private String getUserDirectory() {
        return fsDao.getUserDirectory();
    }

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        return new FileOutputStream(getFileByName(fileName));
    }

    @Override
    public File getFileByName(String fileName) {
        return new File(getUserDirectory() + fileName);
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            Notification.show("Upload start!");
            return fsService.getFileOutputStream(filename);
        } catch (IOException e) {
            Notification.show("Upload failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        fileService.save(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                succeededEvent.getFilename()
        );
    }

    @Override
    public Set<File> filterList(List<TextField> textFields) {
        Set<File> set = new HashSet<>();
        textFields.forEach(textField ->
                set.addAll(getFileList()
                        .stream()
                        .filter(file -> file.getName()
                                .contains(textField.getValue()))
                        .collect(Collectors.toList())));
        return set;
    }
}
