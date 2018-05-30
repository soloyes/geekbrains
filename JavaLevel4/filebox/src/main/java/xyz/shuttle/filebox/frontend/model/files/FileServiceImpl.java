package xyz.shuttle.filebox.frontend.model.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.frontend.dao.FileDao;
import xyz.shuttle.filebox.frontend.domain.UserFiles;

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
}
