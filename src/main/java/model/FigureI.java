package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureI extends Figure {
    private final GameField field = new GameField();
    private final Rectangle figureI = new Rectangle(150, -25, field.getWidthCell() * 4, field.getHeightCell());
    private int delta = 25;
    private int cellX;
    private int cellY;
    private boolean changed;

    public FigureI() {
        //задание падающей фигуры цвета
        figureI.setFill(Color.GREENYELLOW);

        //добавление в граф сцены
        Tetris tetris = new Tetris();
        getChildren().addAll(tetris.getCanvas(), figureI);
    }

    /**
     * Проверка возможности движения влево
     * и само движение
     */
    @Override
    public void moveLeft() {
        if (cellY > 0 && cellY != 20) {
            figureI.setX(figureI.getX() - delta);

            if (!changed) {
                if (figureI.getX() < 0) {
                    figureI.setX(figureI.getX() + delta);
                } else if (getGameField()[cellY][cellX - 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() + delta);
                }
            } else {
                if (figureI.getX() < 0) {
                    figureI.setX(figureI.getX() + delta);
                } else if (getGameField()[cellY][cellX - 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() + delta);
                } else if (getGameField()[cellY + 1][cellX - 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() + delta);
                } else if (getGameField()[cellY + 2][cellX - 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() + delta);
                } else if (getGameField()[cellY + 3][cellX - 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() + delta);
                } else if (getGameField()[cellY + 4][cellX - 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() + delta);
                }
            }
        }
    }

    /**
     * Проверка возможности движения вправо
     * и само движение
     */
    @Override
    public void moveRight() {
        if (cellY > 0 && cellY + 4 != 20) {
            figureI.setX(figureI.getX() + delta);

            if (!changed) {
                if (figureI.getX() == 325) {
                    figureI.setX(figureI.getX() - delta);
                } else if (getGameField()[cellY][cellX + 4] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() - delta);
                }
            } else {
                if (figureI.getX() == 400) {
                    figureI.setX(figureI.getX() - delta);
                } else if (getGameField()[cellY][cellX + 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() - delta);
                } else if (getGameField()[cellY + 1][cellX + 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() - delta);
                } else if (getGameField()[cellY + 2][cellX + 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() - delta);
                } else if (getGameField()[cellY + 3][cellX + 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() - delta);
                } else if (getGameField()[cellY + 4][cellX + 1] != Elements.EmptyCell) {
                    figureI.setX(figureI.getX() - delta);
                }
            }
        }
    }

    /**
     * Движение фигуры вниз
     */
    @Override
    public void moveDown() {
        figureI.setY(figureI.getY() + delta);
    }

    /**
     * Ускорение падения фигуры (доделать)
     */
    public void acceleration() {
    }

    public void changeForm() {
        figureI.setWidth(25);
        figureI.setHeight(100);

        changed = true;
    }

    /**
     * изменение формы на начальную
     */
    public void returnForm() {
        figureI.setHeight(25);
        figureI.setWidth(100);
        delta = 25;
        changed = false;
    }

    /**
     * Проверка на столкновение вертикальной I снизу
     *
     * @return столкнулись ли фигуры
     */
    public boolean intersectsVertical() {
        boolean intersection = false;
        cellY = (int) (figureI.getY() / delta);

        if (changed) {
            if (cellY == 17) {
                intersection = true;
            }
            //столкновение вертикальной I c I
            else if (getGameField()[cellY + 3][cellX] != Elements.EmptyCell) {
                intersection = true;
            }

        }
        return intersection;
    }

    /**
     * Столкновение фигур в горизонтальной форме снизу
     */
    public boolean intersectsDefaultForm() {
        boolean intersection = false;
        cellX = (int) (figureI.getX() / delta);//получение ячейки Х из координаты фигуры

        if (!changed) {
            //столкновение с границей
            if (cellY == 20) {
                intersection = true;
            }
            //столкновение I c I
            else if (getGameField()[cellY][cellX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellY][cellX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellY][cellX + 2] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellY][cellX + 3] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    /**
     * Проверка остановки фигуры и заполнение игрового поля
     *
     * @return установлена ли фигура
     */
    @Override
    public boolean stop() {
        boolean figureSet = false;

        //проверка упала фигура на нижнюю грань или столкнулась с другой фигурой
        if (intersectsVertical() || intersectsDefaultForm()) {
            delta = 0;

            //заполнение игрового поля элементами
            if (changed) {
                for (int i = cellY - 1; i < cellY + 3; i++) {
                    getGameField()[i][cellX] = Elements.FigureI;
                }
            } else {
                for (int i = cellX; i < cellX + 4; i++) {
                    getGameField()[cellY - 1][i] = Elements.FigureI;
                }
            }
            figureSet = true;

            figureI.setY(-25);
            figureI.setX(150);
            //возвращение значений
            returnForm();

        }
        return figureSet;
    }
}
