package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Tetris;

public class FigureL extends Figure {
    private double deltaY = 25;

    GraphicsContext graphicsContext;

    private Tetris field = new Tetris();

    private Rectangle column = new Rectangle(50, -100, 25, 25);

    private Rectangle lowRow = new Rectangle(50, -50, 25, 25);

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public FigureL() {
        for (int i = 0; i < field.getCell() * 4; i += 25) {//создание фигуры
            column.setHeight(i);
        }
        for (int i = 0; i < field.getCell() * 3; i += 25) {
            lowRow.setWidth(i);
        }

        getChildren().addAll(column, lowRow, field.getCanvas());//добавление в граф сцены

        lowRow.setFill(Color.YELLOW);//установка цвета
        column.setFill(Color.YELLOW);
    }

    public Rectangle getLowRow() {
        return lowRow;
    }

    public Rectangle getColumn() {
        return column;
    }

    public void setRowY(double y) {
        lowRow.setY(y);
    }

    public void setColumnY(double y) {
        column.setY(y);
    }

    public void moveY() {
        lowRow.setY(lowRow.getY() + deltaY);
        column.setY(column.getY() + deltaY);
    }

    public void moveXLeft() {
        if (lowRow.getY() != field.getLowBound() - 25) {
            if (lowRow.getX() != 0) {
                lowRow.setX(lowRow.getX() - 25);
                column.setX(column.getX() - 25);
            }
        }
    }

    public void moveXRight() {
        if (lowRow.getY() != field.getLowBound() - 25) {
            if (lowRow.getX() != 350) {
                lowRow.setX(lowRow.getX() + 25);
                column.setX(column.getX() + 25);
            }
        }
    }

    public void acceleration() {
        //доделать
    }

    public boolean stop() {//установить нижние границы
        graphicsContext = field.getCanvas().getGraphicsContext2D();
        boolean bool = lowRow.getY() == field.getLowBound();

        if (lowRow.getY() == field.getLowBound()) {
            deltaY = 0;

            /**
             graphicsContext.fillRect(row.getX(), row.getY() - 25, 50, row.getHeight());
             graphicsContext.fillRect(column.getX(), column.getY() - 25, column.getWidth(), 75);
            ;*/
        }

        return bool;
    }

}