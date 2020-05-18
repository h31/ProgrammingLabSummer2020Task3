package BG_control;

import BG_model.ChipColor;
import BG_view.Board;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import static BG_model.ChipColor.BLACK;
import static BG_model.ChipColor.WHITE;

public class Move {
    private Turn t;
    private Board board;

    private int prevI = -1;
    private ChipColor color;
    private boolean firstClick = true;

    public Move(Turn t, Board board) {
        this.board = board;
        this.t = t;
    }

    public void setNormalMove() {
        for (int i = 0; i < 24; i++) {
            int finalI = i;
            board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                if (firstClick) {

                    if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                        color = board.getBoard().getBoard().get(finalI).onTop();
                        board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                        firstClick = false;
                        prevI = finalI;
                    }
                } else {
                    int moveLength = color == WHITE ? prevI - finalI : finalI - prevI;
                    if (t.getMoveList().contains(moveLength)) {
                        if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(prevI).onTop()) {
                            move(prevI, finalI);
                            moveListChange(moveLength);
                        } else if (board.getBoard().getBoard().get(finalI).size() == 1) {

                            toBlotMove(finalI);
                            //move(prevI, finalI);
                            moveListChange(moveLength);
                            t.setNotEmptyBlot(true, color.ordinal());
                        }
                    }
                    firstClick = true;
                    board.getGrid().getChildren().get(prevI).setStyle("-fx-background-color: none");
                    prevI = -1;
                    color = null;
                }

            });
        }
    }

    void setBlotMove(ChipColor color) {
        if (color == WHITE) {
            for (int i = 0; i < 18; i++) {
                board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                });
            }
            board.getGrid().getChildren().get(24).setStyle("-fx-background-color: #00ffff");
            firstClick = false;
            prevI = 24;
            List<Integer> canMove = new ArrayList<Integer>();
            for (int i = 18; i < 24; i++) {
                int finalI = i;
                if (board.getBoard().getBoard().get(finalI).size() == 0
                        || board.getBoard().getBoard().get(finalI).onTop() == board.getBoard().getBoard().get(prevI).onTop()
                        || board.getBoard().getBoard().get(finalI).size() == 1) {
                    canMove.add(i);
                    board.getGrid().getChildren().get(i).setStyle("-fx-background-color: #F0E68C");
                }

                board.getGrid().getChildren().get(finalI).setOnMouseClicked(mouseEvent -> {
                    int moveLength = prevI - finalI;
                    if (t.getMoveList().contains(moveLength) && canMove.contains(finalI)) {
                        if (board.getBoard().getBoard().get(finalI).size() == 1) {
                            toBlotMove(finalI);
                            t.setNotEmptyBlot(true, ChipColor.BLACK.ordinal());
                        }
                        for (Integer j : canMove) {
                            board.getGrid().getChildren().get(j).setStyle("-fx-background-color: none");
                        }
                        t.setNotEmptyBlot(false, WHITE.ordinal());
                    }
                });
            }
            this.setNormalMove();
            if (canMove.isEmpty()) t.startTurn(board);
        } else {
            for (int i = 23; i > 5; i--) {
                board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                });
            }
            board.getGrid().getChildren().get(25).setStyle("-fx-background-color: #00ffff");
            firstClick = false;
            prevI = -1;
            List<Integer> canMove = new ArrayList<Integer>();
            for (int i = 5; i > -1; i--) {
                int finalI = i;
                if (board.getBoard().getBoard().get(finalI).size() == 0
                        || board.getBoard().getBoard().get(finalI).onTop() == board.getBoard().getBoard().get(prevI).onTop()
                        || board.getBoard().getBoard().get(finalI).size() == 1) {
                    canMove.add(i);
                    board.getGrid().getChildren().get(i).setStyle("-fx-background-color: #F0E68C");
                }
                board.getGrid().getChildren().get(finalI).setOnMouseClicked(mouseEvent -> {
                    int moveLength = finalI - prevI;
                    if (t.getMoveList().contains(moveLength) && canMove.contains(finalI)) {
                        move(prevI, finalI);
                        moveListChange(moveLength);
                        if (board.getBoard().getBoard().get(finalI).size() == 1) {
                            toBlotMove(finalI);
                            t.setNotEmptyBlot(true, WHITE.ordinal());
                        }
                        for (Integer j : canMove) {
                            board.getGrid().getChildren().get(j).setStyle("-fx-background-color: none");
                        }
                        t.setNotEmptyBlot(false, BLACK.ordinal());
                    }
                });
            }
            if (canMove.isEmpty()) t.startTurn(board);
        }
    }

    private void move(int from, int to) {
        board.getBoard().move(from, to);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(from), prevI < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(to);
        node.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), to < 12));
    }

    private void toBlotMove(int from) {
        ChipColor color = board.getBoard().getBoard().get(from).onTop();
        board.getBoard().move(from, color.ordinal() + 24);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(from), from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(color.ordinal() + 24);
        node.getChildren().set(0, board.column(board.getBoard().getBoard().get(color.ordinal() + 24), color.ordinal() == 1));
    }

    private void moveListChange(int moveLength) {
        if (t.getMoveList().size() == 3) {
            switch (t.getMoveList().indexOf(moveLength)) {
                case (2): {
                    t.setMoveList(new ArrayList<Integer>());
                    t.startTurn(board);
                    break;
                }
                case (1): {
                    t.getMoveList().remove(2);
                    t.getMoveList().remove(1);
                    break;
                }
                case (0): {
                    t.getMoveList().remove(2);
                    t.getMoveList().remove(0);
                    break;
                }
            }
        } else {
            switch (t.getMoveList().indexOf(moveLength)) {
                case (7):
                    t.setMoveList(new ArrayList<Integer>());
                    t.startTurn(board);
                    break;
                case (6): {
                    if (t.getMoveList().size() == 8)
                        t.getMoveList().subList(1, t.getMoveList().size()).clear();
                    break;
                }
                case (4): {
                    t.setMoveList(new ArrayList<Integer>());
                    t.startTurn(board);
                }
                case (2): {
                    switch (t.getMoveList().size()) {
                        case (5): {
                            t.getMoveList().subList(1, t.getMoveList().size()).clear();
                            break;
                        }
                        case (3): {
                            t.setMoveList(new ArrayList<Integer>());
                            t.startTurn(board);
                            break;
                        }

                        case (4):
                            t.getMoveList().subList(1, t.getMoveList().size()).clear();
                            break;
                        default:
                            t.getMoveList().subList(3, t.getMoveList().size()).clear();
                            break;
                    }
                    break;
                }
                default: {
                    switch (t.getMoveList().size()) {
                        case (8): {
                            t.getMoveList().remove(7);
                            t.getMoveList().remove(5);
                            t.getMoveList().remove(4);
                            break;
                        }
                        case (5): {
                            t.getMoveList().subList(3, t.getMoveList().size()).clear();
                            break;
                        }
                        case (3): {
                            t.getMoveList().subList(1, t.getMoveList().size()).clear();
                            break;
                        }
                        case (2): {
                            t.getMoveList().remove(1);
                            break;
                        }
                        default: {
                            t.setMoveList(new ArrayList<Integer>());
                            t.startTurn(board);
                        }
                    }
                    break;
                }
            }
        }
    }
}
