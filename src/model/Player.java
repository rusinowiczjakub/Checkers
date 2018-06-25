package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Pawn> pawns;
    private boolean isBot;
    private boolean hasMove;

    public Player(Boolean hasMove) {
        this.hasMove = hasMove;
        pawns = new ArrayList<>();
    }

    public void addPawn(Pawn pawn) {
        pawns.add(pawn);
    }

    public boolean hasMove() {
        return hasMove;
    }

    public void setHasMove(boolean hasMove) {
        this.hasMove = hasMove;
    }
}
