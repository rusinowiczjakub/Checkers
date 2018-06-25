package model;

public class Move {

    private MoveDefinition moveType;
    private Pawn pawn;

    public Move(MoveDefinition moveType, Pawn pawn) {
        this.moveType = moveType;
        this.pawn = pawn;
    }

    public Move(MoveDefinition moveType) {
        this(moveType, null);
    }

    public Pawn getPawn() {
        return pawn;
    }

    public MoveDefinition getMoveType() {
        return moveType;
    }
}
