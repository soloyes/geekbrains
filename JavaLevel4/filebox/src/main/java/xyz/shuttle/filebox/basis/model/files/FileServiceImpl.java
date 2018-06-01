package xyz.shuttle.filebox.basis.model.files;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.basis.dao.FileDao;
import xyz.shuttle.filebox.basis.domain.UserFiles;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileDao fileDao;

    public void save(String username, String filename) {
        if (!fileDao.findFileByName(username, filename).isPresent()) {
            fileDao.save(
                    UserFiles.builder()
                            .username(username)
                            .filename(filename)
                            .build()
            );
        }
    }

    public void delete(String username, String filename){
        fileDao.delete(username, filename);
    }

    public boolean findFileByName(@NonNull String username, @NonNull String fileName){
        return fileDao.findFileByName(username, fileName).isPresent();
    }
}
