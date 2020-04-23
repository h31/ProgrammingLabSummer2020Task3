package model;


import controller.GameCycle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField extends Pane {

//    FigureI figureI = gameCycle.getFigureI();
    Rectangle rectangleI = new Rectangle(150,50,100,25);

    public Rectangle getRectangleI() {
        return rectangleI;
    }


    public GameField() {
       // getChildren().add(rectangleI);
        rectangleI.setFill(Color.GREENYELLOW);
    }

    public void setFigure() {
        rectangleI.setY(150);
        rectangleI.setX(50);
    }

}
