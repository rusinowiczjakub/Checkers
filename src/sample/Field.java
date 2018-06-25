package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends Rectangle {

    protected Pawn pawn;

    public Field(boolean white, int x, int y) {
        setWidth(CheckersApp.FIELD_SIZE);
        setHeight(CheckersApp.FIELD_SIZE);

        relocate(x * CheckersApp.FIELD_SIZE, y * CheckersApp.FIELD_SIZE);

        setFill(white ? Color.LIGHTGOLDENRODYELLOW : Color.DARKGREEN);

//        pawn.setOnMouseClicked(e -> {
//
//        });
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
