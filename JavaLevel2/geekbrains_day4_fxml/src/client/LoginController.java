package client;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LoginController {
    public void commitButton(MouseEvent mouseEvent) {

        FadeTransition fo = new FadeTransition(
                Duration.millis(
                        GetProperty.getInt("fadeOut", 400)),
                RootsController.mainScene.getRoot()
        );
        fo.setFromValue(1.0);
        fo.setToValue(0.0);
        fo.play();
        fo.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RootsController.mainScene.setRoot(RootsController.getScenes().get("client"));

                FadeTransition fi = new FadeTransition(
                        Duration.millis(
                                GetProperty.getInt("fadeIn", 1500)),
                        RootsController.mainScene.getRoot());
                fi.setFromValue(0.0);
                fi.setToValue(1.0);
                fi.setDelay(Duration.millis(2000));
                fi.play();
            }
        });
    }
}
