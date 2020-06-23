package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

public class TetrisView extends Application {
    public static void main(String[] args) { Application.launch(args); }

    public int fieldWidth = 510;
    public int width = fieldWidth - 210;
    public int height = 600;
    public int[] randomCurrentFigure = {1 + (int) (Math.random() * 7)};
    public int[] randomNextFigure = {1 + (int) (Math.random() * 7)};
    public int iArray = width / 30;
    public int jArray = height / 30;

    public int[] filled() {
        int[] filled = new int[jArray];
        for (int i = 0; i < jArray; i++) filled[i] = 0;
        return filled;
    }

    public Color[][] array() {
        Color[][] array = new Color[iArray][jArray];
        for (int i = 0; i < iArray; i++) {
            for (int j = 0; j < jArray; j++) {
                array[i][j] = null;
            }
        }
        return array;
    }

    public void start(Stage stage) {
        final TetrisController controller = new TetrisController();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas(fieldWidth, height);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LAVENDER);
        gc.fillRect(width, 0, 210, height);
        gc.setFill(Color.WHITE);
        gc.fillRect(width + 30, 30, 150, 180);

        int xText = width + 75;
        gc.setFill(Color.BLUEVIOLET);
        Font theFont;
        theFont = Font.font(25);
        gc.setFont(theFont);
        gc.fillText("next", xText, 240);
        gc.fillText("score", xText, 300);
        gc.fillText("0", xText, 330);

        gc.setFill(controller.color(randomNextFigure[0]));
        controller.createFigure(gc, controller.xNextFigure, 60, randomNextFigure[0]);
        gc.setFill(controller.color(randomCurrentFigure[0]));
        controller.createFigure(gc, controller.xCurrentFigure, 0, randomCurrentFigure[0]);


        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), actionEvent -> {
            if (controller.can(0, 1)) {
                controller.transfer(gc, 0, 1);
            } else {
                controller.inArray(randomCurrentFigure[0]);
                randomCurrentFigure[0] = randomNextFigure[0];
                while (randomNextFigure[0] == randomCurrentFigure[0])
                    randomNextFigure[0] = 1 + (int) (Math.random() * 7);

                gc.setFill(Color.WHITE);
                gc.fillRect(width + 30, 30, 150, 180);
                gc.setFill(controller.color(randomNextFigure[0]));
                controller.createFigure(gc, controller.xNextFigure, 60, randomNextFigure[0]);

                controller.deleteLines(gc);

                gc.setFill(controller.color(randomCurrentFigure[0]));
                controller.createFigure(gc, controller.xCurrentFigure, 0, randomCurrentFigure[0]);

                if (!controller.can(0, 1)) {
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
            if (input.contains(KeyCode.RIGHT) && controller.can(1, 0)) {
                controller.transfer(gc, 1, 0);
            }
            if (input.contains(KeyCode.LEFT) && controller.can(-1, 0)) {
                controller.transfer(gc, -1, 0);
            }
            if (input.contains(KeyCode.DOWN) && controller.can(0, 1)) {
                controller.transfer(gc, 0, 1);
            }
            if (input.contains(KeyCode.UP)) {
                controller.rotate(gc);
            }
        }));

        keyboardControl.setCycleCount(Timeline.INDEFINITE);
        keyboardControl.play();

        stage.show();
    }
}
