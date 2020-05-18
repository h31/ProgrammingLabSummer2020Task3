package BG_view;

import BG_control.Turn;
import BG_model.ChipColor;
import BG_model.Column;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Board {

    public final static List<Pair<Integer, Integer>> columnList = Arrays.asList(
            new Pair<>(11, 1),
            new Pair<>(10, 1),
            new Pair<>(9, 1),
            new Pair<>(8, 1),
            new Pair<>(7, 1),
            new Pair<>(6, 1),
            new Pair<>(5, 1),
            new Pair<>(4, 1),
            new Pair<>(3, 1),
            new Pair<>(2, 1),
            new Pair<>(1, 1),
            new Pair<>(0, 1),
            new Pair<>(0, 0),
            new Pair<>(1, 0),
            new Pair<>(2, 0),
            new Pair<>(3, 0),
            new Pair<>(4, 0),
            new Pair<>(5, 0),
            new Pair<>(6, 0),
            new Pair<>(7, 0),
            new Pair<>(8, 0),
            new Pair<>(9, 0),
            new Pair<>(10, 0),
            new Pair<>(11, 0),
            new Pair<>(12, 0),
            new Pair<>(12, 1)
    );
    private BG_model.Board board = new BG_model.Board();
    private GridPane grid = new GridPane();
    private boolean firstClick = true;

    private Turn t;
    private int prevI = -1;

    Board(Turn t) {
        this.t = t;
    }

    Button gButton(GridPane board) {
        Button btn = new Button();
        btn.setText(" New\nGame");
        btn.setMaxHeight(55);
        btn.setOnAction(event -> {
            btn.setText("Next\nTurn");
            t.startTurn();
        });
        return btn;
    }

    GridPane board() {
        grid = grid();
        for (int i = 0; i < 12; i++) {
            Pair<Integer, Integer> colID = columnList.get(i);
            Pane node = new Pane();
            node.getChildren().add(column(board.getBoard().get(i), true));
            grid.add(node, colID.getKey(), colID.getValue());
        }
        for (int i = 12; i < 24; i++) {
            Pair<Integer, Integer> colID = columnList.get(i);
            Pane node = new Pane();
            node.getChildren().add(column(board.getBoard().get(i), false));
            grid.add(node, colID.getKey(), colID.getValue());
        }
        Pane wb = new Pane();
        wb.getChildren().add(column(board.getBoard().get(24), false));
        grid.add(wb, columnList.get(24).getKey(), columnList.get(24).getValue());
        Pane bb = new Pane();
        bb.getChildren().add(column(board.getBoard().get(24), true));
        grid.add(bb, columnList.get(25).getKey(), columnList.get(25).getValue());
        grid.add(gButton(grid), 13, 1);

        for (int i = 0; i < 26; i++) {
            int finalI = i;
            grid.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                        if (t.getTurnCount() != 0) {
                            if (board.getBoard().get(t.playerNumber() + 23).size() != 0) {
                                grid.getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                firstClick = false;
                                prevI = finalI;
                            } else {
                                if (firstClick) {
                                    if (board.getBoard().get(finalI).size() != 0 && t.playerNumber() == board.getBoard().get(finalI).onTop().ordinal()) {
                                        grid.getChildren().get(finalI).setStyle("-fx-background-color: #00ffff");
                                        firstClick = false;
                                        prevI = finalI;
                                    }
                                } else {
                                    if (board.getBoard().get(finalI).size() == 0 | board.getBoard().get(finalI).onTop() == board.getBoard().get(prevI).onTop()) {
                                        move(prevI, finalI);
                                    } else if (board.getBoard().get(finalI).size() == 1) {
                                        blotMove(finalI, board.getBoard().get(finalI).onTop());
                                        move(prevI, finalI);
                                    }
                                    firstClick = true;
                                    grid.getChildren().get(prevI).setStyle("-fx-background-color: none");
                                    prevI = -1;
                                }
                            }
                        }
                    }
            );
        }
        return grid;
    }

    private void move(int from, int to) {
        board.move(from, to);
        Pane prevNode = (Pane) grid.getChildren().get(from);
        prevNode.getChildren().set(0, column(board.getBoard().get(from), prevI < 12));
        Pane node = (Pane) grid.getChildren().get(to);
        node.getChildren().set(0, column(board.getBoard().get(to), to < 12));
    }

    private void blotMove(int from, ChipColor color) {
        board.move(from, color.ordinal() + 24);
        Pane prevNode = (Pane) grid.getChildren().get(from);
        prevNode.getChildren().set(0, column(board.getBoard().get(from), prevI < 12));
        Pane node = (Pane) grid.getChildren().get(color.ordinal() + 24);
        node.getChildren().set(0, column(board.getBoard().get(color.ordinal() + 24), color.ordinal() == 1));
    }

    private GridPane grid() {

        ColumnConstraints column = new ColumnConstraints(55);
        column.setPercentWidth(9.355);


        for (int i = 0; i < 14; i++) {
            grid.getColumnConstraints().add(column);
        }

        RowConstraints row = new RowConstraints(360);
        row.setPercentHeight(50);

        grid.getRowConstraints().add(row);
        grid.getRowConstraints().add(row);

        return grid;
    }

    private VBox column(Column mColumn, boolean bottomLine) {
        VBox vColumn = new VBox();
        vColumn.setSpacing(0);
        boolean over = false;
        int size = mColumn.size();

        if (size > 4) {
            size = 5;
            over = true;
        }

        if (!bottomLine) {
            for (int i = 0; i < size; i++) {
                vColumn.getChildren().add(chipCanvas(mColumn.onTop()));
            }
            vColumn.setPadding(new Insets(20, 0, 0, 0));
            if (over) vColumn.getChildren().add(columnOverFlow(mColumn.size() - 5));
        } else {
            if (over) {
                vColumn.getChildren().add(columnOverFlow(mColumn.size() - 5));
                vColumn.setPadding(new Insets(0, 0, 20, 0));
            } else vColumn.setPadding(new Insets(65 + (55 * (5 - mColumn.size())), 0, 20, 0));
            for (int i = 0; i < size; i++) {
                vColumn.getChildren().add(chipCanvas(mColumn.onTop()));
            }
        }
        return vColumn;

    }

    private Canvas chipCanvas(ChipColor color) {
        Canvas canvas = new Canvas(55, 55);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image chip;
        switch (color) {
            case WHITE:
                chip = new Image("white.png");
                break;
            case BLACK:
                chip = new Image("black.png");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + color);
        }
        gc.drawImage(chip, 0, 0);
        return canvas;
    }

    private Canvas columnOverFlow(int i) {
        Canvas canvas = new Canvas(55, 65);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, i > 9 ? 30 : 50);
        gc.setFont(theFont);
        gc.fillText("+" + i, 0, 50);
        gc.strokeText("+" + i, 0, 50);
        return canvas;
    }

    private Pair<Integer, Column> choose(int i) {
        Column prevColumn = board.getBoard().get(i);
        return new Pair<>(i, prevColumn);
    }


}

