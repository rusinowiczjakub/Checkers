package sample;

import View.MenuPanel;
import javafx.scene.Parent;
import model.GameBoard;
import model.Player;

public class Controller {

    private MenuPanel menu;
    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    public Controller(MenuPanel menu) {
        this.menu = menu;
    }

    public void initListeners() {
        multiPlayerSettingsScreen();
    }

    public void multiPlayerSettingsScreen() {
        menu.getMultiplayerButton().setOnMouseClicked(e -> {
            menu.gameSettingsView();

        });
    }

    public Parent startMultiplayerGame() {
        this.gameBoard = new GameBoard(player1, player2);
        this.gameBoard.setInterval(menu.getMoveTimeLimit().getValue());
        return gameBoard.drawBoard();
    }

    public void menuAction() {

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
