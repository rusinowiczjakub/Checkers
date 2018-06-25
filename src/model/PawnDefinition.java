package model;

public enum PawnDefinition {
    BLACK(1), WHITE(-1);

    public final int movementDirection;

    PawnDefinition(int movementDirection) {
        this.movementDirection = movementDirection;
    }
}
