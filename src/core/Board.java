package core;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Board extends Parent {
    private  VBox box = new VBox();
    private boolean myBoard;
    EventHandler<MouseEvent> handler;

    public Board(boolean isMyBoard) {
        this.myBoard = isMyBoard;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell cell = new Cell(x, y, this);
                cell.setOnMouseClicked(handler);
                row.getChildren().add(cell);
            }
            box.getChildren().add(row);
        }
        getChildren().add(box);
    }
    public void  setAction(EventHandler<MouseEvent> e){
        this.handler = e;
        }

    public Cell getCell(int x, int y) {
        return (Cell) ((HBox) box.getChildren().get(y)).getChildren().get(x);
    }


    public void placeShip(Ship ship) {// размещение кораблей игрока
        if (canPlaceShip(ship)) {
            for (Cell element : ship.cells) {
                Cell cell = getCell((int) element.getX(), (int) element.getY());
                cell.setFill(Color.LIGHTGREEN);
                cell.setStroke(Color.GREEN);
            }
        }
    }

    public boolean canPlaceShip(Ship ship) {// проверяем можно ли разместить корабль, то есть есть не задевает ли соседние корабли
        for (Cell element : ship.cells) {
            if (hasNeighbors(element)) return false;
        }
        return true;
    }

    private boolean hasNeighbors(Cell cell) {//есть ли в соседних клетках корабли
        int x = (int) cell.getX();
        int y = (int) cell.getY();
        boolean b = checkY(x,y);
        switch (x) {
            case 0:
                return b || getCell(x + 1, y).ship;
            case 10:
                return b || getCell(x - 1, y).ship;
            default:
                return b || getCell(x - 1, y).ship || getCell(x + 1, y).ship;
        }
    }


    private boolean checkY(int x, int y) {
        switch (y) {
            case 1:
                return getCell(x, y + 1).ship;
            case 10:
                return getCell(x, y - 1).ship;
            default:
                return getCell(x, y + 1).ship && getCell(x, y - 1).ship;
        }
    }
}



