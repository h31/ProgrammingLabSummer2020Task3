package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureO extends Figure {
    private final Rectangle square = new Rectangle(150, -50, 50, 50);

    private int delta = 25;

    int cellX;
    int cellY;

    public FigureO() {
        Tetris tetris = new Tetris();
        getChildren().addAll(square, tetris.getCanvas());
        square.setFill(Color.RED);
    }

    public void fillFigure() {
        //   getGameField()[0][4] = Elements.FigureO;
        //getGameField()[0][5] = Elements.FigureO;
        // getGameField()[1][4] = Elements.FigureO;
        //  getGameField()[1][5] = Elements.FigureO;
    }


    @Override
    public void moveDown() {


        square.setY(square.getY() + delta);
    }

    @Override
    public void moveLeft() {//фикс верхней
        if (cellY > 0 && cellY + 1 > 0 && cellY + 2 != 20) {
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
        boolean intersection = false;

        //столкновение O c нижней границей
        if (cellY + 2 == 20) {
            intersection = true;
        }
        //столкновение O c O
        else if (getGameField()[cellY + 2][cellX] == Elements.FigureO) {
            intersection = true;
        } else if (getGameField()[cellY + 2][cellX + 1] == Elements.FigureO) {
            intersection = true;
        }
        //столкновение O c I
        else if (getGameField()[cellY + 2][cellX] == Elements.FigureI) {
            intersection = true;
        } else if (getGameField()[cellY + 2][cellX + 1] == Elements.FigureI) {
            intersection = true;
        }
        //столкновение O с T
        else if (getGameField()[cellY + 2][cellX] == Elements.FigureT) {
            intersection = true;
        } else if (getGameField()[cellY + 2][cellX + 1] == Elements.FigureT) {
            intersection = true;
        }
        //столкновение O c L
        else if (getGameField()[cellY + 2][cellX] == Elements.FigureL) {
            intersection = true;
        } else if (getGameField()[cellY + 2][cellX + 1] == Elements.FigureL) {
            intersection = true;
        }
        //столкновение O c Z
        else if (getGameField()[cellY + 2][cellX] == Elements.FigureZ) {
            intersection = true;
        } else if (getGameField()[cellY + 2][cellX + 1] == Elements.FigureZ) {
            intersection = true;
        }
        return intersection;
    }


    @Override
    public boolean stop() {
        boolean figureSet = false;
        cellX = (int) (square.getX() / delta);
        cellY = (int) (square.getY() / delta);

        if (intersectsDefaultForm()) {
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
