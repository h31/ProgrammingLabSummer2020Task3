package core;

import java.util.*;
import java.util.List;

public class Ship {
    public int length;
    public boolean vertical;
    public List<Board.Cell> cells = new ArrayList<>();
    public Board board;

    public Ship(int length, boolean vertical, Board board) {
        this.vertical = vertical;
        this.length = length;
        this.board = board;
    }

    public boolean canPlaceShip(int x, int y) {
        if (this.vertical) {
            for (int j = y; j < y + length; j++) {
                if (!canPlaceShipToCell(x, j)) return false;
            }
        } else for (int i = x; i < x + length; i++) {
            if (!canPlaceShipToCell(i, y)) return false;
        }
        return true;
    }

    public boolean canPlaceShipToCell(int x, int y) {
        Board.Cell cell = board.new Cell(x, y);
        if (!cell.isEmptyCell()) return false;
        cells.add(cell);
        return true;
    }

    public List<Board.Cell> cellsAroundShip() {
        List<Board.Cell> shipsNeighboursCell = new ArrayList<>();
        for (Board.Cell cell : cells) {
            for (Board.Cell e : cell.cellNeighbours()) {
                if (board.boardsCells[e.x][e.y].ship == null && !board.boardsCells[e.x][e.y].wasHit) {
                    board.boardsCells[e.x][e.y].wasHit = true;
                    shipsNeighboursCell.add(e);
                }
            }
        }
        return shipsNeighboursCell;
    }

    public void injured() {
        length--;
    }

    public boolean isDead() {
        return length == 0;
    }

}


