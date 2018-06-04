package xyz.shuttle.filebox.basis.model.files;

public interface FileService {
    void save(String username, String filename);

    void delete(String username, String filename);

    boolean findFileByName(String username, String fileName);
}
