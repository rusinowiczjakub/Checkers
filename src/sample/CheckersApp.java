package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameBoard;

public class CheckersApp extends Application {

    static final int FIELD_SIZE = 100;
    static final int WIDTH = 8;
    static final int HEIGHT = 8;

    private GameBoard gameBoard = new GameBoard(WIDTH, HEIGHT);
    private Group fieldsGroup = new Group();
    private Group pawns = new Group();

    private Parent drawBoard() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * FIELD_SIZE, HEIGHT * FIELD_SIZE);
        root.getChildren().addAll(fieldsGroup, pawns);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Field field = new Field((i + j) % 2 == 0, j, i);

                gameBoard.setFieldAtIntex(j, i, field);

                fieldsGroup.getChildren().add(field);

                Pawn pawn = null;

                if (i <= 2 && (i + j) % 2 != 0) {
                    pawn = makePawn(PawnDefinition.BLACK, j, i);
                }

                if (i >= 5 && (i + j) % 2 != 0) {
                    pawn = makePawn(PawnDefinition.WHITE, j, i);
                }

                if (pawn != null) {
                    field.setPawn(pawn);
                    pawns.getChildren().add(pawn);
                }
            }
        }

        return root;
    }

    private Move tryMove(Pawn pawn, int destX, int destY) {
        if (gameBoard.getFieldByIndex(destX, destY).hasPawn() || (destX + destY) % 2 == 0) {
            return new Move(MoveDefinition.NONE);
        }

        int x0 = toBoard(pawn.getInitialX());
        int y0 = toBoard(pawn.getInitialY());

        if (Math.abs(destX - x0) == 1 && destY - y0 == pawn.getType().movementDirection) {

            return new Move(MoveDefinition.NORMAL);

        } else if(Math.abs(destX - x0) == 2 && destY - y0 == pawn.getType().movementDirection * 2) {
            int x1 = x0 + (destX - x0) / 2;
            int y1 = y0 + (destY - y0) / 2;

            if (gameBoard.getFieldByIndex(x1, y1).hasPawn() && gameBoard.getFieldByIndex(x1, y1).getPawn().getType() != pawn.getType()) {
                return new Move(MoveDefinition.BEAT, gameBoard.getFieldByIndex(x1, y1).getPawn());
            }
        }

        return new Move(MoveDefinition.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + FIELD_SIZE / 2) / FIELD_SIZE;
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
                result = tryMove(pawn, newX, newY);
            }


            int x0 = toBoard(pawn.getInitialX());
            int y0 = toBoard(pawn.getInitialY());

            switch(result.getMoveType()) {
                case NONE:
                    pawn.preventMove();
                    break;

                case BEAT:
                    pawn.movePawn(newX, newY);
                    gameBoard.getFieldByIndex(x0, y0).setPawn(null);
                    gameBoard.getFieldByIndex(newX, newY).setPawn(pawn);

                    Pawn beatenPawn = result.getPawn();
                    gameBoard.getFieldByIndex((toBoard(beatenPawn.getInitialX())), toBoard(beatenPawn.getInitialY())).setPawn(null);
                    pawns.getChildren().remove(beatenPawn);
                    break;

                case NORMAL:
                    pawn.movePawn(newX, newY);
                    gameBoard.getFieldByIndex(x0, y0).setPawn(null);
                    gameBoard.getFieldByIndex(newX, newY).setPawn(pawn);
                    break;
            }
        });

        return pawn;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(drawBoard());
        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
