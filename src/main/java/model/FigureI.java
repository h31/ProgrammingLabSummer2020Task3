package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;


public class FigureI extends Figure {
    double deltaY = 25;

    private Tetris field = new Tetris();

    public double getY() {
        return row.getY();
    }

    public double getX() {
        return row.getX();
    }

    public void setY(double y) {
        row.setY(y);
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    private Rectangle row = new Rectangle(150, -50, 100, 25);

    public Rectangle getRow() {
        return row;
    }

    public FigureI() {
        row.setFill(Color.GREENYELLOW);

        getChildren().add(row);

    }

    public void moveY() {
        row.setY(row.getY() + deltaY);
    }

    public void moveXLeft() {
        if (row.getY() != field.getLowBound() - 25) {
            if (row.getX() != 0) {
                row.setX(row.getX() - 25);
            }
        }
    }

    public void moveXRight() {
        if (row.getY() != field.getLowBound() - 25) {
            if (row.getX() != 300) {
                row.setX(row.getX() + 25);
            }
        }
    }

    public void acceleration() {
        //доделать
    }

    //установить нижние границы
    public boolean stop() {
        boolean figureIisSet = row.getY() == (field.getLowBound() - 25);
        GameField field = new GameField();

        if (figureIisSet) {
            deltaY = 0;

            field.getRectangleI().setX(row.getX());
            field.getRectangleI().setY(row.getY());
        }
        return figureIisSet;
    }
}
