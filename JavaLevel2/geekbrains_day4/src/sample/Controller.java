package sample;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Controller {

    static Properties properties = new Properties();

    String[] s = getClass().getResource("Properties.properties").toString().split(":");
    static {
        String s1 = new Controller().s[1];
        try(BufferedReader reader = new BufferedReader(new FileReader(s1))){
            properties.load(reader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static int getIntProperty(String property, int defaultValue){
        try{
            return Integer.parseInt(properties.getProperty(property, String.valueOf(defaultValue)));
        }
        catch (NumberFormatException e){
            return defaultValue;
        }

    }

    void commit(){
        Stage stage = Main.getPrimaryStage();

        TextArea outArea = (TextArea) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        TextArea inArea = (TextArea) stage.getScene().getRoot().getChildrenUnmodifiable().get(1);

        if (!inArea.getText().isEmpty()) {
            outArea.appendText(inArea.getText() + System.lineSeparator());
            inArea.clear();
        }
    }
}
