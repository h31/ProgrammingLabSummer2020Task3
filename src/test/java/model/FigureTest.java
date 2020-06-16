package model;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;
import view.Tetris;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {
    JFXPanel fxPanel = new JFXPanel();
    Tetris tetris = new Tetris();
    GameField gameField = new GameField();
    Figure shapeTest = new Figure();
    int[][] shape = new int[2][4];

    @Test
    void moveLeft() {
        shapeTest.setShape();
        shape = new int[][]{{2, 2, 3, 3}, {1, 2, 2, 3}};
        shapeTest.moveLeft();

      //  assertEquals(shape, shapeTest);
    }

    @Test
    void moveRight() {
    }

    @Test
    void findIntersect() {
    }

    @Test
    void turningShape() {
    }
}