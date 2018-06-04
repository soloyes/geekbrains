package xyz.shuttle.filebox.basis.model.share;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.basis.dao.ShareDao;
import xyz.shuttle.filebox.basis.domain.UserShare;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    ShareDao shareDao;

    public void save(String userFrom, String userTo, String filename) {
        if (!findShare(userFrom, userTo, filename)) {
            shareDao.save(
                    UserShare.builder()
                            .userFrom(userFrom)
                            .userTo(userTo)
                            .filename(filename)
                            .build()
            );
        }
    }

    public void delete(String userFrom, String userTo, String filename) {
        shareDao.delete(userFrom, userTo, filename);
    }

    public boolean findShare(String userFrom, String userTo, String filename){
        return shareDao.findShare(userFrom, userTo, filename).isPresent();
    }

    public void deleteByFile(String userFrom, String filename) {
        shareDao.delete(userFrom, filename);
    }
}
