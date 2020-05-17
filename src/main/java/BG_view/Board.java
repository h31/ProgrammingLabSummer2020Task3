package BG_view;

import BG_control.Turn;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;


public class Board {
    public final  static  int TILE_WIDTH = 55;
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
    private int prevI = -1;
    private Turn t = new Turn();

    Board(){ }

    Button gButton(GridPane board) {
        Button btn = new Button();
        btn.setText(" New\nGame");
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction(event -> {
            btn.setText("Next\nTurn");
            t.startTurn(board);
        });
        return btn;
    }

    GridPane board(Turn t) {
        grid = grid();
        boolean first = true;
        for (int i = 0; i < 12; i++) {
            Pair<Integer, Integer> colID = columnList.get(i);
            grid.add(new ColumnView(board.getBoard().get(i), true,false), colID.getKey(), colID.getValue());
            grid.getChildren().get(i).setStyle("-fx-background-color: #b87333");
        }
        for (int i = 12; i < 24; i++) {
            Pair<Integer, Integer> colID = columnList.get(i);
            grid.add(new ColumnView(board.getBoard().get(i), false,false), colID.getKey(), colID.getValue());
            grid.getChildren().get(i).setStyle("-fx-background-color: #b87333");
        }
        grid.add(new ColumnView(board.getBlackBlot(), false,true), columnList.get(24).getKey(), columnList.get(24).getValue());
        grid.getChildren().get(24).setStyle("-fx-background-color: #bdbdbd");
        grid.add(new ColumnView(board.getWhiteBlot(), true,true), columnList.get(25).getKey(), columnList.get(25).getValue());
        grid.getChildren().get(25).setStyle("-fx-background-color: #bdbdbdbd");
        grid.add(gButton(grid), 13, 1);


        return grid;

    }

    private GridPane grid() {

        ColumnConstraints column = new ColumnConstraints(TILE_WIDTH);

        for (int i = 0; i < 14; i++) {
            grid.getColumnConstraints().add(column);
        }

        RowConstraints row = new RowConstraints(TILE_WIDTH*6);
        row.setPercentHeight(50);

        grid.getRowConstraints().add(row);
        grid.getRowConstraints().add(row);

        return grid;
    }



}

