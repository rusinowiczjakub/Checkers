package sample;

import View.MenuPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckersApp extends Application {

    private Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new MenuPanel());
        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
