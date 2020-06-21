package project.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void getX() {
        Tile tile = new Tile(3, 4);
        assertEquals(3, tile.getX());
    }

    @Test
    void getY() {
        Tile tile = new Tile(3, 4);
        assertEquals(4, tile.getY());
    }

    @Test
    void getBombsAround() {
        Tile tile = new Tile(3, 3);
        assertEquals(0, tile.getBombsAround());
        tile.setBombsAround(3);
        assertEquals(3, tile.getBombsAround());
    }

    @Test
    void isOpened() {
        Tile tile = new Tile(1, 1);
        assertFalse(tile.isOpened());
        tile.setOpened(true);
        assertTrue(tile.isOpened());
    }

    @Test
    void isMarked() {
        Tile tile = new Tile(1, 1);
        assertFalse(tile.isMarked());
        tile.setFlag(true);
        assertTrue(tile.isMarked());
    }

    @Test
    void hasBomb() {
        Tile tile = new Tile(1, 1);
        assertFalse(tile.hasBomb());
        tile.setBomb(true);
        assertTrue(tile.hasBomb());
    }

    @Test
    void testEquals() {
        Tile tile1 = new Tile(1, 1);
        Tile tile2 = new Tile(1, 1);
        Tile tile3 = new Tile(2, 1);
        assertEquals(tile1, tile2);
        assertNotEquals(tile1, tile3);
        tile2.setOpened(true);
        assertNotEquals(tile1, tile2);
    }

    @Test
    void testHashCode() {
        Tile tile1 = new Tile(1, 1);
        Tile tile2 = new Tile(1, 1);
        Tile tile3 = new Tile(2, 1);
        assertEquals(tile1.hashCode(), tile2.hashCode());
        assertNotEquals(tile1.hashCode(), tile3.hashCode());
        tile2.setOpened(true);
        assertNotEquals(tile1.hashCode(), tile2.hashCode());
    }
}