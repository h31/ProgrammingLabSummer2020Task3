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
import java.util.concurrent.atomic.AtomicBoolean;


public class Board {
    public final static List<Pair<Integer, Integer>> columnList = Arrays.asList(
            new Pair<>(13, 1),
            new Pair<>(12, 1),
            new Pair<>(11, 1),
            new Pair<>(10, 1),
            new Pair<>(9, 1),
            new Pair<>(8, 1),
            new Pair<>(6, 1),
            new Pair<>(5, 1),
            new Pair<>(4, 1),
            new Pair<>(3, 1),
            new Pair<>(2, 1),
            new Pair<>(1, 1),
            new Pair<>(1, 0),
            new Pair<>(2, 0),
            new Pair<>(3, 0),
            new Pair<>(4, 0),
            new Pair<>(5, 0),
            new Pair<>(6, 0),
            new Pair<>(8, 0),
            new Pair<>(9, 0),
            new Pair<>(10, 0),
            new Pair<>(11, 0),
            new Pair<>(12, 0),
            new Pair<>(13, 0),
            new Pair<>(7, 0),
            new Pair<>(7, 1)
    );
    private BG_model.Board board = new BG_model.Board();
    private GridPane grid = new GridPane();
    private int prevI = -1;
    private Turn t = new Turn();

    Board(){}

    Button gButton(GridPane board) {
        Button btn = new Button();
        btn.setText(" New\nGame");
        btn.setMaxHeight(55);
        btn.setOnAction(event -> {
            btn.setText("Next\nTurn");
            t.startTurn(board);
        });
        return btn;
    }

    GridPane board(Turn t) {
        grid = grid();
        for (int i = 0; i < 12; i++) {
            Pair<Integer, Integer> colID = columnList.get(i);
            grid.add(column(board.getBoard().get(i), true), colID.getKey(), colID.getValue());
        }
        for (int i = 12; i < 24; i++) {
            Pair<Integer, Integer> colID = columnList.get(i);
            grid.add(column(board.getBoard().get(i), false), colID.getKey(), colID.getValue());
        }
        grid.add(column(board.getBlackBlot(), false), columnList.get(24).getKey(), columnList.get(24).getValue());
        grid.add(column(board.getWhiteBlot(), true), columnList.get(25).getKey(), columnList.get(25).getValue());
        grid.add(gButton(grid), 15, 1);
        grid.setBackground(new Background(new BackgroundImage(new Image("board.png"), null, null, null, null)));

        AtomicBoolean firstClick = new AtomicBoolean(true);
        for (int i = 0; i < 24; i++) {


            int finalI = i;
            grid.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                move(finalI,firstClick);
            });

        }

        return grid;

    }

    private GridPane grid() {

        ColumnConstraints cPad = new ColumnConstraints(15);
        cPad.setPercentWidth(1.965);

        ColumnConstraints column = new ColumnConstraints(55);
        column.setPercentWidth(7.39);

        grid.getColumnConstraints().add(cPad);
        for (int i = 0; i < 13; i++) {
            grid.getColumnConstraints().add(column);
        }

        grid.getColumnConstraints().add(cPad);
        grid.getColumnConstraints().add(column);

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
            for (int i = 0; i < mColumn.size() && i < 5; i++) {
                vColumn.getChildren().add(chipCanvas(mColumn.onTop()));
            }
            vColumn.setPadding(new Insets(20, 0, 0, 0));
            if (over) vColumn.getChildren().add(columnOverFlow(size - 5));
        } else {
            if (over) {
                vColumn.getChildren().add(columnOverFlow(size - 5));
                vColumn.setPadding(new Insets(0, 0, 20, 0));
            } else vColumn.setPadding(new Insets(65 + (55 * (5 - mColumn.size())), 0, 20, 0));
            for (int i = 0; i < mColumn.size() && i < 5; i++) {
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
        Font theFont = Font.font("Arial", FontWeight.BOLD, 50);
        gc.setFont(theFont);
        gc.fillText("+" + i, 0, 50);
        gc.strokeText("+" + i, 0, 50);
        return canvas;
    }

    private Pair<Integer, Column> choose (int i){
        Column prevColumn = board.getBoard().get(i);
        return new Pair<>(i, prevColumn);
    }
    private void move (int i, AtomicBoolean firstClick){
        if (firstClick.get() && (board.getBoard().get(i).size() != 0) && (t.playerNumber() == board.getBoard().get(i).onTop().ordinal())) {
            firstClick.set(false);
            grid.getChildren().get(i).setStyle("-fx-background-color: #fde910");
            prevI = i;
        } else if (prevI != -1) {
            firstClick.set(true);
            grid.getChildren().get(prevI).setStyle("-fx-background-color: none");
            board.move(prevI, i);
            boolean bottomLine = prevI < 12;
            boolean PrevBottomLine = i < 12;

            grid.add(
                    column(board.getBoard().get(i), bottomLine), columnList.get(i).getKey(),columnList.get(i).getValue()
            );
            grid.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                move(i,firstClick);
            });
            grid.add(column(board.getBoard().get(prevI), PrevBottomLine), columnList.get(prevI).getKey(),columnList.get(prevI).getValue());
            grid.getChildren().get(prevI).setOnMouseClicked(mouseEvent -> {
                move(prevI,firstClick);
            });
            prevI = -1;

        }
    }
}

