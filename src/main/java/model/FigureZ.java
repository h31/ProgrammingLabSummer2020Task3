package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureZ extends Figure {
    private int cellUpRowX;
    private int cellDownRowX;
    private int cellDownRowY;
    private int cellUpRowY;
    private boolean changedForm;

    private final GameField gameField = new GameField();

    private final Rectangle downRow = new Rectangle(125, -25, gameField.getWidthCell() * 2, gameField.getHeightCell());
    private final Rectangle upRow = new Rectangle(150, -50, gameField.getWidthCell() * 2, gameField.getHeightCell());

    public FigureZ() {
        Tetris tetris = new Tetris();
        getChildren().addAll(downRow, upRow, tetris.getCanvas());

        upRow.setFill(Color.ORANGE);
        downRow.setFill(Color.ORANGE);
    }

    @Override
    public void moveDown() {
        if (!endGame()) {
            upRow.setY(upRow.getY() + getDelta());
            downRow.setY(downRow.getY() + getDelta());
        }
    }

    public double getUpRowX() {
        return upRow.getX();
    }

    public double getDownRowX() {
        return downRow.getX();
    }

    @Override
    public void moveLeft() {
        cellUpRowX = (int) (upRow.getX() / getDelta());
        cellDownRowX = (int) (downRow.getX() / getDelta());
        cellDownRowY = (int) (downRow.getY() / getDelta());
        cellUpRowY = (int) (upRow.getY() / getDelta());

        if (cellUpRowY != 20 && cellDownRowY > 0 && cellDownRowX > 0 && cellUpRowX > 0) {
            upRow.setX(upRow.getX() - getDelta());
            downRow.setX(downRow.getX() - getDelta());

            if (changedForm) {
                if (getGameField()[cellUpRowY][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellUpRowY + 1][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellDownRowY][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellDownRowY + 1][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellDownRowY + 2][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellUpRowY + 2][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                }
            } else {
                if (getGameField()[cellUpRowY][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellDownRowY][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                } else if (getGameField()[cellDownRowY + 1][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + getDelta());
                    downRow.setX(downRow.getX() + getDelta());
                }
            }
        }
    }

    @Override
    public void moveRight() {
        cellUpRowX = (int) (upRow.getX() / getDelta());
        cellDownRowX = (int) (downRow.getX() / getDelta());
        cellDownRowY = (int) (downRow.getY() / getDelta());
        cellUpRowY = (int) (upRow.getY() / getDelta());
        if (cellUpRowY != 20 && cellDownRowY > 0 && cellUpRowX + 2 != 16) {
            upRow.setX(upRow.getX() + getDelta());
            downRow.setX(downRow.getX() + getDelta());

            if (changedForm) {
                if (downRow.getX() > 375) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellUpRowY][cellUpRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellUpRowY + 1][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellDownRowY][cellDownRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellDownRowY + 1][cellDownRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellDownRowY + 2][cellDownRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellUpRowY + 2][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                }
            } else {
                if (upRow.getX() > 350) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellUpRowY][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellDownRowY][cellDownRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                } else if (getGameField()[cellUpRowY + 1][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - getDelta());
                    downRow.setX(downRow.getX() - getDelta());
                }
            }
        }
    }

    public void changeForm() {
        if (cellDownRowY + 2 != 20) {
            upRow.setWidth(25);
            upRow.setHeight(50);

            downRow.setHeight(50);
            downRow.setWidth(25);

            downRow.setX(upRow.getX() + 25);

            changedForm = true;
        }
    }

    public void returnForm() {
        upRow.setWidth(50);
        upRow.setHeight(25);

        downRow.setHeight(25);
        downRow.setWidth(50);

        downRow.setY(upRow.getY() + 25);
        downRow.setX(upRow.getX() - 25);

        changedForm = false;
    }

    public boolean intersectsVertical() {
        cellUpRowX = (int) (upRow.getX() / getDelta());
        cellDownRowX = (int) (downRow.getX() / getDelta());
        cellDownRowY = (int) (downRow.getY() / getDelta());
        cellUpRowY = (int) (upRow.getY() / getDelta());
        boolean intersection = false;

        if (changedForm) {
            if (cellDownRowY + 2 == getGameField().length) {
                intersection = true;
            }
            //столкновение 1 формы Z c фигурой
            else if (getGameField()[cellDownRowY + 2][cellDownRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellUpRowY + 2][cellUpRowX] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    public boolean intersectsDefaultForm() {
        cellUpRowX = (int) (upRow.getX() / getDelta());
        cellDownRowX = (int) (downRow.getX() / getDelta());
        cellDownRowY = (int) (downRow.getY() / getDelta());
        cellUpRowY = (int) (upRow.getY() / getDelta());
        boolean intersection = false;
        if (!changedForm) {
            //столкновение Z c границей
            if (cellDownRowY + 1 == getGameField().length) {
                intersection = true;
            }
            //столкновение z c фигурой
            else if (cellUpRowY > 0 && getGameField()[cellUpRowY + 1][cellUpRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            } else if (cellDownRowY > 0 && getGameField()[cellDownRowY + 1][cellDownRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (cellDownRowY > 0 && getGameField()[cellDownRowY + 1][cellDownRowX + 1] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    @Override
    public boolean stop() {
        boolean figureSet = false;
        cellUpRowX = (int) (upRow.getX() / getDelta());
        cellDownRowX = (int) (downRow.getX() / getDelta());
        cellDownRowY = (int) (downRow.getY() / getDelta());
        cellUpRowY = (int) (upRow.getY() / getDelta());

        if (!endGame()) {
            if (intersectsDefaultForm() || intersectsVertical()) {
                setDelta(0);

                if (!changedForm) {
                    for (int i = cellUpRowX; i < cellUpRowX + 2; i++) {
                        getGameField()[cellUpRowY][i] = Elements.FigureZ;
                    }
                    for (int i = cellDownRowX; i < cellDownRowX + 2; i++) {
                        getGameField()[cellDownRowY][i] = Elements.FigureZ;
                    }
                } else {
                    for (int i = cellUpRowY; i < cellUpRowY + 2; i++) {
                        getGameField()[i][cellUpRowX] = Elements.FigureZ;
                    }
                    for (int i = cellDownRowY; i < cellDownRowY + 2; i++) {
                        getGameField()[i][cellDownRowX] = Elements.FigureZ;
                    }
                    upRow.setWidth(50);
                    upRow.setHeight(25);

                    downRow.setHeight(25);
                    downRow.setWidth(50);
                }

                upRow.setY(-50);
                downRow.setY(-25);
                upRow.setX(150);
                downRow.setX(125);
                setDelta(25);

                figureSet = true;
                changedForm = false;
            }
        }
        return figureSet;
    }
}