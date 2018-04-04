package xyz.shuttle.filebox.frontend.model;

public class SavedFiles {
    private String filename;
    private String fileSize;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public SavedFiles(String filename, String fileSize) {
        this.filename = filename;
        this.fileSize = fileSize;
    }
}
