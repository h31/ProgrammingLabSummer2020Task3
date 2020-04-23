package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureT extends Figure {
    double deltaY = 25;

    private Tetris root = new Tetris();

    private Rectangle column = new Rectangle(175, -25, 25, 25);

    private Rectangle row = new Rectangle(150, -50, 25, 25);

    public FigureT() {
        for (int i = 0; i < root.getCell() * 2; i += 25) {
            column.setHeight(i);
        }
        for (int i = 0; i < root.getCell() * 4; i += 25) {
            row.setWidth(i);
        }
        getChildren().addAll(column, row);

        row.setFill(Color.PURPLE);
        column.setFill(Color.PURPLE);
    }

    public Rectangle getColumn() {
        return column;
    }

    public Rectangle getRow() {
        return row;
    }

    public void setY(double y) {
        row.setY(y);
        column.setY(y + 25);
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public void moveY() {
        row.setY(row.getY() + deltaY);
        column.setY(column.getY() + deltaY);
    }

    public void moveXLeft() {
        if (column.getY() != root.getLowBound() - 25) {
            if (row.getX() != 0) {
                row.setX(row.getX() - 25);
                column.setX(column.getX() - 25);
            }
        }
    }

    public void moveXRight() {
        if (column.getY() != root.getLowBound() - 25) {
            if (row.getX() != 325) {
                row.setX(row.getX() + 25);
                column.setX(column.getX() + 25);
            }
        }
    }

    public void acceleration() {
        //доделать
    }

    public boolean stop() {//установить нижние границы
        boolean figureTisSet = column.getY() == root.getLowBound() - 25;
        if (figureTisSet) {
            deltaY = 0;
        }
        return figureTisSet;
    }

}