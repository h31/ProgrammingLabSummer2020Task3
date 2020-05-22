package controller;
import core.Board;
import core.Cell;
import core.ComputerShips;
import core.Ship;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardController {
    Scene scene;
    Board boardEnemy;
    Board myBoard;
    Set<Cell> setCells = new HashSet<>();
    ComputerShips computerShips;

    public BoardController(Board boardEnemy, Board myBoard, Scene scene,ComputerShips computerShips) {
        this.boardEnemy = boardEnemy;
        this.myBoard = myBoard;
        this.scene = scene;
        this.computerShips = computerShips;
    }

    public void playerGame() {
            scene.setOnMouseClicked((event -> {
                Cell cell = (Cell) event.getSource();
                if (computerShips.hitShip((int) cell.getX(), (int) cell.getY())) {
                    cell.setFill(Color.RED);
                    if (computerShips.ships.isEmpty()) System.out.println("Поздравляем! Вы победили!");
                    playerGame();
                }
                cell.setFill(Color.BLACK);
                computerGame();
            }));
    }

    public void placePlayerShips() {
      scene.setOnMouseClicked(e -> {
           final int[] shipsLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
           for (int i = 0; i < shipsLength.length; i++) {
               Cell cell = (Cell) e.getSource();
               if (e.isPrimaryButtonDown()) {
                   Ship ship = new Ship((int) cell.getX(), (int) cell.getY(), shipsLength[i], true, myBoard);
                   myBoard.placeShip(ship);
               }
               Ship ship = new Ship((int) cell.getX(), (int) cell.getY(), shipsLength[i], false, myBoard);
               myBoard.placeShip(ship);
           }
       });
    }

    private void computerGame() {
        Random r = new Random();
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        Cell cell = myBoard.getCell(x, y);
        if (!setCells.contains(cell) && cell.getShip()) {
            cell.setFill(Color.RED);
            setCells.add(cell);
            computerGame();
        }
        cell.setFill(Color.BLACK);
        playerGame();
    }

}
