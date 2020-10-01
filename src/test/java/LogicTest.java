import model.Logic;

import java.util.Arrays;

import static model.Logic.rotate;
import static org.junit.jupiter.api.Assertions.*;

class LogicTest {
    int[][] testWin = new int[][]{
            {0, 1024, 0, 0},
            {0, 1024, 8, 2},
            {2, 16, 0, 0},
            {0, 0, 8, 0}
    };
    int[][] testLogic = new int[][]{
            {0, 2, 2, 0},
            {0, 0, 8, 2},
            {2, 16, 0, 0},
            {0, 0, 8, 0}
    };

    @org.junit.jupiter.api.Test
    void isWin() {
        Logic logic = new Logic(testWin);
        logic.move(testWin, "Up");
        assertTrue(logic.isWin(testWin));
    }

    @org.junit.jupiter.api.Test
    void up() {
        int[][] testBoard = new int[][]{
                {2, 2, 2, 2},
                {0, 16, 16, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Logic logic = new Logic(testLogic);
        logic.move(testLogic, "Up");
        assertTrue(Arrays.deepEquals(logic.getCells(), testBoard));
    }

    @org.junit.jupiter.api.Test
    void down() {
        int[][] testBoard = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {2, 16, 16, 2}
        };
        Logic logic = new Logic(testLogic);
        testLogic = rotate(testLogic);
        testLogic = rotate(testLogic);
        logic.move(testLogic, "Down");
        testLogic = rotate(testLogic);
        testLogic = rotate(testLogic);
        assertTrue(Arrays.deepEquals(testLogic, testBoard));
    }

    @org.junit.jupiter.api.Test
    void right() {
        int[][] testBoard = new int[][]{
                {0, 0, 2, 2},
                {0, 0, 8, 2},
                {0, 0, 2, 16},
                {0, 0, 0, 8}
        };
        Logic logic = new Logic(testLogic);
        testLogic = rotate(testLogic);
        testLogic = rotate(testLogic);
        logic.move(testLogic, "Right");
        testLogic = rotate(testLogic);
        testLogic = rotate(testLogic);
        assertTrue(Arrays.deepEquals(testLogic, testBoard));
    }

    @org.junit.jupiter.api.Test
    void left() {
        int[][] testBoard = new int[][]{
                {2, 2, 0, 0},
                {8, 2, 0, 0},
                {2, 16, 0, 0},
                {8, 0, 0, 0}
        };
        Logic logic = new Logic(testLogic);
        logic.move(testLogic, "Left");
        assertTrue(Arrays.deepEquals(logic.getCells(), testBoard));
    }
}