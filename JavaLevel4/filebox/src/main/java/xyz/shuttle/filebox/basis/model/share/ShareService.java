package xyz.shuttle.filebox.basis.model.share;

public interface ShareService {
    void save(String userFrom, String userTo, String filename);

    void delete(String userFrom, String userTo, String filename);

    boolean findShare(String userFrom, String userTo, String filename);

    void deleteByFile(String username, String userFrom);
}
