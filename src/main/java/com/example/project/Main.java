package com.example.project;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;



public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas(510, 600);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<>();

        scene.setOnKeyPressed(
                event -> {
                    String code = event.getCode().toString();
                    if (!input.contains(code)) input.add(code);
                });
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // заполняем...
        gc.setFill(Color.LAVENDER);
        gc.fillRect(300, 0, 210, 600);
        gc.setFill(Color.WHITE);
        gc.fillRect(330, 30, 150, 180);

        // создаем следующую фигуру
        int[] random2 = {1 + (int) (Math.random() * 7)};
        Figure figure2 = new Figure(new int[]{390, 390, 390, 390}, new int[]{60, 90, 120, 150});
        figure2.startIndex(gc, random2[0]);
        figure2.draw(gc);

        // создаем массив кубиков, где последний столбик - кол-во заполненных кубиков
        Integer[][] array = new Integer[13][21];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if (i == 0 || i == 11 || j == 20) array[i][j] = -8;
                else array[i][j] = 0;
            }
        }
        int[] lines = {0};
        final int[] score = {0};

        gc.setFill(Color.BLUEVIOLET);
        Font theFont;
        theFont = Font.font(25);
        gc.setFont(theFont);
        gc.fillText("next", 375, 240);
        gc.fillText("score", 375, 300);
        gc.fillText("0", 375, 330);

        // создаем текущую фигуру
        int[] random1 = {1 + (int) (Math.random() * 7)};
        Figure figure1 = new Figure(new int[]{150, 150, 150, 150}, new int[]{0, 30, 60, 90});
        figure1.startIndex(gc, random1[0]);
        figure1.draw(gc);

        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), actionEvent -> {
            if (figure1.canDown(array)) {

                figure1.clear(gc);
                Figure.y[0] += 30;
                Figure.y[1] += 30;
                Figure.y[2] += 30;
                Figure.y[3] += 30;
                figure1.draw(gc);
            } else {

                figure1.inArray(array, random1[0]);
                random1[0] = random2[0];

                while (random2[0] == random1[0])
                    random2[0] = 1 + (int) (Math.random() * 7);

                gc.setFill(Color.WHITE);
                gc.fillRect(330, 30, 150, 180);

                figure2.for2();
                figure2.startIndex(gc, random2[0]);
                figure2.draw(gc);


                for (int jj = 0; jj < 20; jj++) {
                    if (array[12][jj] == 10) {
                        lines[0] += 1;
                        for (int j = jj; j > 0; j--) {
                            for (int i = 10; i > 0; i--) {
                                gc.clearRect((i - 1) * 30, (j * 30), 30, 30);
                                if (array[i][j - 1] < 0) {
                                    if (array[i][j - 1] == -1) {
                                        gc.setFill(Color.BLUEVIOLET);
                                    }
                                    if (array[i][j - 1] == -2) {
                                        gc.setFill(Color.INDIGO);
                                    }
                                    if (array[i][j - 1] == -3) {
                                        gc.setFill(Color.DARKVIOLET);
                                    }
                                    if (array[i][j - 1] == -4) {
                                        gc.setFill(Color.MEDIUMPURPLE);
                                    }
                                    if (array[i][j - 1] == -5) {
                                        gc.setFill(Color.DARKMAGENTA);
                                    }
                                    if (array[i][j - 1] == -6) {
                                        gc.setFill(Color.MEDIUMORCHID);
                                    }
                                    if (array[i][j - 1] == -7) {
                                        gc.setFill(Color.PURPLE);
                                    }
                                    gc.fillRect((i - 1) * 30, (j) * 30, 30, 30);


                                }
                                array[i][j] = array[i][j - 1];
                                array[12][j] = array[12][j-1];
                            }

                        }
                        if (lines[0] == 1) score[0] += 100;
                        if (lines[0] == 2) score[0] += 300;
                        if (lines[0] == 3) score[0] += 700;
                        if (lines[0] == 4) score[0] += 1500;
                        lines[0] = 0;
                        gc.setFill(Color.LAVENDER);
                        gc.fillRect(375, 300, 100, 30);
                        gc.setFill(Color.BLUEVIOLET);
                        gc.fillText(String.valueOf(score[0]), 375, 330);
                    }

                }

                gc.setFill(Color.BLUEVIOLET);
                figure1.for1();
                figure1.startIndex(gc, random1[0]);
                figure1.draw(gc);

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline keyboardControl = new Timeline(new KeyFrame(Duration.seconds(0.2), actionEvent -> {
            if (input.contains("RIGHT") && figure1.canRight(array)) {
                figure1.clear(gc);
                Figure.x[0] += 30;
                Figure.x[1] += 30;
                Figure.x[2] += 30;
                Figure.x[3] += 30;
                figure1.draw(gc);
            }
            if (input.contains("LEFT") && figure1.canLeft(array)) {
                figure1.clear(gc);
                Figure.x[0] -= 30;
                Figure.x[1] -= 30;
                Figure.x[2] -= 30;
                Figure.x[3] -= 30;
                figure1.draw(gc);
            }
            if (input.contains("DOWN") && figure1.canDown(array)) {
                figure1.clear(gc);
                Figure.y[0] += 30;
                Figure.y[1] += 30;
                Figure.y[2] += 30;
                Figure.y[3] += 30;
                figure1.draw(gc);
            }
            if (input.contains("UP")) {
                
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
