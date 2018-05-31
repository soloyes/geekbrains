package xyz.shuttle.filebox.frontend.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FSDao {

    @Value("${filePath}")
    private String filePath;

    public String getUserDirectory() {
        StringBuilder userPath = new StringBuilder();
        userPath.append(filePath)
                .append(File.separator)
                .append(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
                )
                .append(File.separator);
        File newFile = new File(userPath.toString());
        newFile.mkdirs();
        return newFile.toString();
    }

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
}
