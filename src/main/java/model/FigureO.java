package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

import java.util.Objects;

public class FigureO extends Figure {
    private final Rectangle square = new Rectangle(150, -50, 50, 50);
    private int cellX;
    private int cellY;

    public FigureO() {
        Tetris tetris = new Tetris();
        getChildren().addAll(square, tetris.getCanvas());
        square.setFill(Color.RED);
    }

    public double getSquareX() {
        return square.getX();
    }

    @Override
    public void moveDown() {
        if (!endGame())
            square.setY(square.getY() + getDelta());
    }

    @Override
    public void moveLeft() {
        cellX = (int) (square.getX() / getDelta());
        cellY = (int) (square.getY() / getDelta());

        if (cellY > 0 && cellY + 1 > 0 && cellY + 2 != 20 && cellX != 0) {
            square.setX(square.getX() - getDelta());
            if (square.getX() < 0) {
                square.setX(square.getX() + getDelta());
            } else if (getGameField()[cellY + 1][cellX - 1] != Elements.EmptyCell) {
                square.setX(square.getX() + getDelta());
            } else if (getGameField()[cellY + 2][cellX - 1] != Elements.EmptyCell) {
                square.setX(square.getX() + getDelta());
            } else if (getGameField()[cellY][cellX - 1] != Elements.EmptyCell) {
                square.setX(square.getX() + getDelta());
            }
        }
    }

    @Override
    public void moveRight() {
        cellX = (int) (square.getX() / getDelta());
        cellY = (int) (square.getY() / getDelta());

        if (cellY > 0 && cellY + 1 > 0 && cellY + 2 != 20) {
            square.setX(square.getX() + getDelta());
            if (square.getX() == 375) {
                square.setX(square.getX() - getDelta());
            } else if (getGameField()[cellY][cellX + 2] != Elements.EmptyCell) {
                square.setX(square.getX() - getDelta());
            } else if (getGameField()[cellY + 2][cellX + 2] != Elements.EmptyCell) {
                square.setX(square.getX() - getDelta());
            } else if (getGameField()[cellY + 1][cellX + 2] != Elements.EmptyCell) {
                square.setX(square.getX() - getDelta());
            }
        }
    }

    public boolean intersectsDefaultForm() {
        cellX = (int) (square.getX() / getDelta());
        cellY = (int) (square.getY() / getDelta());
        boolean intersection = false;

        //столкновение O c нижней границей
        if (cellY + 2 == 20) {
            intersection = true;
        }
        //столкновение O c с фигурой
        else if (getGameField()[cellY + 2][cellX] != Elements.EmptyCell) {
            intersection = true;
        } else if (getGameField()[cellY + 2][cellX + 1] != Elements.EmptyCell) {
            intersection = true;
        }
        return intersection;
    }


    @Override
    public boolean stop() {
        boolean figureSet = false;
        cellX = (int) (square.getX() / getDelta());
        cellY = (int) (square.getY() / getDelta());

        if (intersectsDefaultForm() && !endGame()) {

            for (int i = cellX; i < cellX + 2; i++) {
                getGameField()[cellY][i] = GameField.Elements.FigureO;
            }
            for (int i = cellX; i < cellX + 2; i++) {
                getGameField()[cellY + 1][i] = GameField.Elements.FigureO;
            }
            figureSet = true;

            square.setY(-50);
            square.setX(150);
        }
        return figureSet;
    }

    @Override
    public String toString() {
        return "FigureO{" +
                "square=" + square +
                ", cellX=" + cellX +
                ", cellY=" + cellY +
                '}';
    }

}
