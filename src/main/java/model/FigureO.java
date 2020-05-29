package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureO extends Figure {
    private final Rectangle square = new Rectangle(150, -50, 50, 50);
    private int delta = getDelta();
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
            square.setY(square.getY() + delta);
    }

    @Override
    public void moveLeft() {
        cellX = (int) (square.getX() / delta);
        cellY = (int) (square.getY() / delta);

        if (cellY > 0 && cellY + 1 > 0 && cellY + 2 != 20 && cellX != 0) {
            square.setX(square.getX() - delta);
            if (square.getX() < 0) {
                square.setX(square.getX() + delta);
            } else if (getGameField()[cellY + 1][cellX - 1] != Elements.EmptyCell) {
                square.setX(square.getX() + delta);
            } else if (getGameField()[cellY + 2][cellX - 1] != Elements.EmptyCell) {
                square.setX(square.getX() + delta);
            } else if (getGameField()[cellY][cellX - 1] != Elements.EmptyCell) {
                square.setX(square.getX() + delta);
            }
        }
    }

    @Override
    public void moveRight() {
        cellX = (int) (square.getX() / delta);
        cellY = (int) (square.getY() / delta);

        if (cellY > 0 && cellY + 1 > 0 && cellY + 2 != 20) {
            square.setX(square.getX() + delta);
            if (square.getX() == 375) {
                square.setX(square.getX() - delta);
            } else if (getGameField()[cellY][cellX + 2] != Elements.EmptyCell) {
                square.setX(square.getX() - delta);
            } else if (getGameField()[cellY + 2][cellX + 2] != Elements.EmptyCell) {
                square.setX(square.getX() - delta);
            } else if (getGameField()[cellY + 1][cellX + 2] != Elements.EmptyCell) {
                square.setX(square.getX() - delta);
            }
        }
    }

    public boolean intersectsDefaultForm() {
        cellX = (int) (square.getX() / delta);
        cellY = (int) (square.getY() / delta);
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
        cellX = (int) (square.getX() / delta);
        cellY = (int) (square.getY() / delta);

        if (intersectsDefaultForm() && !endGame()) {
            delta = 0;

            for (int i = cellX; i < cellX + 2; i++) {
                getGameField()[cellY][i] = GameField.Elements.FigureO;
            }
            for (int i = cellX; i < cellX + 2; i++) {
                getGameField()[cellY + 1][i] = GameField.Elements.FigureO;
            }
            figureSet = true;

            square.setY(-50);
            square.setX(150);
            delta = 25;
        }
        return figureSet;
    }
}
