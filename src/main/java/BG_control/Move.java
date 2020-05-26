package BG_control;

import BG_model.ChipColor;
import BG_view.Board;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import static BG_model.ChipColor.BLACK;
import static BG_model.ChipColor.WHITE;

public class Move {
    private static final int[] prevI = {-1};
    private static final boolean[] firstClick = {true};

    public static void setNormalMove(Turn t, Board board) {
        final ChipColor[] color = new ChipColor[1];
        board.getGrid().getChildren().get(25).setStyle("-fx-background-color: none");
        board.getGrid().getChildren().get(24).setStyle("-fx-background-color: none");

        for (int i = 0; i < 24; i++) {

            int finalI = i;
            board.getGrid().getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                if (firstClick[0]) {
                    if (board.getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().get(finalI).onTop().ordinal()) {
                        color[0] = board.getBoard().get(finalI).onTop();
                        board.getGrid().getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                        firstClick[0] = false;
                        prevI[0] = finalI;
                    }
                } else {
                    int moveLength = color[0] == WHITE ? prevI[0] - finalI : finalI - prevI[0];
                    if (t.getMoveList().contains(moveLength)) {
                        if (board.getBoard().get(finalI).size() == 0 || color[0] == board.getBoard().get(finalI).onTop()) {
                            move(prevI[0], finalI, board, t);
                            normalMoveListChange(moveLength, t, board);
                        } else if (board.getBoard().get(finalI).size() == 1) {
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
        List<Integer> canMove = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            int finalI = i;
            if ((board.getBoard().get(finalI).size() < 2
                    || board.getBoard().get(finalI).onTop() == color
            ) && t.getMoveList().contains(color == WHITE ? prevI - finalI : finalI - prevI)) {
                canMove.add(i);

            }
            board.getGrid().getChildren().get(finalI).setOnMouseClicked(mouseEvent -> {
                int moveLength = color == WHITE ? prevI - finalI : finalI - prevI;
                if (t.getMoveList().contains(moveLength) && canMove.contains(finalI)) {
                    if (board.getBoard().get(finalI).size() == 1 && board.getBoard().get(finalI).onTop() != color) {
                        toBarMove(finalI, board, t);
                    }
                    move(prevI == -1 ? 25 : prevI, finalI, board, t);
                    normalMoveListChange(moveLength, t, board);

                    if (board.getBoard().get(24 + color.ordinal()).size() == 0) {
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
        final int[] maxMove = {t.getMoveList().get(1) > t.getMoveList().get(0) ? t.getMoveList().get(1) : t.getMoveList().get(0)};
        if (color == WHITE) {
            for (int i = 0; i < 6; i++) {
                endSpielMoveW(maxMove, i, t, board);
            }
        } else {
            for (int i = 23; i > 17; i--) {
                endSpielMoveB(maxMove, i, t, board);
            }

        }


    }

    private static void endSpielMoveW(int[] maxMove, int to, Turn t, Board board) {
        if (board.getBoard().get(to).onTop() == WHITE) {
            if (t.getMoveList().indexOf(to + 1) < 2 && t.getMoveList().contains(to + 1)) {
                board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                    if (firstClick[0]) {
                        if (board.getBoard().get(to).size() != 0 && WHITE == board.getBoard().get(to).onTop()) {
                            board.getBoard().get(to).remove();
                            if (board.getBoard().get(to).size() == 0 && isWin(WHITE,board)) t.winnerAlert(board);
                            Pane prevNode = (Pane) board.getGrid().getChildren().get(to);
                            prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), true));
                            normalMoveListChange(to + 1, t, board);

                            if (t.getMoveList().size() != 0)
                                maxMove[0] = t.getMoveList().get(0);
                            endSpielMoveW(maxMove, to, t, board);
                            if (board.getBoard().get(to).size() == 0) {

                                for (int i = to - 1; i >= 0; i--) {
                                    if (board.getBoard().get(i).size() != 0) {
                                        endSpielMoveW(maxMove, i, t, board);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        int moveLength = prevI[0] - to;
                        if (t.getMoveList().contains(moveLength)) {
                            if (board.getBoard().get(to).size() == 0 || WHITE == board.getBoard().get(to).onTop()) {
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);
                                endSpielMoveW(maxMove, to, t, board);
                            } else if (board.getBoard().get(to).size() == 1) {
                                toBarMove(to, board, t);
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);
                                endSpielMoveW(maxMove, to, t, board);
                            }
                        }
                        firstClick[0] = true;
                        board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                        prevI[0] = -1;
                    }
                });
            } else if (to < maxMove[0]) {
                boolean first = true;
                for (int j = maxMove[0] - 1; j > to; j--) {
                    if (board.getBoard().get(j).size() != 0) {
                        first = false;
                        break;
                    }
                }
                if (first) {
                    board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                        if (firstClick[0]) {
                            if (board.getBoard().get(to).size() != 0 && WHITE == board.getBoard().get(to).onTop()) {
                                board.getBoard().get(to).remove();
                                if (board.getBoard().get(to).size() == 0 && isWin(WHITE,board)) t.winnerAlert(board);
                                Pane prevNode = (Pane) board.getGrid().getChildren().get(to);
                                prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), true));
                                normalMoveListChange(maxMove[0], t, board);

                                if (t.getMoveList().size() != 0)
                                    maxMove[0] = t.getMoveList().get(0);
                                endSpielMoveW(maxMove, to, t, board);
                                if (board.getBoard().get(to).size() == 0) {
                                    for (int i = to - 1; i >= 0; i--) {
                                        if (board.getBoard().get(i).size() != 0) {
                                            endSpielMoveW(maxMove, i, t, board);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            int moveLength = prevI[0] - to;
                            if (t.getMoveList().contains(moveLength)) {
                                if (board.getBoard().get(to).size() == 0 || WHITE == board.getBoard().get(to).onTop()) {
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);

                                    endSpielMoveW(maxMove, to, t, board);
                                } else if (board.getBoard().get(to).size() == 1) {
                                    toBarMove(to, board, t);
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);

                                    endSpielMoveW(maxMove, to, t, board);
                                }
                            }
                            firstClick[0] = true;
                            board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                            prevI[0] = -1;
                        }
                    });
                } else {
                    board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                        if (!firstClick[0]) {
                            int moveLength = prevI[0] - to;
                            if (t.getMoveList().contains(moveLength)) {
                                if (board.getBoard().get(to).size() == 0 || WHITE == board.getBoard().get(to).onTop()) {
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                    endSpielMoveW(maxMove, to, t, board);
                                } else if (board.getBoard().get(to).size() == 1) {
                                    toBarMove(to, board, t);
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);

                                    endSpielMoveW(maxMove, to, t, board);
                                }
                            }
                            firstClick[0] = true;
                            board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                            prevI[0] = -1;
                        }
                    });
                }

            } else {
                board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                    if (firstClick[0]) {
                        if (board.getBoard().get(to).size() != 0 && t.playerNumber() == board.getBoard().get(to).onTop().ordinal()) {
                            board.getGrid().getChildren().get(to).setStyle("-fx-background-color: #00ffff");
                            firstClick[0] = false;
                            prevI[0] = to;
                        }
                    } else {
                        int moveLength = prevI[0] - to;
                        if (t.getMoveList().contains(moveLength)) {
                            if (board.getBoard().get(to).size() == 0 || WHITE == board.getBoard().get(to).onTop()) {
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);

                                endSpielMoveW(maxMove, to, t, board);
                            } else if (board.getBoard().get(to).size() == 1) {
                                toBarMove(to, board, t);
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);

                                endSpielMoveW(maxMove, to, t, board);

                            }
                        }
                        firstClick[0] = true;
                        board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                        prevI[0] = -1;

                    }
                });
            }
        }

    }

    private static void endSpielMoveB(int[] maxMove, int to, Turn t, Board board) {
        if (board.getBoard().get(to).onTop() == BLACK) {
            if (t.getMoveList().indexOf(24 - to) < 2 && t.getMoveList().contains(24 - to)) {
                board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                    if (firstClick[0]) {
                        if (board.getBoard().get(to).size() != 0 && BLACK == board.getBoard().get(to).onTop()) {
                            board.getBoard().get(to).remove();
                            if (board.getBoard().get(to).size() == 0 && isWin(BLACK,board)) t.winnerAlert(board);
                            Pane prevNode = (Pane) board.getGrid().getChildren().get(to);
                            prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), false));
                            normalMoveListChange(24 - to, t, board);
                            if (t.getMoveList().size() != 0)
                                maxMove[0] = t.getMoveList().get(0);
                            endSpielMoveB(maxMove, to, t, board);
                            if (board.getBoard().get(to).size() == 0) {
                                for (int i = to + 1; i < 24; i++) {
                                    if (board.getBoard().get(i).size() != 0) {
                                        endSpielMoveB(maxMove, i, t, board);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        int moveLength = to - prevI[0];
                        if (t.getMoveList().contains(moveLength)) {
                            if (board.getBoard().get(to).size() == 0 || BLACK == board.getBoard().get(to).onTop()) {
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);
                                endSpielMoveB(maxMove, to, t, board);
                            } else if (board.getBoard().get(to).size() == 1) {
                                toBarMove(to, board, t);
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);
                                endSpielMoveB(maxMove, to, t, board);
                            }
                        }
                        firstClick[0] = true;
                        board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                        prevI[0] = -1;
                    }
                });
            } else if (24 - to < maxMove[0]) {
                boolean first = true;
                for (int j = maxMove[0] - 1; j > 24 - to; j--) {
                    if (board.getBoard().get(24 - j).size() != 0) {
                        first = false;
                        break;
                    }
                }
                if (first) {
                    board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                        if (firstClick[0]) {
                            if (board.getBoard().get(to).size() != 0 && BLACK == board.getBoard().get(to).onTop()) {
                                board.getBoard().get(to).remove();
                                if (board.getBoard().get(to).size() == 0 && isWin(BLACK,board)) t.winnerAlert(board);
                                Pane prevNode = (Pane) board.getGrid().getChildren().get(to);
                                prevNode.getChildren().set(0, board.column(board.getBoard().getBoard().get(to), false));
                                normalMoveListChange(maxMove[0], t, board);
                                if (t.getMoveList().size() != 0)
                                    maxMove[0] = t.getMoveList().get(0);
                                endSpielMoveB(maxMove, to, t, board);
                                if (board.getBoard().get(to).size() == 0) {

                                    for (int i = to + 1; i < 24; i++) {
                                        if (board.getBoard().get(i).size() != 0) {
                                            endSpielMoveB(maxMove, i, t, board);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            int moveLength = to - prevI[0];
                            if (t.getMoveList().contains(moveLength)) {
                                if (board.getBoard().get(to).size() == 0 || BLACK == board.getBoard().get(to).onTop()) {
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                    endSpielMoveB(maxMove, to, t, board);
                                } else if (board.getBoard().get(to).size() == 1) {
                                    toBarMove(to, board, t);
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                    endSpielMoveB(maxMove, to, t, board);
                                }
                            }
                            firstClick[0] = true;
                            board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                            prevI[0] = -1;
                        }
                    });
                } else {
                    board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                        if (!firstClick[0]) {
                            int moveLength = to - prevI[0];
                            if (t.getMoveList().contains(moveLength)) {
                                if (board.getBoard().get(to).size() == 0 || BLACK == board.getBoard().get(to).onTop()) {
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                    endSpielMoveB(maxMove, to, t, board);
                                } else if (board.getBoard().get(to).size() == 1) {
                                    toBarMove(to, board, t);
                                    move(prevI[0], to, board, t);
                                    normalMoveListChange(moveLength, t, board);
                                    endSpielMoveB(maxMove, to, t, board);
                                }
                            }
                            firstClick[0] = true;
                            board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                            prevI[0] = -1;
                        }
                    });
                }
            } else {
                board.getGrid().getChildren().get(to).setOnMouseClicked(mouseEvent -> {
                    if (firstClick[0]) {
                        if (board.getBoard().get(to).size() != 0 && t.playerNumber() == board.getBoard().get(to).onTop().ordinal()) {
                            board.getGrid().getChildren().get(to).setStyle("-fx-background-color: #00ffff");
                            firstClick[0] = false;
                            prevI[0] = to;
                        }
                    } else {
                        int moveLength = to - prevI[0];
                        if (t.getMoveList().contains(moveLength)) {
                            if (board.getBoard().get(to).size() == 0 || BLACK == board.getBoard().get(to).onTop()) {
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);
                                endSpielMoveB(maxMove, to, t, board);
                            } else if (board.getBoard().get(to).size() == 1) {
                                toBarMove(to, board, t);
                                move(prevI[0], to, board, t);
                                normalMoveListChange(moveLength, t, board);
                                endSpielMoveB(maxMove, to, t, board);
                            }
                        }
                        firstClick[0] = true;
                        board.getGrid().getChildren().get(prevI[0]).setStyle("-fx-background-color: none");
                        prevI[0] = -1;

                    }
                });
            }
        }

    }

    private static boolean isWin (ChipColor color, Board board){
            for (int i = 5;i>=0;i--){
                if (board.getBoard().get(color ==WHITE? i:23-i).size() != 0) return false;
            }
            return true;

    }

    private static void move(int from, int to, Board board, Turn t) {
        ChipColor color = board.getBoard().get(from).onTop();
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
        prevNode.getChildren().set(0, board.column(board.getBoard().get(from), from == 25 || from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(to);
        node.getChildren().set(0, board.column(board.getBoard().get(to), to < 12));
    }

    private static void toBarMove(int from, Board board, Turn t) {
        ChipColor color = board.getBoard().get(from).onTop();
        if (color == WHITE) {
            if (from < 6) board.decreaseWH();
        } else {
            if (from > 17) board.decreaseBH();
        }
        t.setNotEmptyBar(true, color.ordinal());
        board.getBoard().move(from, color.ordinal() + 24);
        Pane prevNode = (Pane) board.getGrid().getChildren().get(from);
        prevNode.getChildren().set(0, board.column(board.getBoard().get(from), from < 12));
        Pane node = (Pane) board.getGrid().getChildren().get(color.ordinal() + 24);
        node.getChildren().set(0, board.column(board.getBoard().get(color.ordinal() + 24), color.ordinal() == 1));
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
