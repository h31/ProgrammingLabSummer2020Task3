package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void getCell() {
        Board board = new Board();
        Cell cell = new Cell(3,3,board);
        assertTrue(cell.equals(board.getCell(3,3)));
    }
}