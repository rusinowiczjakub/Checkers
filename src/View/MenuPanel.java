package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.AnimatedText;


public class MenuPanel extends VBox {

    // Start screen
    private Text multiplayerButton;
    private Text singleplayerButton;
    private Text exitButton;

    //Gamesettings screen
    private HBox wrapper;
    private TextField player1Name;
    private TextField player2Name;
    private HBox comboWrapper;
    private Spinner<Integer> moveTimeLimit;
    private Text startGame;


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

        //Start screen
        multiplayerButton = new AnimatedText("Multiplayer Mode");
        singleplayerButton = new AnimatedText("Singleplayer Mode");
        exitButton = new AnimatedText("Exit");
        getChildren().addAll(multiplayerButton, singleplayerButton, exitButton);

        wrapper = new HBox();
        wrapper.setSpacing(20);

        comboWrapper = new HBox();
        comboWrapper.setSpacing(20);
        comboWrapper.setAlignment(Pos.CENTER);

        //Settings screen
        //Player1 input
        Label player1NameLabel = new Label("Gracz 1");
        player1Name = new TextField();

        //Player2 input
        Label player2NameLabel = new Label("Gracz 2");
        player2Name = new TextField();

        //Combobox
        Label comboBoxLabel = new Label("Czas na ruch");
        moveTimeLimit = new Spinner<>();

        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 1000, 10);

        moveTimeLimit.setValueFactory(valueFactory);

        //Start game button
        startGame = new AnimatedText("Rozpocznij");

        //Fillin wrappers
        wrapper.getChildren().addAll(player1NameLabel, player1Name, player2NameLabel, player2Name);
        comboWrapper.getChildren().addAll(comboBoxLabel, moveTimeLimit);
    }

    public void gameSettingsView() {
        getChildren().removeAll(multiplayerButton, singleplayerButton, exitButton);
        getChildren().addAll(wrapper, comboWrapper, startGame);
    }

    public Text getMultiplayerButton() {
        return multiplayerButton;
    }

    public Text getSingleplayerButton() {
        return singleplayerButton;
    }

    public Text getExitButton() {
        return exitButton;
    }

    public TextField getPlayer1NameInput() {
        return player1Name;
    }

    public String getPlayer1Name() {
        return player1Name.getText();
    }

    public String getPlayer2Name() {
        return player2Name.getText();
    }

    public TextField getPlayer2NameInput() {
        return player2Name;
    }

    public Spinner<Integer> getMoveTimeLimit() {
        return moveTimeLimit;
    }

    public Text getStartGame() {
        return startGame;
    }
}
