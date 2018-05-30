package xyz.shuttle.filebox.frontend.model.files;

import com.vaadin.ui.TextField;

import java.io.*;
import java.util.List;
import java.util.Set;

public interface FSService {
    OutputStream getFileOutputStream(String fileName) throws IOException;

    File getFileByName(String fileName);

    List<File> getFileList();

    Set<File> filterList(List<TextField> textFields);
}
