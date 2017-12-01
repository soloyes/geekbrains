package sample;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Controller {

    private static Properties properties = new Properties();

    private static String fileName = Controller.class.getResource("Properties.properties").getFile();

    static {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
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

    static void commit(Stage primaryStage){
        TextArea inArea = (TextArea) primaryStage.getScene().lookup("#inArea");

        TextArea outArea = (TextArea) primaryStage.getScene().lookup("#outArea");

        if (inArea == null) throw new CommitMessageException("inArea value seems null");

        if (outArea == null) throw new CommitMessageException("outArea value seems null");

        StringBuilder inAreaString = new StringBuilder(inArea.getText());
        //Delete ending "\n" because no need to input only "\n" without any text
        inAreaString.deleteCharAt(inAreaString.length() - 1);

        if (inAreaString.length() != 0)
            outArea.appendText(inAreaString.append("\n").toString());
        inArea.clear();
    }
}
