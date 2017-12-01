package sample;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class Controller {

    private static Properties properties = new Properties();

    static {
        try{
            properties.load(Controller.class.getResourceAsStream("Properties.properties"));
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
