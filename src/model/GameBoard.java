package model;

import sample.Field;

public class GameBoard {

    private Field[][] fields;

    public GameBoard(int sizeX, int sizeY) {
        fields = new Field[sizeX][sizeY];
    }

    public Field[][] getFields() {
        return fields;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    public Field getFieldByIndex(int x, int y) {
        return fields[x][y];
    }

    public void setFieldAtIntex(int x, int y, Field field) {
        fields[x][y] = field;
    }
}
