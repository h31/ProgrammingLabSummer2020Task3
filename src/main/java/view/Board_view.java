package view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Board_view extends Pane {
    private VBox box = new VBox();

    public Board_view() {
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

    public void cellSetAction(EventHandler<MouseEvent> handler) {//передаю клетке обработчика события
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = getCell(i, j);
                cell.setOnMouseClicked(handler);
            }
        }
    }
}




