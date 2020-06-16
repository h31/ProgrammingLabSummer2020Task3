package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Board board = new Board();
    Ship ship= new Ship(3,true,board);

    @Test
    void canPlaceShip(){
        assertFalse(ship.canPlaceShip(9,10));
        assertTrue(ship.canPlaceShip(2,2));
    }

    @Test
    void canPlaceShipToCell() {
        board.boardsCells[3][3].ship = new Ship(4,true, board);
        assertFalse(ship.canPlaceShip(3,3));
        assertTrue(ship.canPlaceShip(5,5));
    }

    @Test
    void cellsAroundShip() {
        ship.canPlaceShip(3, 3);
        ship.cells.forEach(x -> board.boardsCells[x.x][x.y].wasHit = true);
        assertEquals(12,ship.cellsAroundShip().size());
    }
    @Test
    void injured() {
        ship.injured();
        assertEquals(2,ship.length);
    }

    @Test
    void isDead() {
        Ship example = new Ship(1,true,board);
        example.injured();
        assertTrue(example.isDead());
    }
}