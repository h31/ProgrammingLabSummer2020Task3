package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureL extends Figure {
    private int cellRowX;
    private int cellColumnX;
    private int cellRowY;
    private int cellColumnY;
    private boolean changedToFirstForm;
    private boolean changedToSecondForm;
    private boolean changedToThirdForm;

    private final Rectangle column = new Rectangle(200, -50, 25, 50);

    private final Rectangle row = new Rectangle(150, -25, 75, 25);

    public FigureL() {
        Tetris tetris = new Tetris();
        getChildren().addAll(column, row, tetris.getCanvas());//добавление в граф сцены

        row.setFill(Color.YELLOW);//установка цвета
        column.setFill(Color.YELLOW);
    }

    public double getColumnX() {
        return column.getX();
    }
    public double getRowX(){
        return row.getX();
    }
    /**
     * Движение фигуры вниз
     */
    @Override
    public void moveDown() {
        if (!endGame()) {
            row.setY(row.getY() + getDelta());
            column.setY(column.getY() + getDelta());
        }
    }

    /**
     * Передвижение влево и проверка столкновений
     */
    @Override
    public void moveLeft() {
        cellRowY = (int) (row.getY() / getDelta());
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        if (cellColumnY > 0 && cellColumnX > 0) {
            row.setX(row.getX() - getDelta());
            column.setX(column.getX() - getDelta());

            if (changedToFirstForm) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 1][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 2][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 3][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }
            } else if (changedToSecondForm) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 2][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }

            } else if (changedToThirdForm) {
                if (row.getX() < 0) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 2][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 2][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 3][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 4][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }
            } else {
                if (row.getX() < 0) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 2][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }
            }
        }
    }

    /**
     * Передвижение вправо и проверка столкновений
     */
    @Override
    public void moveRight() {
        cellRowY = (int) (row.getY() / getDelta());
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        if (cellRowX + 2 != 16 && cellColumnY > -1 && cellColumnY + 4 != 20) {
            row.setX(row.getX() + getDelta());
            column.setX(column.getX() + getDelta());

            if (changedToFirstForm) {
                if (row.getX() > 350) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY][cellRowX + 2] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY + 1][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY + 2][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX + 2] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                }
            } else if (changedToSecondForm) {
                if (row.getX() > 325) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY][cellRowX + 3] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX + 3] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX + 2] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                }

            } else if (changedToThirdForm) {
                if (row.getX() > 350) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY + 2][cellRowX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY + 2][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY + 3][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY + 4][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                }
            } else {
                if (row.getX() > 325) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellRowY][cellRowX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                } else if (getGameField()[cellColumnY + 1][cellColumnX + 1] != Elements.EmptyCell) {
                    row.setX(row.getX() - getDelta());
                    column.setX(column.getX() - getDelta());
                }
            }
        }
    }

    public void changeFirstForm() {
        if (cellRowY + 2 != 20 && getGameField()[cellRowY + 2][cellRowX] == Elements.EmptyCell) {
            column.setHeight(75);
            row.setWidth(50);
            column.setX(row.getX());
            row.setY(column.getY() + 50);

            changedToFirstForm = true;
            changedToSecondForm = false;
            changedToThirdForm = false;
        }
    }

    public void changeSecondForm() {
        if (changedToFirstForm && row.getX() != 350) {
            column.setHeight(50);
            row.setWidth(75);
            column.setX(row.getX());
            row.setY(column.getY());

            changedToSecondForm = true;
            changedToFirstForm = false;
            changedToThirdForm = false;
        }
    }

    public void changeThirdForm() {
        if (changedToSecondForm && column.getX() != 0) {
            column.setHeight(75);
            row.setWidth(50);
            row.setY(column.getY());
            row.setX(column.getX() - 25);

            changedToFirstForm = false;
            changedToSecondForm = false;
            changedToThirdForm = true;
        }
    }

    public void returnForm() {
        row.setWidth(75);
        column.setWidth(25);

        row.setHeight(25);
        column.setHeight(50);

        column.setX(row.getX() + 50);
        column.setY(row.getY() - 25);

        changedToFirstForm = false;
        changedToSecondForm = false;
        changedToThirdForm = false;
    }

    public boolean intersectsThirdForm() {
        boolean intersection = false;

        if (changedToThirdForm) {
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

    public boolean intersectsSecondForm() {
        boolean intersection = false;
        cellRowY = (int) (row.getY() / getDelta());
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        if (changedToSecondForm) {
            if (cellColumnY + 2 == getGameField().length) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 1][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 1][cellRowX + 2] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    public boolean intersectsFirstForm() {
        cellRowY = (int) (row.getY() / getDelta());
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());
        boolean intersection = false;
        if (changedToFirstForm) {

            if (cellRowY + 1 == getGameField().length) {
                intersection = true;
            } else if (getGameField()[cellRowY + 1][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellRowY + 1][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }
        }
        return intersection;
    }

    public boolean intersectsDefaultForm() {
        cellRowY = (int) (row.getY() / getDelta());
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        boolean intersection = false;
        if (!changedToFirstForm && !changedToSecondForm && !changedToThirdForm) {

            if (cellColumnY + 2 == getGameField().length) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX + 2] != Elements.EmptyCell) {
                intersection = true;
            }
        }
        return intersection;
    }

    @Override
    public boolean stop() {
        cellRowY = (int) (row.getY() / getDelta());
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        boolean figureSet = false;

        if (!endGame()) {
            if (intersectsFirstForm() || intersectsSecondForm() || intersectsThirdForm() || intersectsDefaultForm()) {
                setDelta(0);

                if (changedToFirstForm) {
                    for (int i = cellColumnY; i < cellColumnY + 3; i++) {
                        getGameField()[i][cellRowX] = GameField.Elements.FigureL;
                    }
                    for (int i = cellRowX; i < cellRowX + 2; i++) {
                        getGameField()[cellRowY][i] = GameField.Elements.FigureL;
                    }
                    changedToFirstForm = false;
                } else if (changedToSecondForm) {
                    for (int i = cellRowX; i < cellRowX + 3; i++) {
                        getGameField()[cellRowY][i] = Elements.FigureL;
                    }
                    getGameField()[cellRowY + 1][cellRowX] = Elements.FigureL;
                    changedToSecondForm = false;
                } else if (changedToThirdForm) {
                    for (int i = cellColumnY; i < cellColumnY + 3; i++) {
                        getGameField()[i][cellRowX + 1] = GameField.Elements.FigureL;
                    }
                    getGameField()[cellColumnY][cellRowX] = Elements.FigureL;

                    changedToThirdForm = false;
                } else {
                    for (int i = cellRowX; i < cellRowX + 3; i++) {
                        getGameField()[cellRowY][i] = Elements.FigureL;
                    }
                    getGameField()[cellRowY - 1][cellRowX + 2] = Elements.FigureL;

                    changedToThirdForm = false;
                    changedToFirstForm = false;
                    changedToSecondForm = false;
                }

                column.setY(-50);
                row.setY(-25);

                column.setWidth(25);
                column.setHeight(50);
                row.setWidth(75);
                row.setHeight(25);

                row.setX(150);
                column.setX(200);

                setDelta(25);
                figureSet = true;
            }
        }
        return figureSet;
    }


}