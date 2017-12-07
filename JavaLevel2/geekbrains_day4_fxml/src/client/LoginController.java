package client;

import javafx.animation.FadeTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class LoginController {
    public void commitButton(MouseEvent mouseEvent) {

        ((Pane) RootsController.mainScene.getRoot()).getChildren().removeAll();
        RootsController.mainScene.setRoot(RootsController.getScenes().get("client"));

        FadeTransition fi = new FadeTransition(
                Duration.millis(1500),
                RootsController.mainScene.getRoot());

        fi.setFromValue(0.0);
        fi.setToValue(1.0);
        fi.play();
    }
}
