package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Hello World");
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setMinHeight(Controller.getIntProperty("setMinHeight", 150));
        primaryStage.setMinWidth(Controller.getIntProperty("setMinWidth", 300));

        TextArea outArea = new TextArea();
        TextArea inArea = new TextArea();
        inArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER))
                    new Controller().commit();
            }
        });

        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Controller().commit();
            }
        });

        inArea.promptTextProperty().setValue("Input message...");
        VBox.setVgrow(outArea, Priority.ALWAYS);
        HBox.setHgrow(outArea, Priority.ALWAYS);
        HBox.setHgrow(inArea, Priority.ALWAYS);
        HBox.setHgrow(sendButton, Priority.ALWAYS);

        VBox vBox = new VBox();
        vBox.setId("vBox");
        vBox.getChildren().addAll(outArea, inArea, sendButton);

        setUserAgentStylesheet(STYLESHEET_MODENA);
        //setUserAgentStylesheet(STYLESHEET_CASPIAN);

        Scene scene = new Scene(
                vBox,
                Controller.getIntProperty("setWidth", 800),
                Controller.getIntProperty("setHeight", 600));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
