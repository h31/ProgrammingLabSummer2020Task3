package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerShips {
    Board board;
    final int[] shipsLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private Random r = new Random();
    public List<Ship> ships = new ArrayList<>();// массив с кораблями компьютера
    public boolean endPlaceComputerShips;// когда завершаем заполнение массива,становится истинно и после этого игрок размещает свои корабли

    public ComputerShips(Board board) {
        this.board = board;
    }


    public void placeComputersShips() {// размещение кораблей компьютера(заполнение массива с кораблями)
        int i = 0;
        while (i < 10) {
            boolean direction = r.nextBoolean();
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            Ship example = new Ship(x, y, shipsLength[i], direction, board);
            if (board.canPlaceShip(example)) {
                ships.add(example);
                i++;
            }
        }
        this.endPlaceComputerShips = true;
    }

    public boolean hitShip(int x, int y) {
        for (Ship ship : ships) {
            for (Cell element : ship.cells) {
                if (element.checkHit(x, y))
                    return true;
                break;
            }
        }
        return false;
    }


}
