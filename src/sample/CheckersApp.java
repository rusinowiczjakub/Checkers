package sample;

import View.MenuPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;

public class CheckersApp extends Application {

    private MenuPanel menu = new MenuPanel();
    private Controller controller = new Controller(menu);

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(menu);
        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        menu.getStartGame().setOnMouseClicked(e -> {
            controller.setPlayer1(new Player(true, menu.getPlayer1Name()));
            controller.setPlayer2(new Player(false, menu.getPlayer2Name()));
            primaryStage.close();

            Stage gameStage = new Stage();
            Scene scene1 = new Scene(controller.startMultiplayerGame());
            gameStage.setTitle("Checkers - multiplayer mode");
            gameStage.setScene(scene1);
            gameStage.show();
            gameStage.setResizable(false);
        });

        controller.initListeners();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
