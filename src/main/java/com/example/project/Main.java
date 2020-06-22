package com.example.project;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        //размеры всей игры
        int fieldWidth = 510;
        int width = fieldWidth - 210;
        int height = 600;

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas(fieldWidth, height);
        root.getChildren().add(canvas);

        // создаем массив кубиков, где последний столбик - кол-во заполненных кубиков
        // предварительно вычислим размеры массива
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int iArray = width / 30 + 1;
        int jArray = height / 30;
        Integer[][] array = new Integer[iArray][jArray];
        for (int i = 0; i < iArray; i++) {
            for (int j = 0; j < jArray; j++) {
                if (i == iArray - 1) array[i][j] = -10;
                else
                    array[i][j] = 0;
            }
        }

        // заполняем...
        gc.setFill(Color.LAVENDER);
        gc.fillRect(width, 0, 210, height);
        gc.setFill(Color.WHITE);
        gc.fillRect(width + 30, 30, 150, 180);

        // создаем следующую фигуру
        int[] randomNextFigure = {1 + (int) (Math.random() * 7)};
        int xNextFigure = width + 90;
        Figure nextFigure = new Figure(xNextFigure, 60);
        nextFigure.startIndex(gc, randomNextFigure[0]);
        nextFigure.draw(gc);

        int[] lines = {0};
        final int[] score = {0};
        int xText = width + 75;
        gc.setFill(Color.BLUEVIOLET);
        Font theFont;
        theFont = Font.font(25);
        gc.setFont(theFont);
        gc.fillText("next", xText, 240);
        gc.fillText("score", xText, 300);
        gc.fillText("0", xText, 330);

        // создаем текущую фигуру
        int[] randomCurrentFigure = {1 + (int) (Math.random() * 7)};
        int xCurrentFigure = iArray / 2 * 30;
        Figure currentFigure = new Figure(xCurrentFigure, 0);
        currentFigure.startIndex(gc, randomCurrentFigure[0]);
        currentFigure.draw(gc);

        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), actionEvent -> {
            if (currentFigure.can(array, 0, 1)) {
                currentFigure.transfer(gc, 0, 1);
            } else {

                currentFigure.inArray(array, randomCurrentFigure[0]);
                randomCurrentFigure[0] = randomNextFigure[0];

                while (randomNextFigure[0] == randomCurrentFigure[0])
                    randomNextFigure[0] = 1 + (int) (Math.random() * 7);

                gc.setFill(Color.WHITE);
                gc.fillRect(width + 30, 30, 150, 180);

                Figure.x[0] = xNextFigure;
                Figure.y[0] = 60;
                nextFigure.startIndex(gc, randomNextFigure[0]);
                nextFigure.draw(gc);


                for (int jj = 0; jj < jArray; jj++) {

                    if (array[iArray - 1][jj] == -10 * iArray) {
                        lines[0] += 1;
                        for (int j = jj; j > 0; j--) {
                            for (int i = iArray - 2; i >= 0; i--) {
                                gc.clearRect(i * 30, j * 30, 30, 30);
                                gc.setFill(Color.WHITE);
                                if (array[i][j - 1] == 1) {
                                    gc.setFill(Color.BLUEVIOLET);
                                }
                                if (array[i][j - 1] == 2) {
                                    gc.setFill(Color.INDIGO);
                                }
                                if (array[i][j - 1] == 3) {
                                    gc.setFill(Color.DARKVIOLET);
                                }
                                if (array[i][j - 1] == 4) {
                                    gc.setFill(Color.MEDIUMPURPLE);
                                }
                                if (array[i][j - 1] == 5) {
                                    gc.setFill(Color.DARKMAGENTA);
                                }
                                if (array[i][j - 1] == 6) {
                                    gc.setFill(Color.MEDIUMORCHID);
                                }
                                if (array[i][j - 1] == 7) {
                                    gc.setFill(Color.PURPLE);
                                }
                                gc.fillRect((i) * 30, (j) * 30, 30, 30);

                                array[i][j] = array[i][j - 1];
                            }
                            array[iArray - 1][j] = array[iArray - 1][j - 1];
                        }
                    }

                }
                if (lines[0] == 1) score[0] += 100;
                if (lines[0] == 2) score[0] += 300;
                if (lines[0] == 3) score[0] += 700;
                if (lines[0] == 4) score[0] += 1500;
                lines[0] = 0;
                gc.setFill(Color.LAVENDER);
                gc.fillRect(width + 75, 300, 100, 30);
                gc.setFill(Color.BLUEVIOLET);
                gc.fillText(String.valueOf(score[0]), width + 75, 330);


                Figure.x[0] = xCurrentFigure;
                Figure.y[0] = 0;
                currentFigure.startIndex(gc, randomCurrentFigure[0]);
                currentFigure.draw(gc);
                if (!currentFigure.can(array, 0, 1)) {
                    gc.clearRect(0, 0, fieldWidth, height);
                    gc.setFill(Color.BLUEVIOLET);
                    int textX = fieldWidth / 2 - 100;
                    int textY = height / 2 - 30;
                    gc.fillText("ВЫ ПРОИГРАЛИ", textX, textY);
                }

            }
        }));
        List<KeyCode> input = new ArrayList<>();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        scene.setOnKeyPressed(
                event -> {
                    KeyCode code = event.getCode();
                    if (!input.contains(code)) input.add(code);
                });
        scene.setOnKeyReleased(
                e -> {
                    KeyCode code = e.getCode();
                    input.remove(code);
                });

        Timeline keyboardControl = new Timeline(new KeyFrame(Duration.seconds(0.2), actionEvent -> {
            if (input.contains(KeyCode.RIGHT) && currentFigure.can(array, 1, 0)) {
                currentFigure.transfer(gc, 1, 0);
            }
            if (input.contains(KeyCode.LEFT) && currentFigure.can(array, -1, 0)) {
                currentFigure.transfer(gc, -1, 0);
            }
            if (input.contains(KeyCode.DOWN) && currentFigure.can(array, 0, 1)) {
                currentFigure.transfer(gc, 0, 1);
            }
            if (input.contains(KeyCode.UP)) {
                currentFigure.rotate(gc, array);
            }
        }));

        keyboardControl.setCycleCount(Timeline.INDEFINITE);
        keyboardControl.play();

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
