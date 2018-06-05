package xyz.shuttle.filebox.basis.model.files;

import com.vaadin.ui.TextField;
import lombok.NonNull;

import java.io.*;
import java.util.List;
import java.util.Set;

public interface FSService {
    OutputStream getFileOutputStream(String filename) throws IOException;

    File getFileByName(String filename);

    File getFileByNameAndUsername(String username, String filename);

    List<File> getFileList(String username);

    Set<File> filterList(List<TextField> textFields);

    void initUserDirectory(String username) throws IOException;

    void delete(String filename) throws IOException;
}
