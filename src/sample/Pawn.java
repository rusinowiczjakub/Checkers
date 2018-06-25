package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Pawn extends StackPane {

    private PawnDefinition type;
    private double mouseX;
    private double mouseY;
    private double initialX;
    private double initialY;

    public Pawn(PawnDefinition type, int x, int y) {
        this.type = type;

        movePawn(x, y);

        Ellipse bg = new Ellipse(CheckersApp.FIELD_SIZE * 0.3125, CheckersApp.FIELD_SIZE * 0.26);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(CheckersApp.FIELD_SIZE * 0.03);
        bg.setTranslateX((CheckersApp.FIELD_SIZE - CheckersApp.FIELD_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((CheckersApp.FIELD_SIZE - CheckersApp.FIELD_SIZE * 0.26 * 2) / 2 + CheckersApp.FIELD_SIZE * 0.07);

        Ellipse pawn = new Ellipse(CheckersApp.FIELD_SIZE * 0.3125, CheckersApp.FIELD_SIZE * 0.26);
        pawn.setFill(type == PawnDefinition.BLACK ? Color.DARKRED : Color.WHITE);
        pawn.setStroke(Color.BLACK);
        pawn.setStrokeWidth(CheckersApp.FIELD_SIZE * 0.03);
        pawn.setTranslateX((CheckersApp.FIELD_SIZE - CheckersApp.FIELD_SIZE * 0.3125 * 2) / 2);
        pawn.setTranslateY((CheckersApp.FIELD_SIZE - CheckersApp.FIELD_SIZE * 0.26 * 2) / 2);

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
        initialX = x * CheckersApp.FIELD_SIZE;
        initialY = y * CheckersApp.FIELD_SIZE;
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
}
