package core;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class Board extends Pane {
    private VBox box = new VBox();
    public Board() {
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell cell = new Cell(x, y, this);
                row.getChildren().add(cell);
            }
            box.getChildren().add(row);
        }
        getChildren().add(box);
    }

    public Cell getCell(int x, int y) {
        return (Cell) ((HBox) box.getChildren().get(y)).getChildren().get(x);
    }

    public void cellSetAction(EventHandler<MouseEvent> handler) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = getCell(i, j);
                cell.setOnMouseClicked(handler);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(box, board.box);
    }

    @Override
    public int hashCode() {
        return Objects.hash(box);
    }
}




