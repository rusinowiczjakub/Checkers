package model;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GamePanel extends HBox {

    Text player1;
    Text player2;
    Text player1BeatenPawns;
    Text player2BeatenPawns;
    Text timer;

    GamePanel(Player player1, Player player2) {
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();

        timer = new Text();

        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(30);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(30);
        player1BeatenPawns = new Text("Pozostało pionków: 12");
        player1BeatenPawns.setFont(new Font(25));
        player2BeatenPawns = new Text("Pozostało pionków: 12");
        player2BeatenPawns.setFont(new Font(25));
        this.player1 = new Text(player1.getNickname().toUpperCase());
        this.player2 = new Text(player2.getNickname().toUpperCase());
        this.player1.setFill(Color.GREY);
        this.player2.setFill(Color.GREY);
        this.player1.setFont(new Font(25));
        this.player2.setFont(new Font(25));


        if (player1.hasMove()){
            this.player1.setFill(Color.GREEN);
            this.player1.setUnderline(true);
        }
        if (player2.hasMove()){
            this.player2.setFill(Color.GREEN);
            this.player2.setUnderline(true);
        }
        setSpacing(40);
        setAlignment(Pos.BOTTOM_CENTER);

        timer.setFont(new Font(50));
        timer.setFill(Color.RED);
        vbox1.getChildren().addAll(this.player1, player1BeatenPawns);
        vbox2.getChildren().addAll(this.player2, player2BeatenPawns);
        getChildren().addAll(vbox1, timer, vbox2);
    }

    public Text getPlayer1() {
        return player1;
    }

    public Text getPlayer2() {
        return player2;
    }

    public void setPlayer1BeatenPawns(String player1BeatenPawns) {
        this.player1BeatenPawns.setText(player1BeatenPawns);
    }

    public void setPlayer2BeatenPawns(String player2BeatenPawns) {
        this.player2BeatenPawns.setText(player2BeatenPawns);
    }

    public Text getTimer() {
        return timer;
    }
}
