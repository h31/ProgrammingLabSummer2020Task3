package core;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Board extends Parent {
    private static HBox box = new HBox();
    private boolean myBoard;

    public Board(boolean myBoard) {
        this.myBoard = myBoard;
        for (int y = 0; y < 10; y++) {
            VBox row = new VBox();
            for (int x = 0; x < 10; x++) {
                Cell cell = new Cell(x, y,this);
                row.getChildren().add(cell);
            }
            box.getChildren().add(row);
        }
        getChildren().add(box);
    }

    public Cell getCell(int x, int y) {
        return (Cell) ((HBox) box.getChildren().get(y)).getChildren().get(x);
    }


    public boolean placeShip(Ship ship) {// размещение кораблей игрока
        if (canPlaceShip(ship)) {
            for (Cell element : ship.cells) {
                Cell cell = getCell((int) element.getX(), (int) element.getY());
                if (myBoard) {
                    cell.setFill(Color.LIGHTGREEN);
                    cell.setStroke(Color.GREEN);
                }
            }
            return true;
        }
        return false;
    }

    public  boolean canPlaceShip(Ship ship) {// проверяем можно ли разместить корабль, то есть есть не задевает ли соседние корабли
        for (Cell element : ship.cells) {
            int x = (int) element.getX();
            int y = (int) element.getY();
            Cell[] neighbors = getNeighbors(x,y);
            for (Cell cell : neighbors) {
                if (cell.getShip()) return false;
            }

        }
        return true;
    }

    private  Cell[] getNeighbors(int x, int y) {//список из соседних клеток
        Point2D[] points = new Point2D[]{
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };
        List<Cell> neighbors = new ArrayList<Cell>();
        for (Point2D p : points) {
            double pointX = p.getX();
            double pointY = p.getY();
            if (pointX >= 0 && pointX < 10 && pointY >= 0 && pointY < 10) {//проверяем не выходит ли за игровое поле,то есть дейсвтительная ли клетка
                neighbors.add(this.getCell((int) p.getX(), (int) p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);
    }


}
