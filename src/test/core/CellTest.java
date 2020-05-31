package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Board first = new Board();

    @Test
    void hasNeighbors() {
        assertFalse(() -> first.getCell(3, 3).hasNeighbors());
        assertTrue(() -> {
            first.getCell(3, 3).ship = new Ship(3, true, first);
            return first.getCell(3, 4).hasNeighbors();
        });
    }

    @Test
    void cellNeighbours() {
        assertEquals(8, first.getCell(3, 3).cellNeighbours().size());
    }

    @Test
    void isPoint() {
        assertTrue(() -> Cell.isPoint(3, 3));
        assertFalse(() -> Cell.isPoint(11, 9));
    }
}