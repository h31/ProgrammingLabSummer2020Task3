package BG_view;

import BG_control.Move;
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
    public final static int CHIP_SIZE = 55;
    private final static List<Pair<Integer, Integer>> columnList = Arrays.asList(
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
    private Turn t;

    public Board(Turn t) {
        this.t = t;
    }



    //TODO(оформление кнопки)
    private Button gButton() {
        Button btn = new Button();
        btn.setText("New\nGame");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: #00FF00");
        btn.setOnAction(event -> {
            btn.setStyle("-fx-background-color: grey");
            btn.setText("Next\nTurn");
            t.startTurn(this);
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
        grid.add(gButton(), 13, 1);


        Move.setNormalMove(t, this);
        return grid;
    }

    private GridPane grid() {

        ColumnConstraints column = new ColumnConstraints(CHIP_SIZE);
        column.setPercentWidth(9.355);


        for (int i = 0; i < 14; i++) {
            grid.getColumnConstraints().add(column);
        }

        RowConstraints row = new RowConstraints(CHIP_SIZE*6);
        row.setPercentHeight(50);

        grid.getRowConstraints().add(row);
        grid.getRowConstraints().add(row);

        return grid;
    }

    public VBox column(Column mColumn, boolean bottomLine) {
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
            if (over) vColumn.getChildren().add(columnOverFlow(mColumn.size() - 5));
        } else {
            if (over) {
                vColumn.getChildren().add(columnOverFlow(mColumn.size() - 5));
            } else vColumn.setPadding(new Insets(CHIP_SIZE + (CHIP_SIZE * (5 - mColumn.size())), 0, 0, 0));
            for (int i = 0; i < size; i++) {
                vColumn.getChildren().add(chipCanvas(mColumn.onTop()));
            }
        }
        return vColumn;

    }

    private Canvas chipCanvas(ChipColor color) {
        Canvas canvas = new Canvas(CHIP_SIZE, CHIP_SIZE);
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
        Canvas canvas = new Canvas(CHIP_SIZE, CHIP_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, i > 9 ? CHIP_SIZE*0.6 : CHIP_SIZE*0.9);
        gc.setFont(theFont);
        gc.fillText("+" + i, 0, CHIP_SIZE*0.9);
        gc.strokeText("+" + i, 0, CHIP_SIZE*0.9);
        return canvas;
    }

    public BG_model.Board getBoard() {
        return board;
    }

    public GridPane getGrid() {
        return grid;
    }
}

