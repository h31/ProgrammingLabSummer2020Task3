package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureZ extends Figure {
    double deltaY = 25;

    Tetris field = new Tetris();

    //переделать имена
    Rectangle downRow = new Rectangle(75, -25, 25, 25);
    Rectangle upRow = new Rectangle(100, -50, 25, 25);

    public Rectangle getDownRow() {
        return downRow;
    }

    public Rectangle getUpRow() {
        return upRow;
    }

    public void setY(double y) {
        upRow.setY(y);
        downRow.setY(y + 25);
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public FigureZ() {
        for (int i = 0; i < field.getCell() * 3; i += 25) {
            downRow.setWidth(i);
        }
        for (int i = 0; i < field.getCell() * 3; i += 25) {
            upRow.setWidth(i);
        }
        getChildren().addAll(downRow, upRow,field.getCanvas());

        upRow.setFill(Color.ORANGE);
        downRow.setFill(Color.ORANGE);
    }

    public void moveY() {
        upRow.setY(upRow.getY() + deltaY);
        downRow.setY(downRow.getY() + deltaY);
    }

    public void moveXLeft() {
        if (upRow.getY() != field.getLowBound() - 50) {
            if (downRow.getX() != 0) {
                upRow.setX(upRow.getX() - 25);
                downRow.setX(downRow.getX() - 25);
            }
        }
    }

    public void moveXRight() {
        if (upRow.getY() != field.getLowBound() - 50) {
            if (downRow.getX() != 325) {
                upRow.setX(upRow.getX() + 25);
                downRow.setX(downRow.getX() + 25);
            }
        }
    }

    public void acceleration() {
        //доделать
    }

    public boolean stop() {//установить нижние границы

        boolean bool = downRow.getY() == field.getLowBound() - 25;
        if (downRow.getY() == field.getLowBound() - 25) {
            deltaY = 0;
        }
        return bool;
    }

}