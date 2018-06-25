package sample;

public enum PawnDefinition {
    BLACK(1), WHITE(-1);

    final int movementDirection;

    PawnDefinition(int movementDirection) {
        this.movementDirection = movementDirection;
    }
}
