package BG_control;

import BG_model.ChipColor;
import BG_view.Board;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import static BG_model.ChipColor.WHITE;

public class Move {

    public static void setNormalMove(Turn t,Board board) {
        final int[] prevI = {-1};
        final ChipColor[] color = new ChipColor[1];
        final boolean[] firstClick = {true};

        for (int i = 0; i < 24; i++) {
            board.getGrid().getChildren().get(i).setStyle("-fx-background-color: none");
            int finalI = i;
            board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                if (firstClick[0]) {
                    if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                        color[0] = board.getBoard().getBoard().get(finalI).onTop();
                        board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                        firstClick[0] = false;
                        prevI[0] = finalI;
                    }
                } else {
                    int moveLength = color[0] == WHITE ? prevI[0] - finalI : finalI - prevI[0];
                    if (t.getMoveList().contains(moveLength)) {
                        if (board.getBoard().getBoard().get(finalI).size() == 0 || color[0] == board.getBoard().getBoard().get(finalI).onTop()) {
                            move(prevI[0], finalI,board);
                            moveListChange(moveLength, t, board);
                        } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                            toBlotMove(finalI,board,t);
                            move(prevI[0], finalI, board);
                            moveListChange(moveLength,t ,board);

                        }
                    }
                    firstClick[0] = true;
                    board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                    prevI[0] = -1;
                    color[0] = null;
                }

            });
        }
    }

    static void setBlotMove(ChipColor color, Turn t, Board board) {
        board.getGrid().getChildren().get(24).setStyle("-fx-background-color: #00ffff");
        boolean firstClick = false;
        int prevI = 24;
        List<Integer> canMove = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            int finalI = i;
            if ((board.getBoard().getBoard().get(finalI).size() < 2
                    || board.getBoard().getBoard().get(finalI).onTop() == color
                    ) && t.getMoveList().contains(prevI - finalI)) {
                canMove.add(i);
                board.getGrid().getChildren().get(i).setStyle("-fx-background-color: #F0E68C");
            }
            board.getGrid().getChildren().get(finalI).setOnMouseClicked(mouseEvent -> {
                int moveLength = prevI - finalI;
                if (t.getMoveList().contains(moveLength) && canMove.contains(finalI)) {

                    if (board.getBoard().getBoard().get(finalI).size() == 1 && board.getBoard().getBoard().get(finalI).onTop() != WHITE) {
                        toBlotMove(finalI, board,t);
                        t.setNotEmptyBlot(true, ChipColor.BLACK.ordinal());
                    }
                    move(prevI, finalI,board);
                    moveListChange(moveLength,t,board);
                    t.setNotEmptyBlot(false, WHITE.ordinal());
                }
            });
        }
        setNormalMove(t, board);
        if (canMove.isEmpty()) t.startTurn(board);

    }

    private static void move(int from, int to,Board board) {
        board.getBoard().move(from, to);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(from), from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(to);
        node.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), to < 12));
    }

    private static void toBlotMove(int from,Board board,Turn t) {

        ChipColor color = board.getBoard().getBoard().get(from).onTop();
        t.setNotEmptyBlot(true, color.ordinal());
        board.getBoard().move(from, color.ordinal() + 24);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(from), from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(color.ordinal() + 24);
        node.getChildren().set(0, board.column(board.getBoard().getBoard().get(color.ordinal() + 24), color.ordinal() == 1));
    }

    private static void moveListChange(int moveLength, Turn t, Board board) {
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
