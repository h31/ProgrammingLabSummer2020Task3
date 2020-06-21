package project.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void getRowNumber() {
        Field field = new Field(6, 5, 7);
        assertEquals(6, field.getRowNumber());
    }

    @Test
    void getTilesInRow() {
        Field field = new Field(6, 5, 7);
        assertEquals(5, field.getTilesInRow());
    }

    @Test
    void getBombs() {
        Field field = new Field(6, 5, 7);
        assertEquals(7, field.getBombs());
    }

    @Test
    void getFlagsLeft() {
        Field field = new Field(4, 5, 7);
        field.initialiseField(field.getTile(1, 1));
        assertEquals(7, field.getFlagsLeft());
        field.markTile(field.getTile(1, 1));
        assertEquals(6, field.getFlagsLeft());
    }

    @Test
    void isGameOver() {
        Field field = new Field(4, 4, 3);
        assertFalse(field.isGameOver());
        field.setGameOver(true);
        assertTrue(field.isGameOver());
    }

    @Test
    void isLost() {
        Field field = new Field(5, 6, 7);
        assertFalse(field.isLost());
        field.setLost(true);
        assertTrue(field.isLost());
    }

    @Test
    void isFieldInitialized() {
        Field field = new Field(3, 3, 1);
        assertFalse(field.isFieldInitialized());
        field.initialiseField(field.getTile(0, 0));
        assertTrue(field.isFieldInitialized());
    }

    @Test
    void getGrid() {
        Field field = new Field(1, 3, 0);
        Tile tile1 = new Tile(0, 0);
        Tile tile2 = new Tile(1, 0);
        Tile tile3 = new Tile(2, 0);
        field.initialiseField(new Tile(0, 0));

        Tile[][] expected = new Tile[][] {{tile1}, {tile2}, {tile3}};
        assertTrue(Arrays.deepEquals(expected, field.getGrid()));
    }

    @Test
    void winCheck() {
        Field field = new Field(3, 3, 1);
        field.initialiseField(field.getGrid()[1][1]);
        assertFalse(field.winCheck());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!field.getGrid()[i][j].hasBomb()) {
                    field.openTile(field.getTile(i, j));
                }
            }
        }
        assertTrue(field.winCheck());
    }

    @Test
    void initialiseField() {
        Field field = new Field(4, 4, 3);
        Field field1 = new Field(4, 4, 3);
        assertEquals(field, field1);
        field1.initialiseField(field1.getTile(2, 3));
        assertNotEquals(field, field1);
    }

    @Test
    void openTile() {
        Field field = new Field(3, 3, 3);
        assertFalse(field.getTile(1, 1).isOpened());
        field.openTile(field.getTile(1, 1));
        assertTrue(field.getTile(1, 1).isOpened());
        assertTrue(field.isFieldInitialized());
    }

    @Test
    void markTile() {
        Field field = new Field(3, 3, 3);
        assertFalse(field.getTile(1, 1).isMarked());

        field.markTile(field.getTile(1, 1));
        assertFalse(field.getTile(1, 1).isMarked());

        field.initialiseField(field.getTile(1, 1));
        field.markTile(field.getTile(1, 1));
        assertTrue(field.getTile(1, 1).isMarked());
    }

    @Test
    void getTile() {
        Field field = new Field(3, 3, 0);
        Tile tile = new Tile(1, 2);
        assertEquals(tile, field.getTile(1, 2));
    }

    @Test
    void testEquals() {
        Field field = new Field(3, 3, 0);
        Field field1 = new Field(3, 3, 0);
        Field field2 = new Field(2, 1, 1);
        field.initialiseField(new Tile(1, 1));
        field1.initialiseField(new Tile(1, 1));
        assertEquals(field, field1);
        assertNotEquals(field, field2);

        field1.openTile(new Tile(1, 1));
        assertNotEquals(field, field1);
    }

    @Test
    void testHashCode() {
        Field field = new Field(3, 3, 0);
        Field field1 = new Field(3, 3, 0);
        Field field2 = new Field(2, 1, 1);
        field.initialiseField(new Tile(1, 1));
        field1.initialiseField(new Tile(1, 1));
        assertEquals(field.hashCode(), field1.hashCode());
        assertNotEquals(field.hashCode(), field2.hashCode());

        field1.openTile(new Tile(1, 1));
        assertNotEquals(field.hashCode(), field1.hashCode());
    }
}