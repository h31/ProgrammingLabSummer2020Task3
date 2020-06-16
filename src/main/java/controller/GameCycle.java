package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;

public class GameCycle extends GameField {
    Figure figure = new Figure();
    Button startButton = new Button("START");

    public GameCycle() {
        //стиль кнопки
        startButton.setStyle("-fx-background-color: #00ff00");
        startButton.setFont(new Font(20));
        startButton.setLayoutY(150);
        startButton.setLayoutX(450);

        buttonController();

        getChildren().addAll(figure, startButton);
    }

    /**
     * Взаимодействие пользователя с кнопкой
     */
    public void buttonController() {
        //эффекты для кнопки
        Lighting lighting = new Lighting();
        DropShadow shadow = new DropShadow();
        startButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> startButton.setEffect(shadow));
        startButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> startButton.setEffect(null));

        //запуск игры по нажатию на кнпку START
        startButton.setOnAction(actionEvent -> {
            startButton.setEffect(lighting);
            figure.setShape();
            keyController();
        });

        startButton.isPressed();
    }

    /**
     * Взаимодействие пользователя с клавиатурой
     */
    public void keyController() {
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(500), t -> {
            figure.moveDown();

            //обработка нажатий с клаввиатуры
            startButton.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) {
                    figure.moveLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    figure.moveRight();
                } else if (event.getCode() == KeyCode.UP) {
                    figure.turningShape();
                } else if (event.getCode() == KeyCode.DOWN) {
                    figure.moveDown();
                }
            });

            if (figure.stop()) {
                figure.setShape();
            } else {
                clearRow();
                drawField();
                drawFigure();
            }

            if (endGame()) {
                getEndGame().setVisible(true);
            }

        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }
}