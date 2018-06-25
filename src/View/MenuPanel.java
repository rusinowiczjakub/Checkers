package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.AnimatedText;


public class MenuPanel extends VBox {

    private Text multiplayerButton;
    private Text singleplayerButton;
    private Text exitButton;

    public MenuPanel() {
        setPrefSize(800, 800);
        setSpacing(30);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(100));
        setStyle(
                "-fx-background-image: url(" + "/resources/background.jpg" + "" +"); " +
                "-fx-background-position: center; -fx-background-repeat: no-repeat; " +
                "-fx-background-size: cover"
        );

        multiplayerButton = new AnimatedText("Multiplayer Mode");

        singleplayerButton = new AnimatedText("Singleplayer Mode");

        exitButton = new AnimatedText("Exit");


        getChildren().addAll(multiplayerButton, singleplayerButton, exitButton);

    }
}
