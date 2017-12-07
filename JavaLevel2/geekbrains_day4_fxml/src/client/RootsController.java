package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.HashMap;

public class RootsController {

    static Scene mainScene;
    
    private static HashMap<String, Parent> scenes = new HashMap<>();

    public static HashMap<String, Parent> getScenes() {
        return scenes;
    }

    void addRoot(String name, String location){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scenes.put(name, root);
    }
}
