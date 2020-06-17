package model;

import static org.junit.jupiter.api.Assertions.*;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

class FigureTest {
    final JFXPanel fxPanel = new JFXPanel();
    GameField gameField = new GameField();

    Figure shapeTest = new Figure();
    int[][] shape = new int[2][4];

    @Test
    void moveLeftTest() {
        shapeTest.setShape();

        for (int i = 0; i < 4; i++) {
            shape[0][i] = Figure.shape[0][i] - 1;
            shape[1][i] = Figure.shape[1][i];
        }
        shapeTest.moveLeft();

        assertArrayEquals(shape, Figure.shape);
    }

    @Test
    void moveRightTest() {
        shapeTest.setShape();

        for (int i = 0; i < 4; i++) {
            shape[0][i] = Figure.shape[0][i] + 1;
            shape[1][i] = Figure.shape[1][i];
        }
        shapeTest.moveRight();

        assertArrayEquals(shape, Figure.shape);
    }

    @Test
    void findIntersectTest() {
        for (int i = 0; i < 16; i++) {
            gameField.getGameField()[5][i] = GameField.Elements.FigureL;
        }

        shapeTest.setShape();

        shapeTest.moveDown();
        shapeTest.moveDown();
        shapeTest.moveDown();
        shapeTest.moveDown();
        shapeTest.findIntersect();

        assertTrue(shapeTest.findIntersect());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FigureTest that = (FigureTest) o;
        return Objects.equals(fxPanel, that.fxPanel) &&
                Objects.equals(shapeTest, that.shapeTest) &&
                Arrays.equals(shape, that.shape);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fxPanel, shapeTest);
        result = 31 * result + Arrays.hashCode(shape);
        return result;
    }
}