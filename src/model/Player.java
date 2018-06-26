package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Pawn> pawns;
    private boolean isBot;
    private boolean hasMove;
    private String nickname;

    public Player(Boolean hasMove, String nickname) {
        this.hasMove = hasMove;
        pawns = new ArrayList<>();
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void removePawn(Pawn pawn) {
        pawns.remove(pawn);
    }

    public List<Pawn> getPawns() {
        return pawns;
    }
}
