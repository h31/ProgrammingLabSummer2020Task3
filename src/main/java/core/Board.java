package core;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public Cell[][] boardsCells = new Cell[10][10];
    public int counter_ships = 10;
    public final int[] shipLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    public Board() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardsCells[i][j] = new Cell(i, j);
            }
        }
    }

    public class Cell {
        public boolean wasHit = false;
        public Ship ship;
        public int x;
        public int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public List<Board.Cell> cellNeighbours() {// список соседних клеток
            List<Board.Cell> neighboursCell = new ArrayList<Cell>() {{
                if (isPoint(x + 1, y)) add(new Cell(x + 1, y));
                if (isPoint(x - 1, y)) add(new Cell(x - 1, y));
                if (isPoint(x, y + 1)) add(new Cell(x, y + 1));
                if (isPoint(x, y - 1)) add(new Cell(x, y - 1));
                if (isPoint(x + 1, y + 1)) add(new Cell(x + 1, y + 1));
                if (isPoint(x - 1, y + 1)) add(new Cell(x - 1, y + 1));
                if (isPoint(x + 1, y - 1)) add(new Cell(x + 1, y - 1));
                if (isPoint(x - 1, y - 1)) add(new Cell(x - 1, y - 1));
            }};
            return neighboursCell;
        }

        public boolean hasNeighbours() {//есть ли в соседних клетках корабли
            List<Board.Cell> neighboursCell = cellNeighbours();
            for (Board.Cell element : neighboursCell) {
                if (boardsCells[element.x][element.y].ship != null) return true;
            }
            return false;
        }

        public boolean isEmptyCell() {
            if (!Board.isPoint(x, y)) return false;
            Board.Cell cell = boardsCells[x][y];
            if (cell.ship != null || cell.hasNeighbours()) return false;
            return true;
        }
    }

    public static boolean isPoint(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

}
