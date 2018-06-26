package model;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;


public class GameBoard {

    static final int FIELD_SIZE = 100;
    static final int WIDTH = 8;
    static final int HEIGHT = 8;

    private Group fieldsGroup = new Group();
    private Group pawns = new Group();
    private Field[][] fields;
    private Player player1, player2;
    private GamePanel gamePanel;
    private int interval;

    public GameBoard(Player player1, Player player2) {
        fields = new Field[WIDTH][HEIGHT];
        this.player1 = player1;
        this.player2 = player2;

        gamePanel = new GamePanel(player1, player2);
    }

    public Parent drawBoard() {
        VBox wrapper = new VBox();
        wrapper.setPrefSize(WIDTH * FIELD_SIZE, HEIGHT * FIELD_SIZE + 100);

        Pane root = new Pane();

        root.setPrefSize(WIDTH * FIELD_SIZE, HEIGHT * FIELD_SIZE);
        root.getChildren().addAll(fieldsGroup, pawns);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Field field = new Field((i + j) % 2 == 0, j, i);

                setFieldAtIntex(j, i, field);

                fieldsGroup.getChildren().add(field);

                Pawn pawn = null;

                if (i <= 2 && (i + j) % 2 != 0) {
                    pawn = makePawn(PawnDefinition.BLACK, j, i);
                    player2.addPawn(pawn);
                    pawn.setPlayer(player2);
                }

                if (i >= 5 && (i + j) % 2 != 0) {
                    pawn = makePawn(PawnDefinition.WHITE, j, i);
                    player1.addPawn(pawn);
                    pawn.setPlayer(player1);
                }

                if (pawn != null) {
                    field.setPawn(pawn);
                    pawns.getChildren().add(pawn);
                }
            }
        }

        wrapper.getChildren().addAll(root, gamePanel);

        setTimer();

        return wrapper;
    }

    private Pawn makePawn(PawnDefinition type, int x, int y) {
        Pawn pawn = new Pawn(type, x, y);

        pawn.setOnMouseReleased(e -> {
            int newX = toBoard(pawn.getLayoutX());
            int newY = toBoard(pawn.getLayoutY());

            Move result = null;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new Move(MoveDefinition.NONE);
            } else {
                result = doMovement(pawn, newX, newY);
            }


            int x0 = toBoard(pawn.getInitialX());
            int y0 = toBoard(pawn.getInitialY());

            switch (result.getMoveType()) {
                case NONE:
                    pawn.preventMove();
                    break;

                case BEAT:
                    pawn.movePawn(newX, newY);
                    getFieldByIndex(x0, y0).setPawn(null);
                    getFieldByIndex(newX, newY).setPawn(pawn);

                    Pawn beatenPawn = result.getPawn();
                    getFieldByIndex((toBoard(beatenPawn.getInitialX())), toBoard(beatenPawn.getInitialY())).setPawn(null);
                    pawns.getChildren().remove(beatenPawn);

                    beatenPawn.getPlayer().removePawn(beatenPawn);
                    Player nextPlayer = getNextPlayer();

                    if (nextPlayer.getNickname().toUpperCase().equals(gamePanel.player1.getText())) {
                        gamePanel.setPlayer1BeatenPawns("Pozostało pionków: " + String.valueOf(player1.getPawns().size()));
                    }

                    if (nextPlayer.getNickname().toUpperCase().equals(gamePanel.player2.getText())){
                        gamePanel.setPlayer2BeatenPawns("Pozostało pionków: " + String.valueOf(player2.getPawns().size()));
                    }

                    setTimer();
                    break;

                case NORMAL:
                    pawn.movePawn(newX, newY);
                    getFieldByIndex(x0, y0).setPawn(null);
                    getFieldByIndex(newX, newY).setPawn(pawn);
                    getNextPlayer();

                    setTimer();
                    break;
            }
        });

        return pawn;
    }

    private Move doMovement(Pawn pawn, int destX, int destY) {
        if (getFieldByIndex(destX, destY).hasPawn() || (destX + destY) % 2 == 0) {
            return new Move(MoveDefinition.NONE);
        }

        int x0 = toBoard(pawn.getInitialX());
        int y0 = toBoard(pawn.getInitialY());

        if (Math.abs(destX - x0) == 1 && destY - y0 == pawn.getType().movementDirection && pawn.getPlayer().hasMove()) {

            return new Move(MoveDefinition.NORMAL);

        } else if (Math.abs(destX - x0) == 2 && destY - y0 == pawn.getType().movementDirection * 2 ||
                Math.abs(x0 - destX) == 2 && y0 - destY == pawn.getType().movementDirection * 2) {

            int forwardX = x0 + (destX - x0) / 2;
            int forwardY = y0 + (destY - y0) / 2;

            int backwardX = x0 - (x0 - destX) / 2;
            int backwardY = y0 - (y0 - destX) / 2;

            if (getFieldByIndex(backwardX, backwardY).hasPawn() && getFieldByIndex(backwardX, backwardY).getPawn().getType() != pawn.getType() && pawn.getPlayer().hasMove()) {
                return new Move(MoveDefinition.BEAT, getFieldByIndex(backwardX, backwardY).getPawn());
            }

            if (getFieldByIndex(forwardX, forwardY).hasPawn() && getFieldByIndex(forwardX, forwardY).getPawn().getType() != pawn.getType() && pawn.getPlayer().hasMove()) {
                return new Move(MoveDefinition.BEAT, getFieldByIndex(forwardX, forwardY).getPawn());
            }
        }

        return new Move(MoveDefinition.NONE);
    }

    private int toBoard(double pixel) {
        return (int) (pixel + FIELD_SIZE / 2) / FIELD_SIZE;
    }

    public Field getFieldByIndex(int x, int y) {
        return fields[x][y];
    }

    public void setFieldAtIntex(int x, int y, Field field) {
        fields[x][y] = field;
    }

    public Player getNextPlayer() {
        if (player1.hasMove()) {
            player1.setHasMove(false);
            player2.setHasMove(true);
            gamePanel.player1.setFill(Color.GREY);
            gamePanel.player2.setFill(Color.GREEN);
            gamePanel.player1.setUnderline(false);
            gamePanel.player2.setUnderline(true);
            return player2;
        } else {
            player2.setHasMove(false);
            player1.setHasMove(true);
            gamePanel.player1.setFill(Color.GREEN);
            gamePanel.player2.setFill(Color.GREY);
            gamePanel.player1.setUnderline(true);
            gamePanel.player2.setUnderline(false);
            return player1;
        }
    }

    public void setTimer() {
        Timer timer = new Timer();
        gamePanel.getTimer().setText(String.valueOf(interval));

        Player currentPlayer = player1.hasMove() ? player1 : player2;

        timer.scheduleAtFixedRate(new TimerTask() {
            int initialIntervalValue = interval;

            public void run() {
                Player actualPlayer = player1.hasMove() ? player1 : player2;

                if(initialIntervalValue > 0)
                {
                    if (currentPlayer != actualPlayer) {
                        timer.cancel();
                    } else {
                        Platform.runLater(() -> gamePanel.getTimer().setText(String.valueOf(initialIntervalValue)));
                        initialIntervalValue--;
                    }
                } else {
                    timer.cancel();
                    getNextPlayer();
                    setTimer();

                }

            }
        }, 1000,1000);
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
