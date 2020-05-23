package BG_control;

import BG_model.ChipColor;
import BG_view.Board;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static BG_model.ChipColor.BLACK;
import static BG_model.ChipColor.WHITE;

public class Move {

    public static void setNormalMove(Turn t, Board board) {
        final int[] prevI = {-1};
        final ChipColor[] color = new ChipColor[1];
        final boolean[] firstClick = {true};
        board.getGrid().getChildren().get(25).setStyle("-fx-background-color: none");
        board.getGrid().getChildren().get(24).setStyle("-fx-background-color: none");

        for (int i = 0; i < 24; i++) {

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
                            move(prevI[0], finalI, board, t);
                            normalMoveListChange(moveLength, t, board);
                        } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                            toBarMove(finalI, board, t);
                            move(prevI[0], finalI, board, t);
                            normalMoveListChange(moveLength, t, board);

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

    static void setBarMove(ChipColor color, Turn t, Board board) {
        board.getGrid().getChildren().get(24 + color.ordinal()).setStyle("-fx-background-color: #00ffff");
        int prevI = color == WHITE ? 24 : -1;
        List<Integer> canMove = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            int finalI = i;
            if ((board.getBoard().getBoard().get(finalI).size() < 2
                    || board.getBoard().getBoard().get(finalI).onTop() == color
            ) && t.getMoveList().contains(color == WHITE ? prevI - finalI : finalI - prevI)) {
                canMove.add(i);

            }
            board.getGrid().getChildren().get(finalI).setOnMouseClicked(mouseEvent -> {
                int moveLength = color == WHITE ? prevI - finalI : finalI - prevI;
                if (t.getMoveList().contains(moveLength) && canMove.contains(finalI)) {
                    if (board.getBoard().getBoard().get(finalI).size() == 1 && board.getBoard().getBoard().get(finalI).onTop() != color) {
                        toBarMove(finalI, board, t);
                        t.setNotEmptyBar(true, color == WHITE ? 1 : 0);
                    }
                    move(prevI == -1 ? 25 : prevI, finalI, board, t);
                    normalMoveListChange(moveLength, t, board);

                    if (board.getBoard().getBoard().get(24 + color.ordinal()).size() == 0) {
                        t.setNotEmptyBar(false, color.ordinal());
                        setNormalMove(t, board);
                    }
                }
            });
        }
        if (canMove.isEmpty()) {
            t.startTurn(board);

        }

    }

