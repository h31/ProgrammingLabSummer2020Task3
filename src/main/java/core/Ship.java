package core;
import java.util.*;
import java.util.List;
public class Ship {
    public int length;
    public boolean vertical;
    public List <Cell> cells = new ArrayList<>();
    Board board;

    public Ship(int length, boolean vertical, Board board) {
        this.vertical = vertical;
        this.length = length;
        this.board = board;
    }
    public boolean canPlaceShip(int x, int y) {
        if (this.vertical){
            for (int j = y; j < y + length; j++){
                if (!Cell.isPoint(x, j)) return false;
                Cell cell = board.getCell(x,j);
                if (cell.ship != null || cell.hasNeighbors()) return false;
                cells.add(cell);
            }
        } else for (int i = x; i < x + length; i++) {
            if (!Cell.isPoint(i, y)) return false;
            Cell cell = board.getCell(i, y);
            if (cell.ship != null || cell.hasNeighbors()) return false;
            cells.add(cell);
        }
        return true;
    }
    public List<Cell> shipsNeighbours(){
        List<Cell> shipsNeighboursCell = new ArrayList<>();
        for(Cell cell: cells){
            List<Cell> neighboursCell = cell.cellNeighbours();
            for(Cell e: neighboursCell){
                if(e.ship == null && !e.wasHit){
                    board.getCell(e.x,e.y).wasHit = true;
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


