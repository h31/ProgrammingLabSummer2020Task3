package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureZ extends Figure {
    private int delta = 25;

    int cellUpRowX;
    int cellDownRowX;
    int cellDownRowY;
    int cellUpRowY;
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
        upRow.setY(upRow.getY() + delta);
        downRow.setY(downRow.getY() + delta);

    }

    @Override
    public void moveLeft() {
        if (cellUpRowY != 20 && cellDownRowY > 0 && cellDownRowX > 0) {
            upRow.setX(upRow.getX() - delta);
            downRow.setX(downRow.getX() - delta);

            if (changedForm) {
                if (cellDownRowX < 0 && cellUpRowX < 0) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellUpRowY][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellUpRowY + 1][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellDownRowY][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellDownRowY + 1][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellDownRowY + 2][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellUpRowY + 2][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                }

            } else {
                if (downRow.getX() < 0) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellUpRowY][cellUpRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellDownRowY][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                } else if (getGameField()[cellDownRowY + 1][cellDownRowX - 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() + delta);
                    downRow.setX(downRow.getX() + delta);
                }
            }
        }
    }

    @Override
    public void moveRight() {
        if (cellUpRowY != 20 && cellDownRowY > 0 && cellUpRowX + 2 != 16) {
            upRow.setX(upRow.getX() + delta);
            downRow.setX(downRow.getX() + delta);

            if (changedForm) {
                if (downRow.getX() > 375) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellUpRowY][cellUpRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellUpRowY + 1][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellDownRowY][cellDownRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellDownRowY + 1][cellDownRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellDownRowY + 2][cellDownRowX + 1] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellUpRowY + 2][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                }

            } else {
                if (upRow.getX() > 350) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellUpRowY][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellDownRowY][cellDownRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                } else if (getGameField()[cellUpRowY + 1][cellUpRowX + 2] != Elements.EmptyCell) {
                    upRow.setX(upRow.getX() - delta);
                    downRow.setX(downRow.getX() - delta);
                }
            }
        }
    }

    public void changeForm() {
        upRow.setWidth(25);
        upRow.setHeight(50);

        downRow.setHeight(50);
        downRow.setWidth(25);

        downRow.setX(upRow.getX() + 25);

        changedForm = true;
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
        boolean intersection = false;

        if (changedForm) {
            if (cellDownRowY + 2 == getGameField().length) {
                intersection = true;
            }
            //столкновение 1 формы Z c Z
            else if (getGameField()[cellDownRowY + 2][cellDownRowX] != Elements.EmptyCell) {
                intersection = true;
            } else if (getGameField()[cellUpRowY + 2][cellUpRowX] != Elements.EmptyCell) {
                intersection = true;
            }
        }

        return intersection;
    }

    public boolean intersectsDefaultForm() {
        boolean intersection = false;
        if (!changedForm) {
            //столкновение Z c границей
            if (cellDownRowY + 1 == getGameField().length) {
                intersection = true;
            }
            //столкновение Z c Z
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
        cellUpRowX = (int) (upRow.getX() / delta);
        cellDownRowX = (int) (downRow.getX() / delta);

        cellDownRowY = (int) (downRow.getY() / delta);
        cellUpRowY = (int) (upRow.getY() / delta);
        //установить нижние границы
        if (intersectsDefaultForm() || intersectsVertical()) {
            delta = 0;

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
            delta = 25;
            upRow.setX(150);
            downRow.setX(125);

            figureSet = true;
            changedForm = false;
        }
        return figureSet;
    }

}