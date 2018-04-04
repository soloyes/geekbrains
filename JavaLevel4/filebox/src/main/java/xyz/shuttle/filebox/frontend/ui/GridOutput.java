package xyz.shuttle.filebox.frontend.ui;

import com.vaadin.ui.Grid;
import org.springframework.stereotype.Component;
import xyz.shuttle.filebox.frontend.model.SavedFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class GridOutput {
    private final String filePath = "/home/sol/storage/";

    private Grid<SavedFiles> gridFiles = new Grid<>();

    public GridOutput(){
        gridFiles.addColumn(SavedFiles::getFilename).setCaption("Name");
        gridFiles.addColumn(SavedFiles::getFileSize).setCaption("Size");
        gridFiles.setSizeFull();
        calcGrid();
    }

    public void calcGrid(){
        File[] filesArray = new File(filePath).listFiles();
        List<SavedFiles> fileList = new ArrayList<>();
        for (File f : filesArray) {
            fileList.add(new SavedFiles(f.getName(), f.length() / 1024 + "Kb"));
        }
        gridFiles.setItems(fileList);
    }

    public Grid<SavedFiles> getGridFiles() {
        return gridFiles;
    }
}
