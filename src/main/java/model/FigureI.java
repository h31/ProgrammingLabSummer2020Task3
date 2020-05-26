package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureI extends Figure {
    private final GameField field = new GameField();
    private final Rectangle row = new Rectangle(150, -25, field.getWidthCell() * 4, field.getHeightCell());
    private int cellX;
    private int cellY;
    private boolean changed;

    public FigureI() {
        //задание цвета падающей фигуре
        row.setFill(Color.GREENYELLOW);

        //добавление в граф сцены
        Tetris tetris = new Tetris();
        getChildren().addAll(tetris.getCanvas(), row);
    }

    public double getRowX(){
        return row.getX();
    }
    public double getRowY(){
        return row.getY();
    }
    /**
     * Движение фигуры вниз
     */
    @Override
    public void moveDown() {
        if (!endGame()) {
            row.setY(row.getY() + getDelta());
        }
    }
    /**
     * Проверка возможности движения влево
     * и само движение
     */
    @Override
    public void moveLeft() {
        cellY = (int) (row.getY() / getDelta());
        cellX = (int) (row.getX() / getDelta());//получение ячейки Х из координаты фигуры
        if (cellY > 0 && cellY != 20) {
            row.setX(row.getX() - getDelta());

            if (!changed) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + getDelta());
                } else if (getGameField()[cellY][cellX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                }
            } else {
                if (row.getX() < 0) {
                    row.setX(row.getX() + getDelta());
                } else if (getGameField()[cellY][cellX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                } else if (getGameField()[cellY + 1][cellX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                } else if (getGameField()[cellY + 2][cellX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                } else if (getGameField()[cellY + 3][cellX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                } else if (getGameField()[cellY + 4][cellX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
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
        cellX = (int) (row.getX() / getDelta());
        cellY = (int) (row.getY() / getDelta());

        if (cellY > 0 && cellY + 4 != 20) {
            row.setX(row.getX() + getDelta());

            if (!changed) {
                if (row.getX() == 325) {
                    row.setX(row.getX() - getDelta());
                } else if (getGameField()[cellY][cellX + 4] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                }
            } else {
                if (row.getX() == 400) {
                    row.setX(row.getX() - getDelta());
                } else if (getGameField()[cellY][cellX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                } else if (getGameField()[cellY + 1][cellX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                } else if (getGameField()[cellY + 2][cellX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                } else if (getGameField()[cellY + 3][cellX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                } else if (getGameField()[cellY + 4][cellX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                }
            }
        }
    }


    public void changeForm() {
        if (cellY + 3 != 20 && cellY + 2 != 20 && cellY + 1 != 20
                && getGameField()[cellY][cellX] == Elements.EmptyCell
                && getGameField()[cellY + 1][cellX] == Elements.EmptyCell
                && getGameField()[cellY + 2][cellX] == Elements.EmptyCell
                && getGameField()[cellY + 3][cellX] == Elements.EmptyCell
        ) {
            row.setWidth(25);
            row.setHeight(100);

            changed = true;
        }
    }

    /**
     * изменение формы на начальную
     */
    public void returnForm() {
        if (cellX != 0 && cellX + 1 != 16 && cellX + 2 != 16 && cellX + 3 != 16
                && getGameField()[cellY][cellX] == Elements.EmptyCell
                && getGameField()[cellY][cellX + 1] == Elements.EmptyCell
                && getGameField()[cellY][cellX + 2] == Elements.EmptyCell
                && getGameField()[cellY][cellX + 3] == Elements.EmptyCell) {
            row.setHeight(25);
            row.setWidth(100);

            changed = false;
        }
    }

    /**
     * Проверка на столкновение вертикальной I снизу
     *
     * @return столкнулись ли фигуры
     */
    public boolean intersectsVertical() {
        boolean intersection = false;
        cellY = (int) (row.getY() / getDelta());
        cellY = (int) (row.getY() / getDelta());

        if (changed) {
            if (cellY == 17) {
                intersection = true;
            }
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
        cellX = (int) (row.getX() / getDelta());//получение ячейки Х из координаты фигуры
        cellY = (int) (row.getY() / getDelta());

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
        cellX = (int) (row.getX() / getDelta());
        cellY = (int) (row.getY() / getDelta());
        boolean figureSet = false;

        //проверка упала фигура на нижнюю грань или столкнулась с другой фигурой
        if (!endGame()) {
            if (intersectsVertical() || intersectsDefaultForm()) {
                setDelta(0);

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

                //возвращение значений
                row.setY(-25);
                row.setX(150);
                row.setHeight(25);
                row.setWidth(100);
                changed = false;
                setDelta(25);
            }
        }
        return figureSet;
    }
}
