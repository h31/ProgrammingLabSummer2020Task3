package view;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.Objects;

public class Cell extends Rectangle {
    public int x;
    public int y;
    public Board_view board;

    public Cell(int x, int y, Board_view board) {
        super(30, 30);
        this.board = board;
        this.x = x;
        this.y = y;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                Objects.equals(board, cell.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, board);
    }
}

