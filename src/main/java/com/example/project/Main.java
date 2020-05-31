package com.example.project;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        final int[] x1 = {150};
        final int[] y1 = {0};
        final int[] x2 = {150};
        final int[] y2 = {30};
        final int[] x3 = {150};
        final int[] y3 = {60};
        final int[] x4 = {150};
        final int[] y4 = {90};
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas(300, 600);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        if (!input.contains(code)) input.add(code);
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();


            int random = 1 + (int) (Math.random() * 7);
            if (random == 1) {
                gc.setFill(Color.BLUEVIOLET);
            }
            if (random == 2) {
                gc.setFill(Color.INDIGO);
                x4[0] = 180;
                y4[0] = 60;
            }
            if (random == 3) {
                gc.setFill(Color.DARKVIOLET);
                x4[0] = 120;
                y4[0] = 60;
            }
            if (random == 4) {
                gc.setFill(Color.MEDIUMPURPLE);
                x3[0] = 180;
                y3[0] = 0;
                x4[0] = 180;
                y4[0] = 30;
            }
            if (random == 5) {
                gc.setFill(Color.DARKMAGENTA);
                x3[0] = 180;
                y3[0] = 0;
                x4[0] = 120;
                y4[0] = 0;
            }
            if (random == 6) {
                gc.setFill(Color.MEDIUMORCHID);
                x3[0] = 180;
                y3[0] = 0;
                x4[0] = 120;
                y4[0] = 30;
            }
            if (random == 7) {
                gc.setFill(Color.PURPLE);
                x3[0] = 180;
                y3[0] = 30;
                x4[0] = 120;
                y4[0] = 0;
            }
            gc.fillRect(x1[0], y1[0], 30, 30);
            gc.fillRect(x2[0], y2[0], 30, 30);
            gc.fillRect(x3[0], y3[0], 30, 30);
            gc.fillRect(x4[0], y4[0], 30, 30);
            Timeline gameLoop = new Timeline();
            gameLoop.setCycleCount(Timeline.INDEFINITE);

            final long timeStart = System.currentTimeMillis();

            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(1.0),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent ae) {
                            gc.clearRect(x1[0], y1[0], 30, 30);
                            gc.clearRect(x2[0], y2[0], 30, 30);
                            gc.clearRect(x3[0], y3[0], 30, 30);
                            gc.clearRect(x4[0], y4[0], 30, 30);
                            y1[0] += 30;
                            y2[0] += 30;
                            y3[0] += 30;
                            y4[0] += 30;
                            gc.fillRect(x1[0], y1[0], 30, 30);
                            gc.fillRect(x2[0], y2[0], 30, 30);
                            gc.fillRect(x3[0], y3[0], 30, 30);
                            gc.fillRect(x4[0], y4[0], 30, 30);

                        }
                    });
            gameLoop.getKeyFrames().add(keyFrame);
            gameLoop.play();

            gameLoop = new Timeline();
            gameLoop.setCycleCount(Timeline.INDEFINITE);

            KeyFrame kf = new KeyFrame(
                    Duration.seconds(0.2),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent ae) {

                            if (input.contains("LEFT")) {
                                gc.clearRect(x1[0], y1[0], 30, 30);
                                gc.clearRect(x2[0], y2[0], 30, 30);
                                gc.clearRect(x3[0], y3[0], 30, 30);
                                gc.clearRect(x4[0], y4[0], 30, 30);
                                x1[0] -= 30;
                                x2[0] -= 30;
                                x3[0] -= 30;
                                x4[0] -= 30;
                                gc.fillRect(x1[0], y1[0], 30, 30);
                                gc.fillRect(x2[0], y2[0], 30, 30);
                                gc.fillRect(x3[0], y3[0], 30, 30);
                                gc.fillRect(x4[0], y4[0], 30, 30);
                            }
                            if (input.contains("RIGHT")) {
                                gc.clearRect(x1[0], y1[0], 30, 30);
                                gc.clearRect(x2[0], y2[0], 30, 30);
                                gc.clearRect(x3[0], y3[0], 30, 30);
                                gc.clearRect(x4[0], y4[0], 30, 30);
                                x1[0] += 30;
                                x2[0] += 30;
                                x3[0] += 30;
                                x4[0] += 30;
                                gc.fillRect(x1[0], y1[0], 30, 30);
                                gc.fillRect(x2[0], y2[0], 30, 30);
                                gc.fillRect(x3[0], y3[0], 30, 30);
                                gc.fillRect(x4[0], y4[0], 30, 30);
                            }
                            if (input.contains("DOWN")) {
                                gc.clearRect(x1[0], y1[0], 30, 30);
                                gc.clearRect(x2[0], y2[0], 30, 30);
                                gc.clearRect(x3[0], y3[0], 30, 30);
                                gc.clearRect(x4[0], y4[0], 30, 30);
                                y1[0] += 30;
                                y2[0] += 30;
                                y3[0] += 30;
                                y4[0] += 30;
                                gc.fillRect(x1[0], y1[0], 30, 30);
                                gc.fillRect(x2[0], y2[0], 30, 30);
                                gc.fillRect(x3[0], y3[0], 30, 30);
                                gc.fillRect(x4[0], y4[0], 30, 30);
                            }
                            if (input.contains("UP")) {
                                gc.rotate(90);
                                gc.fillRect(x1[0], y1[0], 30, 30);
                                gc.fillRect(x2[0], y2[0], 30, 30);
                                gc.fillRect(x3[0], y3[0], 30, 30);
                                gc.fillRect(x4[0], y4[0], 30, 30);
                            }
                        }
                    });
            gameLoop.getKeyFrames().add(kf);
            gameLoop.play();

        Bounds bounds = canvas.getBoundsInLocal();
        double boundsY = bounds.getMaxY() - 30;
        if (y1[0] >= boundsY && y1[0] > 570) stage.close();
        if (y2[0] >= boundsY || y2[0] > 570) stage.close();
        if (y3[0] >= boundsY || y3[0] > 570) stage.close();
        if (y4[0] >= boundsY || y4[0] > 570) stage.close();

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
