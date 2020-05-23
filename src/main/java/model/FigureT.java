package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureT extends Figure {
    private final Rectangle row = new Rectangle(150, -50, 75, 25);
    private final Rectangle column = new Rectangle(175, -25, 25, 25);

    private int cellRowX;
    private int cellColumnX;
    private int cellRowY;
    private int cellColumnY;
    private boolean changedToFirstForm;
    private boolean changedToSecondForm;
    private boolean changedToThirdForm;

    public FigureT() {
        Tetris tetris = new Tetris();
        getChildren().addAll(column, row, tetris.getCanvas());

        row.setFill(Color.PURPLE);
        column.setFill(Color.PURPLE);
    }

    @Override
    public void moveDown() {
        if (!endGame()) {
            row.setY(row.getY() + getDelta());
            column.setY(column.getY() + getDelta());
        }
    }

    @Override
    public void moveLeft() {
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());

        cellRowY = (int) (row.getY() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        if (cellRowX > 0 && cellRowY > 0) {

            row.setX(row.getX() - getDelta());
            column.setX(column.getX() - getDelta());

            if (row.getX() < 0 || column.getX() < 0) {
                row.setX(row.getX() + getDelta());
                column.setX(column.getX() + getDelta());
            }
            if (changedToFirstForm) {
                if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
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
                if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY + 1][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }
            } else if (changedToThirdForm) {
                if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 2][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY + 3][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }
            } else {
                if (getGameField()[cellRowY][cellRowX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                } else if (getGameField()[cellColumnY][cellColumnX - 1] != Elements.EmptyCell) {
                    row.setX(row.getX() + getDelta());
                    column.setX(column.getX() + getDelta());
                }
            }
        }
    }

    @Override
    public void moveRight() {
        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());

        cellRowY = (int) (row.getY() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());

        row.setX(row.getX() + getDelta());
        column.setX(column.getX() + getDelta());

        if (changedToFirstForm) {
            if (row.getX() == 375) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellRowY][cellRowX + 2] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellColumnY + 2][cellColumnX + 1] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellColumnY + 3][cellColumnX + 1] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            }
        } else if (changedToSecondForm) {
            if (row.getX() == 350) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellRowY][cellRowX + 3] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellRowY + 1][cellRowX + 3] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            }

        } else if (changedToThirdForm) {
            if (column.getX() == 400) {
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
            } else if (getGameField()[cellColumnY + 3][cellColumnX + 1] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            }
        } else {
            if (row.getX() == 350) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellRowY][cellRowX + 3] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            } else if (getGameField()[cellColumnY][cellColumnX + 1] != Elements.EmptyCell) {
                row.setX(row.getX() - getDelta());
                column.setX(column.getX() - getDelta());
            }
        }
    }

    public void changeFirstForm() {
        if (cellColumnY + 2 != 20 && (getGameField()[cellColumnY + 2][cellColumnX] == Elements.EmptyCell)) {

            column.setHeight(75);
            row.setWidth(50);

            row.setY(column.getY() + 25);
            row.setX(column.getX());

            changedToFirstForm = true;
            changedToSecondForm = false;
            changedToThirdForm = false;
        }
    }

    public void changeSecondForm() {
        if (changedToFirstForm) {
            column.setHeight(50);
            row.setWidth(75);

            column.setX(row.getX());
            row.setX(row.getX() - 25);

            changedToSecondForm = true;
            changedToFirstForm = false;
            changedToThirdForm = false;
        }
    }

    public void changeThirdForm() {
        if (column.getX() != 0 && column.getX() != 375 && cellColumnY + 2 != 20) {
            column.setHeight(75);
            row.setWidth(50);

            row.setY(column.getY() + 25);
            row.setX(column.getX() - 25);

            changedToFirstForm = false;
            changedToSecondForm = false;
            changedToThirdForm = true;
        }
    }

    public void returnToDefaultForm() {
        if (column.getX() != 0 && column.getX() != 375) {
            row.setWidth(75);
            column.setHeight(25);
            row.setY(row.getY());
            column.setY(row.getY() + 25);

            changedToFirstForm = false;
            changedToSecondForm = false;
            changedToThirdForm = false;
        }
    }

    public boolean intersectsDefaultForm() {
        boolean intersection = false;
        if (!changedToThirdForm && !changedToSecondForm && !changedToFirstForm) {

            //столкновение дефолтной формы T c границей
            if (cellColumnY == getGameField().length) {
                intersection = true;
            }
            //столкновение дефолтной формы T c T
            else if (cellColumnY > 0 && getGameField()[cellColumnY - 1][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (cellColumnY > 0 && getGameField()[cellColumnY - 1][cellRowX + 2] != Elements.EmptyCell) {
                intersection = true;
            } else if (cellColumnY > 0 && getGameField()[cellColumnY][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }

        }
        return intersection;
    }

    public boolean intersectsFirstForm() {
        boolean intersection = false;

        if (changedToFirstForm) {
            if (cellColumnY + 3 == getGameField().length) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 3][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 2][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    public boolean intersectsSecondForm() {
        boolean intersection = false;

        if (changedToSecondForm) {
            if (cellColumnY + 2 == getGameField().length) {
                intersection = true;
            }
            //столкновение 2 формы T c T
            else if (getGameField()[cellColumnY + 2][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (cellRowX + 1 != 16 && getGameField()[cellColumnY + 2][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (cellRowX + 1 != 16 && getGameField()[cellColumnY + 2][cellRowX + 2] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    public boolean intersectsThirdForm() {
        boolean intersection = false;

        if (changedToThirdForm) {
            if (cellColumnY + 3 == getGameField().length) {
                intersection = true;
            }
            //столкновение 3 формы T c T
            else if (getGameField()[cellColumnY + 2][cellRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellColumnY + 3][cellRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }
        }
        return intersection;
    }

    @Override
    public boolean stop() {
        boolean figureSet = false;

        cellRowX = (int) (row.getX() / getDelta());
        cellColumnX = (int) (column.getX() / getDelta());

        cellRowY = (int) (row.getY() / getDelta());
        cellColumnY = (int) (column.getY() / getDelta());
        if (!endGame()) {
            if (intersectsDefaultForm() || intersectsFirstForm() || intersectsSecondForm() || intersectsThirdForm()) {

                setDelta(0);

                if (changedToFirstForm) {
                    for (int i = cellColumnY; i < cellColumnY + 3; i++) {
                        getGameField()[i][cellRowX] = Elements.FigureT;
                    }
                    getGameField()[cellColumnY + 1][cellRowX + 1] = Elements.FigureT;
                    changedToFirstForm = false;
                } else if (changedToSecondForm) {
                    for (int i = cellRowX; i < cellRowX + 3; i++) {
                        getGameField()[cellRowY][i] = Elements.FigureT;
                    }
                    getGameField()[cellRowY - 1][cellRowX + 1] = Elements.FigureT;
                    changedToSecondForm = false;
                } else if (changedToThirdForm) {
                    for (int i = cellColumnY; i < cellColumnY + 3; i++) {
                        getGameField()[i][cellRowX + 1] = Elements.FigureT;
                    }
                    getGameField()[cellColumnY + 1][cellRowX] = Elements.FigureT;
                    changedToThirdForm = false;
                } else {
                    for (int i = cellRowX; i < cellRowX + 3; i++) {
                        getGameField()[cellColumnY - 2][i] = Elements.FigureT;
                    }
                    getGameField()[cellColumnY - 1][cellRowX + 1] = Elements.FigureT;
                }

                figureSet = true;

                row.setWidth(75);
                column.setHeight(25);
                row.setY(-50);
                column.setY(-25);
                column.setX(175);
                row.setX(150);

                setDelta(25);

            }
        }
        return figureSet;
    }
}