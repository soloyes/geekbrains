package xyz.shuttle.filebox.frontend.model;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FileServiceImpl implements FileService, Upload.Receiver, Upload.SucceededListener {

    @Value("${filePath}")
    private String filePath;

    @Autowired
    FileServiceImpl fileService;

    @Override
    public List<File> getFileList() {
        List<File> fileList = new ArrayList<>();
        try {
            File folder = new File(getUserDirectory());
            return Arrays.stream(folder.listFiles())
                    .filter(x -> !x.isDirectory())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }

    private String getUserDirectory() {
        String userPath =
                filePath                            // директория общая
                        + File.separator            // разделитель для ОС
                        + SecurityContextHolder     // Контекст секюрити
                        .getContext()               // Получаем контекст
                        .getAuthentication()        // Получаем авторизацию текущего пользователя
                        .getName()                  // Имя текущего пользователя
                        + File.separator;           // разделитель для ОС
        File userFiles = new File(userPath);
        if (!userFiles.exists())
            userFiles.mkdirs();
        return userPath;
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
            return fileService.getFileOutputStream(filename);
        } catch (IOException e) {
            Notification.show("Upload failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {

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
