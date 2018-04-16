package xyz.shuttle.filebox.frontend.model;

import com.vaadin.ui.TextField;

import java.io.*;
import java.util.List;

public interface FileService {
    OutputStream getFileOutputStream(String fileName) throws IOException;

    File getFileByName(String fileName);

    List<File> getFileList();

    List<File> filterList(List<TextField> textFields);
}
