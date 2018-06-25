package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends Rectangle {

    protected Pawn pawn;

    public Field(boolean white, int x, int y) {
        setWidth(GameBoard.FIELD_SIZE);
        setHeight(GameBoard.FIELD_SIZE);

        relocate(x * GameBoard.FIELD_SIZE, y * GameBoard.FIELD_SIZE);

        setFill(white ? Color.LIGHTGOLDENRODYELLOW : Color.DARKGREEN);

    }

    public boolean hasPawn() {
        return !(pawn == null);
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }
}
