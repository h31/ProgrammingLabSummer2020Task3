package core;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Cell extends Rectangle {

    private int x;
    private int y;
    private Board board;
    public boolean ship;// показывает,является ли эта клетка частью корабля

    public Cell(int x, int y, Board board) {
        super(30,30);
        this.board = board;
        this.x = x;
        this.y = y;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }
     public void setShip(){
        this.ship = true;
     }

     public boolean getShip(){
        return this.ship;
     }

    public  boolean checkHit(int x, int y) {
        if (this.x == x && this.y == y) {
            setFill(Color.RED);
            return true;
        }
        setFill(Color.BLACK);
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                ship == cell.ship;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, ship);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", board=" + board +
                ", ship=" + ship +
                '}';
    }
}

