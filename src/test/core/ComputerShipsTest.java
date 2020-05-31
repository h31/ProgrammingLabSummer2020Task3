package core;

import org.junit.jupiter.api.Test;
import view.BattleShipView;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ComputerShipsTest {
    BattleShipView view = new BattleShipView();
    @Test
    void placeComputerShips() {
    }

    public int helper(){
        Ship ship = new Ship(4,true, view.enemyBoard);
        if(ship.canPlaceShip(3,3)){
            for (Cell cell : ship.cells) {
                Cell element = view.enemyBoard.getCell(cell.x, cell.y);
                element.ship = ship;
            }
        }
        ship.injured();
        return ship.length;
    }

    @Test
    void shoot() {
        assertEquals(3, helper());

    }
}