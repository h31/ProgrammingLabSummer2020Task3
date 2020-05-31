package core;
import view.BattleShipView;
import java.util.Random;

public class ComputerShips {
    private BattleShipView view;
    private final int[] shipLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private int j = 0;
    private Random r = new Random();
    Shoot shoot;
    public ComputerShips(BattleShipView view){
        this.view = view;
        shoot = new Shoot(true, view);
    }

    public void placeComputerShips() {
        while (j < 10) {
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            boolean direction = r.nextBoolean();
            Ship ship = new Ship(shipLength[j], direction, view.enemyBoard);
            if (ship.canPlaceShip(x, y)) {
                j++;
                for (Cell cell : ship.cells) {
                    Cell element = view.enemyBoard.getCell(cell.x, cell.y);
                    element.ship = ship;
                }
                if (j == shipLength.length) {
                     view.reaction.justPhrase(true);
                     view.controller.placePlayerShips();
                }
            }
        }
    }

    public void computerGame() {
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        shoot.move(x, y);
    }

}


