package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        RootsController roots = new RootsController();

        roots.addRoot("client",
                "view/client.fxml");

        roots.addRoot("login",
                "view/login.fxml");

        Scene scene = new Scene(RootsController.getScenes().get("login"),
                ClientController.getIntProperty("setWidth", 500),
                ClientController.getIntProperty("setHeight", 600));

        RootsController.mainScene = scene;

        primaryStage.setTitle("Client of " + new ClientController().getUserName());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}