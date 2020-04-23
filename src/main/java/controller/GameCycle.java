package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.*;
import view.Tetris;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class GameCycle extends Pane {
    FigureL figureL = new FigureL();
    FigureZ figureZ = new FigureZ();
    FigureT figureT = new FigureT();
    FigureO figureO = new FigureO();
    FigureI figureI = new FigureI();

    ArrayList<Figure> figures = new ArrayList<>();
    Random random = new Random();
    Figure figure;


    public FigureI getFigureI() {
        return figureI;
    }

    public GameCycle() {
        figures.add(figureI);
        figures.add(figureL);
        figures.add(figureT);
        figures.add(figureO);
        figures.add(figureZ);

         getChildren().add(field.getCanvas());

        getChildren().addAll(figureI, figureL, figureO, figureT, figureZ);

        figure = figureI;
        animation();
    }


    Tetris field = new Tetris();
    GraphicsContext graphicsContext = field.getCanvas().getGraphicsContext2D();

  //  GameField gameField  = new GameField();;

    public void animation() {
        //Анимация движения по экран
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(360), t -> {
            if (figure.equals(figureI)) {
                figureI.moveY();
            } else if (figure.equals(figureL)) {
                figureL.moveY();
            } else if (figure.equals(figureO)) {
                figureO.moveY();
            } else if (figure.equals(figureT)) {
                figureT.moveY();
            } else if (figure.equals(figureZ)) {
                figureZ.moveY();
            }

            getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) {
                    figureI.moveXLeft();
                    figureZ.moveXLeft();
                    figureT.moveXLeft();
                    figureO.moveXLeft();
                    figureL.moveXLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    figureI.moveXRight();
                    figureZ.moveXRight();
                    figureT.moveXRight();
                    figureO.moveXRight();
                    figureL.moveXRight();
                }

            });

            setFigure();

        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    public void setFigure() {
        if (figureI.stop()) {
            graphicsContext.setFill(Color.GREENYELLOW);
            graphicsContext.fillRect(figureI.getX(), figureI.getY(), 100, 25);

            figureI.setY(-50);
            figureI.setDeltaY(25);

            figure = figures.get(random.nextInt(figures.size()));
        } else if (figureO.stop()) {
            graphicsContext.setFill(Color.RED);
            graphicsContext.fillRect(figureO.getX(), figureO.getY() - 25, 50, 50);

            figureO.setY(-50);
            figureO.setDeltaY(25);

            figure = figures.get(random.nextInt(figures.size()));
        } else if (figureL.stop()) {
            graphicsContext.setFill(Color.YELLOW);
            graphicsContext.fillRect(figureL.getLowRow().getX(), figureL.getLowRow().getY() - 25, 50, 25);//настроить приземление
            graphicsContext.fillRect(figureL.getColumn().getX(), figureL.getColumn().getY() - 25, 25, 75);

            figureL.setRowY(-50);
            figureL.setColumnY(-100);
            figureL.setDeltaY(25);
            figure = figures.get(random.nextInt(figures.size()));

        } else if (figureZ.stop()) {
            graphicsContext.setFill(Color.ORANGE);
            graphicsContext.fillRect(figureZ.getUpRow().getX(), figureZ.getUpRow().getY(), figureZ.getUpRow().getWidth(), figureZ.getUpRow().getHeight());
            graphicsContext.fillRect(figureZ.getDownRow().getX(), figureZ.getDownRow().getY(), figureZ.getDownRow().getWidth(), figureZ.getDownRow().getHeight());


            figureZ.setY(-50);
            figureZ.setDeltaY(25);
            figure = figures.get(random.nextInt(figures.size()));
        } else if (figureT.stop()) {
            graphicsContext.setFill(Color.PURPLE);
            graphicsContext.fillRect(figureT.getColumn().getX(), figureT.getColumn().getY(), figureT.getColumn().getWidth(), figureT.getColumn().getHeight());
            graphicsContext.fillRect(figureT.getRow().getX(), figureT.getRow().getY(), figureT.getRow().getWidth(), figureT.getRow().getHeight());

            figureT.setY(-50);
            figureT.setDeltaY(25);
            figure = figures.get(random.nextInt(figures.size()));
        }
    }
}
