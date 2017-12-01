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

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Client view");
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setMinHeight(Controller.getIntProperty("setMinHeight", 150));
        primaryStage.setMinWidth(Controller.getIntProperty("setMinWidth", 300));

        TextArea outArea = new TextArea();
        outArea.setId("outArea");
        outArea.setEditable(false);
        VBox.setVgrow(outArea, Priority.ALWAYS);
        HBox.setHgrow(outArea, Priority.ALWAYS);

        TextArea inArea = new TextArea();
        inArea.setId("inArea");
        inArea.promptTextProperty().setValue("Input message...");
        HBox.setHgrow(inArea, Priority.ALWAYS);
        inArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER))
                    Controller.commit(primaryStage);
            }
        });

        Button commitButton = new Button("Send");
        HBox.setHgrow(commitButton, Priority.ALWAYS);
        commitButton.setId("commitButton");
        commitButton.setMaxWidth(Double.MAX_VALUE);
        commitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inArea.appendText("\n");
                Controller.commit(primaryStage);
            }
        });

        VBox mainVBox = new VBox();
        mainVBox.setId("mainVBox");
        mainVBox.getChildren().addAll(outArea, inArea, commitButton);

        //setUserAgentStylesheet(STYLESHEET_MODENA);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        Scene scene = new Scene(
                mainVBox,
                Controller.getIntProperty("setWidth", 800),
                Controller.getIntProperty("setHeight", 600));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
