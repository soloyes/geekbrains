package xyz.shuttle.filebox.basis.model.files;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.basis.dao.FSDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FSServiceImpl implements FSService, Upload.Receiver, Upload.SucceededListener {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private FSDao fsDao;

    @Override
    public List<File> getFileList(String username) {
        return fsDao.getFileList(username);
    }

    @Override
    public File getFileByName(String name) {
        return fsDao.getFileByName(name);
    }

    @Override
    public OutputStream getFileOutputStream(String name) throws IOException {
        return new FileOutputStream(getFileByName(name));
    }

    @Override
    public OutputStream receiveUpload(String name, String mimeType) {
        try {
            return this.getFileOutputStream(name);
        } catch (IOException e) {
            Notification.show("Upload failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        Notification.show("Upload succeed!");
        fileService.save(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                succeededEvent.getFilename()
        );
    }

    @Override
    public Set<File> filterList(List<TextField> textFields) {
        Set<File> set = new HashSet<>();
        textFields.forEach(textField ->
                set.addAll(getFileList(SecurityContextHolder.getContext().getAuthentication().getName())
                        .stream()
                        .filter(file -> file.getName()
                                .contains(textField.getValue()))
                        .collect(Collectors.toList())));
        return set;
    }

    @Override
    public void initUserDirectory(String username) throws IOException {
        fsDao.initUserDirectory(username);
    }

    @Override
    public void delete(String name) throws IOException {
        fsDao.delete(name);
    }
}
