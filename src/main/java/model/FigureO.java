package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureO extends Figure {
    double deltaY = 25;

    Tetris field = new Tetris();

    Rectangle square = new Rectangle(250, -50, 50, 50);

    GraphicsContext graphicsContext;

    public double getX(){
        return square.getX();
    }
    public double getY(){
        return square.getY();
    }

    public void setY(double y){
        square.setY(y);
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public FigureO() {
        for (int i = 0; i < field.getCell() * 3; i += 25) {
            square.setWidth(i);
        }
        getChildren().addAll(square);
        square.setFill(Color.RED);
    }

    public void moveY() {
        square.setY(square.getY() + deltaY);
    }

    public void moveXLeft() {
        if (square.getY() != field.getLowBound() - 50) {
            if (square.getX() != 0) {
                square.setX(square.getX() - 25);
            }
        }
    }

    public void moveXRight() {
        if (square.getY() != field.getLowBound()) {
            if (square.getX() != 350) {
                square.setX(square.getX() + 25);
            }
        }
    }

    public void acceleration() {
        //доделать ускорение
    }

    public boolean stop() {//установить нижние границы
        boolean bool = square.getY() == field.getLowBound() - 25;

        if (square.getY() == field.getLowBound() - 25) {
            deltaY = 0;
        }
        return bool;
    }

}
