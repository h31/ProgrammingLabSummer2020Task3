package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;
import view.Tetris;


public class GameCycle extends GameField {
    Figure shape = new Figure();
    private int keyPressedCount = 0;//отсчет нажатий клавиши

    Button startButton = new Button("START");
    Button pauseButton = new Button("PAUSE");

    public GameCycle() {

        Tetris tetris = new Tetris();

        startButton.setStyle("-fx-background-color: #00ff00");
        startButton.setFont(new Font(20));
        startButton.setLayoutY(150);
        startButton.setLayoutX(450);

        pauseButton.setStyle("-fx-background-color: #ffa500");
        pauseButton.setFont(new Font(20));
        pauseButton.setLayoutY(300);
        pauseButton.setLayoutX(450);

        //эффекты для кнопок
        DropShadow shadow = new DropShadow();

        startButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> startButton.setEffect(shadow));

        startButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> startButton.setEffect(null));

        shape.setShape();
        keyController();
        getChildren().addAll(shape, tetris.getCanvas(), startButton);
    }

    public void keyController() {
        /**  Lighting lighting = new Lighting();

         startButton.setOnAction(actionEvent -> {
         startButton.setEffect(lighting);

         });*/

        Timeline loop = new Timeline(new KeyFrame(Duration.millis(180), t -> {
            shape.moveDown();

            //обработка нажатий с клаввиатуры
            startButton.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) {
                    shape.moveLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    shape.moveRight();
                }
                else if (event.getCode() == KeyCode.UP) {
                    shape.turningShape();
                }
            });

            if (shape.stop()) {
                shape.setShape();

            } else {
                clearRow();
                drawField();
                drawFigure();
            }

            if (endGame()) {
                getEndGame().setVisible(true);
            }
            //проверка на окончание игры
            /**     if (!endGame()) {
             clearRow();
             drawFigure();
             } else {
             getEndGame().setVisible(true);
             if (startButton.isPressed()) {
             repaintField();
             getEndGame().setVisible(false);
             getScore().setText("0");
             }
             }*/
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

}