package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureL extends Figure {
    private int delta = 25;
    private int cellRowX;
    private int cellColumnX;
    private int cellRowY;
    private int cellColumnY;
    private boolean changedToFirstForm;
    private boolean changedToSecondForm;
    private boolean changedToThirdForm;

    private final Rectangle column = new Rectangle(150, -75, 25, 75);//*поставить переменные

    private final Rectangle row = new Rectangle(150, -25, 50, 25);


    public FigureL() {

        Tetris tetris = new Tetris();
        getChildren().addAll(column, row, tetris.getCanvas());//добавление в граф сцены


        row.setFill(Color.YELLOW);//установка цвета
        column.setFill(Color.YELLOW);
    }

    /**
     * Движение фигуры вниз
     */
    @Override
    public void moveDown() {
        row.setY(row.getY() + delta);
        column.setY(column.getY() + delta);
    }

    /**
     * Передвижение влево и проверка столкновений
     */
    @Override
    public void moveLeft() {
        if (cellColumnY > 0 && cellColumnX > 0) {
            row.setX(row.getX() - delta);
            column.setX(column.getX() - delta);

            if (changedToFirstForm) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY - 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                }
            } else if (changedToSecondForm) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellRowY + 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellRowY + 2][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY + 2][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY + 3][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY + 4][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                }


            } else if (changedToThirdForm) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellRowY + 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                }
            } else {
                if (row.getX() < 0) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY + 1][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY + 2][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                } else if (getGameField()[cellColumnY + 3][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + delta);
                    column.setX(column.getX() + delta);
                }
            }
        }
    }

    /**
     * Передвижение вправо и проверка столкновений
     */
    @Override
    public void moveRight() {
        if (cellRowX + 2 != 16 && cellColumnY > -1) {
            row.setX(row.getX() + delta);
            column.setX(column.getX() + delta);

            if (changedToFirstForm) {
                if (row.getX() > 325) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY][cellRowX + 3] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY + 1][cellRowX + 3] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY][cellColumnX + 2] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                }


            } else if (changedToSecondForm) {
                if (row.getX() > 350) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY + 1][cellRowX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY + 2][cellRowX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY + 2][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY + 3][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY + 4][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                }

            } else if (changedToThirdForm) {
                if (row.getX() > 350) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY][cellRowX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY + 1][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                }

            } else {
                if (row.getX() > 350) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY][cellRowX + 2] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY + 1][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellColumnY + 2][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                } else if (getGameField()[cellRowY + 1][cellRowX + 2] != Elements.EmptyCell) {
                    row.setX(row.getX() - delta);
                    column.setX(column.getX() - delta);
                }
            }
        }
    }

    public void changeFirstForm() {
        row.setWidth(75);
        column.setHeight(25);
        column.setY(row.getY() + 25);

        changedToFirstForm = true;
        changedToSecondForm = false;
        changedToThirdForm = false;
    }

    public void changeSecondForm() {
        row.setWidth(50);
        column.setHeight(75);
        row.setY(column.getY());
        row.setX(column.getX() - 25);

        changedToSecondForm = true;
        changedToFirstForm = false;
        changedToThirdForm = false;
    }

    public void changeThirdForm() {
        row.setWidth(75);
        column.setHeight(50);
        column.setX(column.getX() + 25);
        column.setY(column.getY() - 25);

        changedToFirstForm = false;
        changedToSecondForm = false;
        changedToThirdForm = true;
    }

    public void returnToBegin() {
        column.setY(-75);
        row.setY(-25);
        column.setWidth(25);
        column.setHeight(75);
        row.setWidth(50);
        row.setHeight(25);
        row.setX(150);
        column.setX(150);

        delta = 25;
    }

    public void returnForm() {
        row.setWidth(50);
        column.setWidth(25);

        row.setHeight(25);
        column.setHeight(75);

        row.setX(row.getX() + 50);
        row.setY(row.getY() - 25);
        column.setY(column.getY() - 50);

        changedToFirstForm = false;
        changedToSecondForm = false;
        changedToThirdForm = false;
    }

    public boolean intersectsThirdForm() {
        boolean intersection = false;

        if (changedToThirdForm) {
            //столкновение 3 формы L c границей
            if (cellColumnY + 2 == getGameField().length) {
                intersection = true;
            }
            //столкновение 3 формы L c L
            else if (getGameField()[cellColumnY + 2][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX + 2] != Elements.EmptyCell) {
                intersection = true;
            }
        }


        return intersection;
    }

    public boolean intersectsSecondForm() {
        boolean intersection = false;

        if (changedToSecondForm) {
            //столкновение 2 формы L c границуй
            if (cellColumnY + 3 == getGameField().length) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 1][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 3][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    public boolean intersectsFirstForm() {
        boolean intersection = false;
        if (changedToFirstForm) {
            if (cellColumnY == 19) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 1][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY][cellRowX + 2] != Elements.EmptyCell) {
                intersection = true;
            }
        }
        return intersection;
    }


    public boolean intersectsDefaultForm() {
        boolean intersection = false;
        if (!changedToFirstForm && !changedToSecondForm && !changedToThirdForm) {
            //столкновение L c границей
            if (cellRowY + 1 == 20) {
                intersection = true;
            } else if (getGameField()[cellRowY + 1][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellRowY + 1][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }

        }
        return intersection;
    }


    @Override
    public boolean stop() {
        cellRowY = (int) (row.getY() / delta);
        cellRowX = (int) (row.getX() / delta);
        cellColumnX = (int) (column.getX() / delta);
        cellColumnY = (int) (column.getY() / delta);

        boolean figureSet = false;

        if (intersectsFirstForm() || intersectsSecondForm() || intersectsThirdForm() || intersectsDefaultForm()) {
            delta = 0;

            if (changedToFirstForm) {
                for (int i = cellRowX; i < cellRowX + 3; i++) {
                    getGameField()[cellRowY][i] = Elements.FigureL;
                }
                getGameField()[cellRowY + 1][cellRowX] = Elements.FigureL;
                changedToFirstForm = false;
            } else if (changedToSecondForm) {
                for (int i = cellColumnY; i < cellColumnY + 3; i++) {
                    getGameField()[i][cellRowX + 1] = GameField.Elements.FigureL;
                }
                getGameField()[cellColumnY][cellRowX] = Elements.FigureL;
                changedToSecondForm = false;
            } else if (changedToThirdForm) {
                for (int i = cellRowX; i < cellRowX + 3; i++) {
                    getGameField()[cellRowY][i] = Elements.FigureL;
                }
                getGameField()[cellRowY - 1][cellRowX + 2] = Elements.FigureL;

                changedToThirdForm = false;
            } else {
                for (int i = cellColumnY; i < cellColumnY + 3; i++) {
                    getGameField()[i][cellRowX] = GameField.Elements.FigureL;
                }
                for (int i = cellRowX; i < cellRowX + 2; i++) {
                    getGameField()[cellRowY][i] = GameField.Elements.FigureL;
                }
                changedToFirstForm = false;
                changedToSecondForm = false;
                changedToThirdForm = false;
            }

            figureSet = true;

            returnToBegin();

        }

        return figureSet;
    }

}