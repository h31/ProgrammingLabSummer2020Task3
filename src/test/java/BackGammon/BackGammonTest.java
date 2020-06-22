package BackGammon;

import BG_control.Turn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BackGammonTest {

    private BG_model.Board board = new BG_model.Board();

    @Test
    void modelTest() {
        assertEquals(26, board.getBoard().size());
        assertEquals(0, board.get(1).size());
        assertFalse(board.move(1,0));
    }

    @Test
    void turnTest(){
        Turn t = new Turn();
        assertEquals(-1, t.playerNumber());
        t.setTurnCount(1);
        assertEquals(0, t.playerNumber());
        t.setTurnCount(10);
        assertEquals(1, t.playerNumber());
    }
}
