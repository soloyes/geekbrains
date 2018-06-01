package xyz.shuttle.filebox.basis.model.files;

import com.vaadin.ui.TextField;

import java.io.*;
import java.util.List;
import java.util.Set;

public interface FSService {
    OutputStream getFileOutputStream(String fileName) throws IOException;

    File getFileByName(String fileName);

    List<File> getFileList(String username);

    Set<File> filterList(List<TextField> textFields);

    void initUserDirectory(String username) throws IOException;

    void delete(String name) throws IOException;
}
