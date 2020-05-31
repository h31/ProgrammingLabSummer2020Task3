package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private Board board = new Board();
    private Ship ship1 =  new Ship(4,true,board);
    private Ship ship2 =  new Ship(3,false,board);
    @Test
    void canPlaceShip() {
        ship1.canPlaceShip(2,2);
        for(Cell cell: ship1.cells){
            cell.ship = ship1;
        }
        assertTrue(ship2.canPlaceShip(5,5));
        assertFalse(ship2.canPlaceShip(2,2));
    }

    @Test
    void shipsNeighbours() {
        ship2.canPlaceShip(3,3);
        for(Cell cell: ship2.cells){
            cell.ship = ship1;
        }
        assertEquals(12 ,ship2.shipsNeighbours().size());

    }

    @Test
    void injured() {
        ship1.injured();
        ship2.injured();
        assertEquals(3,ship1.length);
        assertEquals(2,ship2.length);
    }

    @Test
    void isDead() {
        Ship e = new Ship(1,true,board);
        e.injured();
        assertTrue(e.isDead());
    }
}