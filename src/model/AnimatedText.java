package model;

import javafx.animation.ScaleTransition;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.concurrent.*;

public class AnimatedText extends Text {

    public AnimatedText(String text) {
        setText(text);
        setStyle("-fx-font-size: 20px");


        setOnMouseEntered(e -> {
            ScaleTransition transition = new ScaleTransition(Duration.millis(200), (Text)e.getTarget());
            transition.setFromX(1);
            transition.setToX(1.5);
            transition.setFromY(1);
            transition.setToY(1.5);
            transition.setAutoReverse(false);
            transition.play();

        });

        setOnMouseExited(e -> {
            ScaleTransition transition = new ScaleTransition(Duration.millis(200), (Text)e.getTarget());
            transition.setFromX(1.5);
            transition.setToX(1);
            transition.setFromY(1.5);
            transition.setToY(1);
            transition.setAutoReverse(false);
            transition.play();
        });
    }
}
