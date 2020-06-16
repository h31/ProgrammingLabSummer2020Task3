package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board = new Board();

    @Test
    void isPoint() {
        assertTrue(board.isPoint(3, 3));
        assertFalse(board.isPoint(11, 8));
    }
}

class CellTest {
    Board board = new Board();

    @Test
    void cellNeighbours() {
        assertEquals(8, new Board().new Cell(3, 3).cellNeighbours().size());
        assertEquals(3, new Board().new Cell(9, 9).cellNeighbours().size());
    }

    @Test
    void hasNeighbours() {
        board.boardsCells[3][3].ship = new Ship(3, true, board);
        assertTrue(board.boardsCells[4][3].hasNeighbours());
        assertFalse(board.boardsCells[8][8].hasNeighbours());
    }

    @Test
    void isEmptyCell() {
        board.boardsCells[3][3].ship = new Ship(3, true, board);
        assertFalse(board.boardsCells[2][3].isEmptyCell());
        assertTrue(board.boardsCells[8][8].isEmptyCell());
    }

}
