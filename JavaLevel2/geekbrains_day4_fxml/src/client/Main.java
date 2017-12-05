package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/client.fxml"));
        primaryStage.setTitle("Client of " + new ClientController().getUserName());

        primaryStage.setMinWidth(ClientController.getIntProperty("setMinWidth", 400));
        primaryStage.setMinHeight(ClientController.getIntProperty("setMinHeight", 300));

        Scene scene = new Scene(root,
                ClientController.getIntProperty("setWidth",500),
                ClientController.getIntProperty("setHeight", 600)
                );

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
