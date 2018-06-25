package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import model.GameBoard;
import model.Player;

public class Controller {

    public Parent createGame() {
        Player player1 = new Player(true);
        Player player2 = new Player(false);

        return new GameBoard(player1, player2).drawBoard();
    }

    public void menuAction() {

    }
}
