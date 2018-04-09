package xyz.shuttle.filebox.frontend.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class SaveFileServiceImpl implements SaveFile {

    @Value("${filePath}")
    private String filePath;

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        return new FileOutputStream(getFileByName(fileName));
    }

    @Override
    public File getFileByName(String fileName) {
        return new File(filePath + fileName);
    }
}
