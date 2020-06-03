package model;

import controller.GameCycle;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;
import view.Tetris;


import static org.junit.jupiter.api.Assertions.*;

class TetrisTest {
    JFXPanel fxPanel = new JFXPanel();
    Tetris tetris = new Tetris();
    GameCycle gameCycle = new GameCycle();
    GameField gameField = new GameField();

    FigureL figureL = new FigureL();
    FigureO figureO = new FigureO();
    FigureI figureI = new FigureI();
    FigureZ figureZ = new FigureZ();
    FigureT figureT = new FigureT();

    /**
     * Тесты на движение фигур влево
     */
    @Test
    void moveLeftFigureL() {
        figureL.moveDown();
        figureL.moveDown();
        figureL.moveDown();

        figureL.moveLeft();

        assertEquals(175, figureL.getColumnX());
        assertEquals(125, figureL.getRowX());
    }
    @Test
    void moveLeftFigureO() {
        figureO.moveDown();
        figureO.moveDown();
        figureO.moveDown();

        figureO.moveLeft();

        assertEquals(125, figureO.getSquareX());
    }

    @Test
    void moveLeftFigureI() {
        figureI.moveDown();
        figureI.moveDown();
        figureI.moveDown();

        figureI.moveLeft();

        assertEquals(125, figureI.getRowX());
    }

    @Test
    void moveLeftFigureT() {
        figureT.moveDown();
        figureT.moveDown();
        figureT.moveDown();

        figureT.moveLeft();

        assertEquals(125, figureT.getRowX());
        assertEquals(150, figureT.getColumnX());
    }

    @Test
    void moveLeftFigureZ() {
        figureZ.moveDown();
        figureZ.moveDown();
        figureZ.moveDown();

        figureZ.moveLeft();

        assertEquals(125, figureZ.getUpRowX());
        assertEquals(100, figureZ.getDownRowX());
    }

    /**
     * Тесты на движение фигур вправо
     */
    @Test
    void moveRightFigureL() {
        figureL.moveDown();
        figureL.moveDown();
        figureL.moveDown();

        figureL.moveRight();

        assertEquals(225, figureL.getColumnX());
        assertEquals(175, figureL.getRowX());
    }

    @Test
    void moveRightFigureO() {
        figureO.moveDown();
        figureO.moveDown();
        figureO.moveDown();

        figureO.moveRight();

        assertEquals(175, figureO.getSquareX());
    }

    @Test
    void moveRightFigureI() {
        figureI.moveDown();
        figureI.moveDown();
        figureI.moveDown();

        figureI.moveRight();

        assertEquals(175, figureI.getRowX());
    }

    @Test
    void moveRightFigureT() {
        figureT.moveDown();
        figureT.moveDown();
        figureT.moveDown();

        figureT.moveRight();

        assertEquals(175, figureT.getRowX());
        assertEquals(200, figureT.getColumnX());
    }

    @Test
    void moveRightFigureZ() {
        figureZ.moveDown();
        figureZ.moveDown();
        figureZ.moveDown();

        figureZ.moveRight();

        assertEquals(175, figureZ.getUpRowX());
        assertEquals(150, figureZ.getDownRowX());
    }

    /**
     * Тесты на останавку фигур
     */
    @Test
    void stopFigure() {
        gameField.getGameField()[3][6] = GameField.Elements.FigureI;
        gameField.getGameField()[3][7] = GameField.Elements.FigureI;
        gameField.getGameField()[3][8] = GameField.Elements.FigureI;
        gameField.getGameField()[3][9] = GameField.Elements.FigureI;

        figureI.moveDown();
        figureI.moveDown();
        figureI.moveDown();
        figureI.moveDown();

        assertTrue(figureI.intersectsDefaultForm());

        figureZ.moveDown();
        figureZ.moveDown();
        figureZ.moveDown();

        assertTrue(figureZ.intersectsDefaultForm());

        figureL.moveDown();
        figureL.moveDown();
        figureL.moveDown();

        assertTrue(figureL.intersectsDefaultForm());

        figureT.moveDown();
        figureT.moveDown();
        figureT.moveDown();
        figureT.moveDown();

        assertTrue(figureT.intersectsDefaultForm());

        figureO.moveDown();
        figureO.moveDown();
        figureO.moveDown();

        assertTrue(figureO.intersectsDefaultForm());
    }

    /**
     * Закончилась ли игра
     */
    @Test
    void endGame() {
        gameField.getGameField()[1][6] = GameField.Elements.FigureI;

        assertTrue(gameField.endGame());
    }

    @Test
    void clearRow() {
        boolean clear = true;
        for (int i = 0; i < 16; i++) {
            gameField.getGameField()[19][i] = GameField.Elements.FigureI;

        }
        for (int i = 0; i < 16; i++) {
            gameField.clearRow();
            if (gameField.getGameField()[19][i] != GameField.Elements.EmptyCell) {
                clear = false;
            }
        }
        assertTrue(clear);
    }
}