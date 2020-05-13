package core;
import java.util.List;
import java.util.Random;

public class ComputerShips {
    final int[] shipsLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private  Random r = new Random();
    public    List<Ship>  ships;// массив с кораблями компьютера
    public boolean endPlaceComputerShips;// когда завершаем заполнение массива,становится истинно и после этого игрок размещает свои корабли
    private  void placeComputersShips(Board board){// размещение кораблей компьютера(заполнение массива с кораблями)
        int i = 0;
        while (i < 10) {
            boolean direction = r.nextBoolean();
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            Ship example = new Ship(x, y,shipsLength [i], direction,board);
            if(board.canPlaceShip(example)){
            ships.add(example);
            i++;
            }
        }
        this.endPlaceComputerShips = true;
    }
    public void hitShip(int x, int y, Board board) {
        for (Ship ship: ships){
            for(Cell element: ship.cells ){
                if(element.checkHit(x,y))
            break;
            }
        }
    }



}
