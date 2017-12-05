package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientController implements Initializable{

    @FXML
    TextArea outArea;
    @FXML
    TextArea inArea;
    @FXML
    Button commitButton;
    @FXML
    ListView<String> currentUsers = new ListView<>();


    ObservableList<String> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList.add(this.userName);
        currentUsers.setItems(observableList);
    }

    private String userName = "User";

    public String getUserName() {
        return userName;
    }

    private Pattern pattern = Pattern.compile("\n*");

    private static Properties properties = new Properties();

    static {
        try{
            properties.load(ClientController.class.getResourceAsStream("view/Properties.properties"));
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
    public void commitField(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode().equals(KeyCode.ENTER)){
            commit();
        }
    }

    public void commitButton(MouseEvent mouseEvent) {
       commit();
       inArea.requestFocus();
    }

    private void commit(){
        Matcher matcher = pattern.matcher(inArea.getText());
        if (!matcher.matches()){
            outArea.appendText(userName);
            outArea.appendText(": ");
            outArea.appendText(inArea.getText());
            outArea.appendText("\n");
            inArea.clear();
            observableList.add("Maybe other User");
        }
    }


}
