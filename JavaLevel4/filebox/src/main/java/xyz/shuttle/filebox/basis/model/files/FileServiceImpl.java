package xyz.shuttle.filebox.basis.model.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.basis.dao.FileDao;
import xyz.shuttle.filebox.basis.domain.UserFiles;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileDao fileDao;

    public void save(String username, String filename) {
        if (!findFileByName(username, filename)) {
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

    public boolean findFileByName(String username, String fileName){
        return fileDao.findFileByName(username, fileName).isPresent();
    }
}
