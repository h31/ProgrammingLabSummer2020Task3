package core;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell extends Rectangle {
    public int x;
    public int y;
    public Board board;
    public boolean wasHit = false;
    public Ship ship;// присваивается корабль,который состоит из этой клетки

    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.board = board;
        this.x = x;
        this.y = y;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean hasNeighbors() {//есть ли в соседних клетках корабли
        List<Cell> neighboursCell = cellNeighbours();
        for (Cell element : neighboursCell) {
            if (element.ship != null) return true;
        }
        return false;
    }

    public List<Cell> cellNeighbours() {// список соседних клеток
        int x = this.x;
        int y = this.y;
        List<Cell> neighboursCell = new ArrayList<Cell>() {{
            if (isPoint(x + 1, y)) add(board.getCell(x + 1, y));
            if (isPoint(x - 1, y)) add(board.getCell(x - 1, y));
            if (isPoint(x, y + 1)) add(board.getCell(x, y + 1));
            if (isPoint(x, y - 1)) add(board.getCell(x, y - 1));
            if (isPoint(x + 1, y + 1)) add(board.getCell(x + 1, y + 1));
            if (isPoint(x - 1, y + 1)) add(board.getCell(x - 1, y + 1));
            if (isPoint(x + 1, y - 1)) add(board.getCell(x + 1, y - 1));
            if (isPoint(x - 1, y - 1)) add(board.getCell(x - 1, y - 1));
        }};
        return neighboursCell;
    }

    public static boolean isPoint(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    } //принадлежит ли клетка игровому полю

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                ship == cell.ship &&
                Objects.equals(board, cell.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, board, ship);
    }
}

