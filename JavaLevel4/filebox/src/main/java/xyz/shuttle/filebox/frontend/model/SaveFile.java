package xyz.shuttle.filebox.frontend.model;

import java.io.*;

public interface SaveFile {
    OutputStream getFileOutputStream(String fileName) throws IOException;

    File getFileByName(String fileName);
}
