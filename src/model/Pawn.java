package model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import sample.CheckersApp;

public class Pawn extends StackPane {

    private PawnDefinition type;
    private double mouseX;
    private double mouseY;
    private double initialX;
    private double initialY;
    private Player player;

    public Pawn(PawnDefinition type, int x, int y) {
        this.type = type;

        movePawn(x, y);

        Ellipse bg = new Ellipse(GameBoard.FIELD_SIZE * 0.3125, GameBoard.FIELD_SIZE * 0.26);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(GameBoard.FIELD_SIZE * 0.03);
        bg.setTranslateX((GameBoard.FIELD_SIZE - GameBoard.FIELD_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((GameBoard.FIELD_SIZE - GameBoard.FIELD_SIZE * 0.26 * 2) / 2 + GameBoard.FIELD_SIZE * 0.07);

        Ellipse pawn = new Ellipse(GameBoard.FIELD_SIZE * 0.3125, GameBoard.FIELD_SIZE * 0.26);
        pawn.setFill(type == PawnDefinition.BLACK ? Color.DIMGREY : Color.WHITE);
        pawn.setStroke(Color.BLACK);
        pawn.setStrokeWidth(GameBoard.FIELD_SIZE * 0.03);
        pawn.setTranslateX((GameBoard.FIELD_SIZE - GameBoard.FIELD_SIZE * 0.3125 * 2) / 2);
        pawn.setTranslateY((GameBoard.FIELD_SIZE - GameBoard.FIELD_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, pawn);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + initialX, e.getSceneY() - mouseY + initialY);
        });

    }

    public PawnDefinition getType() {
        return type;
    }

    public void movePawn(int x, int y) {
        initialX = x * GameBoard.FIELD_SIZE;
        initialY = y * GameBoard.FIELD_SIZE;
        relocate(initialX, initialY);
    }

    public double getInitialX() {
        return initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public void preventMove() {
        relocate(initialX, initialY);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