    static void setEndspielMove(ChipColor color, Turn t, Board board) {
        final int[] prevI = {-1};
        final boolean[] firstClick = {true};

        List<Integer> moveList = Arrays.asList(t.getMoveList().get(0), t.getMoveList().get(1));
        if (moveList.get(0).equals(moveList.get(1))) {
            moveList.add(moveList.get(0));
            moveList.add(moveList.get(0));
        }
        final int[] minMove = {moveList.get(1) > moveList.get(0) ? moveList.get(0) : moveList.get(1)};
        if (color == WHITE) {
            for (int i = 0; i < 6; i++) {
                int finalI = i;
                board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                    if (moveList.contains(finalI+1)) {
                        if (board.getBoard().getBoard().get(finalI).size() != 0 && color == board.getBoard().getBoard().get(finalI).onTop()) {
                            board.getBoard().getBoard().get(finalI).remove();
                            Pane prevNode = (Pane) board.getGrid().getChildren().get(finalI);
                            prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(finalI), true));
                            moveList.remove((Integer) finalI);
                            if (moveList.size() == 0) {
                                t.startTurn(board);
                            } else {
                                minMove[0] = moveList.get(0);
                            }
                        } else {
                            if (firstClick[0]) {
                                if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                                    board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                    firstClick[0] = false;
                                    prevI[0] = finalI;
                                }
                            } else {
                                int moveLength = prevI[0] - finalI;
                                if (t.getMoveList().contains(moveLength)) {
                                    if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(finalI).onTop()) {
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);
                                    } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                                        toBarMove(finalI, board, t);
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);

                                    }
                                }
                                firstClick[0] = true;
                                board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                                prevI[0] = -1;
                            }
                        }
                    } else if (finalI > minMove[0]) {
                        if (firstClick[0]) {
                            if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                                board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                firstClick[0] = false;
                                prevI[0] = finalI;
                            }
                        } else {
                            int moveLength = prevI[0] - finalI;
                            if (t.getMoveList().contains(moveLength)) {
                                if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(finalI).onTop()) {
                                    move(prevI[0], finalI, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                                    toBarMove(finalI, board, t);
                                    move(prevI[0], finalI, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                }
                            }
                            firstClick[0] = true;
                            board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                            prevI[0] = -1;
                        }
                    } else {
                        boolean first = true;
                        for (int j = 5; j >= finalI; j--) {
                            if (board.getBoard().getBoard().get(finalI).size() != 0) {
                                first = false;
                                break;
                            }

                        }
                        if (first) {
                            if (board.getBoard().getBoard().get(finalI).size() != 0 && color == board.getBoard().getBoard().get(finalI).onTop()) {
                                board.getBoard().getBoard().get(finalI).remove();
                                moveList.remove((Integer) minMove[0]);
                                Pane prevNode = (Pane) board.getGrid().getChildren().get(finalI);
                                prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(finalI), true));
                                if (moveList.size() == 0) {
                                    t.startTurn(board);
                                } else {
                                    minMove[0] = moveList.get(0);
                                }
                            }
                        } else {
                            if (firstClick[0]) {
                                if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                                    board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                    firstClick[0] = false;
                                    prevI[0] = finalI;
                                }
                            } else {
                                int moveLength = prevI[0] - finalI;
                                if (t.getMoveList().contains(moveLength)) {
                                    if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(finalI).onTop()) {
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);
                                    } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                                        toBarMove(finalI, board, t);
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);
                                    }
                                }
                                firstClick[0] = true;
                                board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                                prevI[0] = -1;
                            }
                        }
                    }
                });
            }
        }else{
            for (int i = 18; i < 24; i++) {
                int finalI = i;
                board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                    if (moveList.contains(finalI-17)) {
                        if (board.getBoard().getBoard().get(finalI).size() != 0 && color == board.getBoard().getBoard().get(finalI).onTop()) {
                            board.getBoard().getBoard().get(finalI).remove();
                            Pane prevNode = (Pane) board.getGrid().getChildren().get(finalI);
                            prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(finalI), false));
                            moveList.remove(moveList.indexOf(finalI -17));
                            if (moveList.size() == 0) {
                                t.startTurn(board);
                            } else {
                                minMove[0] = moveList.get(0);
                            }
                        } else {
                            if (firstClick[0]) {
                                if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                                    board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                    firstClick[0] = false;
                                    prevI[0] = finalI;
                                }
                            } else {
                                int moveLength = prevI[0] - finalI;
                                if (t.getMoveList().contains(moveLength)) {
                                    if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(finalI).onTop()) {
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);
                                    } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                                        toBarMove(finalI, board, t);
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);

                                    }
                                }
                                firstClick[0] = true;
                                board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                                prevI[0] = -1;
                            }
                        }
                    } else if (finalI-17 > minMove[0]) {
                        if (firstClick[0]) {
                            if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                                board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                firstClick[0] = false;
                                prevI[0] = finalI;
                            }
                        } else {
                            int moveLength = prevI[0] - finalI;
                            if (t.getMoveList().contains(moveLength)) {
                                if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(finalI).onTop()) {
                                    move(prevI[0], finalI, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                                    toBarMove(finalI, board, t);
                                    move(prevI[0], finalI, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                }
                            }
                            firstClick[0] = true;
                            board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                            prevI[0] = -1;
                        }
                    } else {
                        boolean first = true;
                        for (int j = 5; j >= finalI - 17; j--) {
                            if (board.getBoard().getBoard().get(finalI).size() != 0) {
                                first = false;
                                break;
                            }

                        }
                        if (first) {
                            if (board.getBoard().getBoard().get(finalI).size() != 0 && color == board.getBoard().getBoard().get(finalI).onTop()) {
                                board.getBoard().getBoard().get(finalI).remove();
                                Pane prevNode = (Pane) board.getGrid().getChildren().get(finalI);
                                prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(finalI), false));
                                moveList.remove((Integer) minMove[0]);
                                if (moveList.size() == 0) {
                                    t.startTurn(board);
                                } else {
                                    minMove[0] = moveList.get(0);
                                }
                            }
                        } else {
                            if (firstClick[0]) {
                                if (board.getBoard().getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().getBoard().get(finalI).onTop().ordinal()) {
                                    board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                    firstClick[0] = false;
                                    prevI[0] = finalI;
                                }
                            } else {
                                int moveLength = prevI[0] - finalI;
                                if (t.getMoveList().contains(moveLength)) {
                                    if (board.getBoard().getBoard().get(finalI).size() == 0 || color == board.getBoard().getBoard().get(finalI).onTop()) {
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);
                                    } else if (board.getBoard().getBoard().get(finalI).size() == 1) {
                                        toBarMove(finalI, board, t);
                                        move(prevI[0], finalI, board, t);
                                        normalMoveListChange(moveLength, t, board);
                                    }
                                }
                                firstClick[0] = true;
                                board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                                prevI[0] = -1;
                            }
                        }
                    }
                });
            }
        }


    }

    private static void move(int from, int to, Board board, Turn t) {
        ChipColor color = board.getBoard().getBoard().get(from).onTop();
        if (color == WHITE) {
            if (from > 5) {
                if (to < 6) {
                    board.increaseWH();
                    if (board.getWhiteHome() == 15) setEndspielMove(WHITE, t, board);
                }
            }
        } else {
            if (from < 18) {
                if (to > 17) {
                    board.increaseBH();
                    if (board.getBlackHome() == 15) setEndspielMove(BLACK, t, board);
                }
            }
        }
        board.getBoard().move(from, to);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(from), from == 25 || from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(to);
        node.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), to < 12));
    }

    private static void toBarMove(int from, Board board, Turn t) {
        ChipColor color = board.getBoard().getBoard().get(from).onTop();
        if (color == WHITE) {
            if (from < 6) board.decreaseWH();
        } else {
            if (from > 17) board.decreaseBH();
        }
        t.setNotEmptyBar(true, color.ordinal());
        board.getBoard().move(from, color.ordinal() + 24);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(from), from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(color.ordinal() + 24);
        node.getChildren().set(0, board.column(board.getBoard().getBoard().get(color.ordinal() + 24), color.ordinal() == 1));
    }

    private static void normalMoveListChange(int moveLength, Turn t, Board board) {
        if (t.getMoveList().size() == 3) {
            switch (t.getMoveList().indexOf(moveLength)) {
                case (2): {
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
                    t.startTurn(board);
                    break;
                case (6): {
                    if (t.getMoveList().size() == 8)
                        t.getMoveList().subList(1, t.getMoveList().size()).clear();
                    break;
                }
                case (4): {
                    t.startTurn(board);
                }
                case (2): {
                    switch (t.getMoveList().size()) {
                        case (5): {
                            t.getMoveList().subList(1, t.getMoveList().size()).clear();
                            break;
                        }
                        case (3): {
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
                            t.startTurn(board);
                        }
                    }
                    break;
                }
            }
        }
    }
}
