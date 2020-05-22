package core;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public int length;
    public boolean vertical;
    public List<Cell> cells = new ArrayList<Cell>();
    public Board board;

    public Ship(int x, int y, int length, boolean vertical, Board board) {
        this.vertical = vertical;
        this.length = length;
        if (vertical) {
            for (int i = 0; i < length; i++) {
                Cell cell = new Cell(x, y + 1, board);
                cell.setShip();
                cells.add(cell);
            }
        }
        for (int i = 0; i < length; i++) {
            Cell cell = new Cell(x + 1, y, board);
            cell.setShip();
            cells.add(cell);
        }
    }

    public void hit() {
        length--;
    }

    public boolean isAlive() {
        return length > 0;
    }

}


