import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Tetris extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TETRIS");
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        Canvas canvas = new Canvas(431, 620);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        int startY = 100;
        int startX = 120;
        final int[] countLines = {0};
        final int[] score = {0};
        Random random = new Random();
        final int[] figureNum = {random.nextInt(7)};
        final int[] nextFigureNum = {random.nextInt(7)};
        final Boolean[] stop = {true};

        Integer[][] playingField = new Integer[12][22];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 22; j++) {
                if (i == 0 || j == 0 || j == 21 || i == 11) playingField[i][j] = 10;
                else playingField[i][j] = -1;
            }
        }

        gc.setFill(Color.rgb(20, 20, 30));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.rgb(205, 215, 225));
        gc.fillRect(14, 72, 263, 523);
        gc.fillRect(290, 72, 125, 340);
        gc.setFill(Color.rgb(20, 20, 30));
        gc.fillRect(15, 73, 261, 521);
        gc.fillRect(291, 73, 123, 338);
        gc.setFill(Color.rgb(205, 215, 225));
        javafx.scene.text.Font theFont = javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("TETRIS", 65, 50);
        theFont = javafx.scene.text.Font.font("Roboto", 19);
        gc.setFont(theFont);
        gc.fillText("lines: ", 292, 440);
        gc.fillText("score: ", 292, 460);
        gc.fillText("0", 350, 440);
        gc.fillText("0", 350, 460);

        primaryStage.show();
        Figure current = new Figure(startX, startY);
        current.newFigure(figureNum[0]);
        Figure next = new Figure(327, 123);
        nextFigureNum[0] = random.nextInt(7);
        next.newFigure(nextFigureNum[0]);

        Timeline falling = new Timeline(new KeyFrame(Duration.seconds(0.5), actionEvent -> {
            int piecesInLine;
            int linesPerTurn = 0;
            if (!current.willIntersectDown(playingField)) {
                current.delete(gc);
            }
            if (!current.intersectsDown(playingField)) {
                current.update();
            }
            if (current.intersectsDown(playingField)) {
                playingField[(current.piece1.positionX - 16) / 26 + 1][(current.piece1.positionY - startY) / 26 + 1] = figureNum[0];
                playingField[(current.piece2.positionX - 16) / 26 + 1][(current.piece2.positionY - startY) / 26 + 1] = figureNum[0];
                playingField[(current.piece3.positionX - 16) / 26 + 1][(current.piece3.positionY - startY) / 26 + 1] = figureNum[0];
                playingField[(current.piece4.positionX - 16) / 26 + 1][(current.piece4.positionY - startY) / 26 + 1] = figureNum[0];
                for (int i = 1; i <= 20; i++) {
                    piecesInLine = 0;
                    int imageNum;
                    for (int j = 1; j <= 10; j++)
                        if (playingField[j][i] != -1) {
                            piecesInLine += 1;
                        }
                    if (piecesInLine == 10) {
                        linesPerTurn += 1;
                        for (int j = i; j >= 1; j--) {
                            gc.setFill(Color.rgb(20, 20, 30));
                            gc.fillRect(16, 74, 260, 26 * j);
                            for (int k = 1; k <= 10; k++) {
                                if (j != 1) playingField[k][j] = playingField[k][j - 1];
                                else playingField[k][1] = -1;
                                if (playingField[k][j] != -1) {
                                    imageNum = playingField[k][j] + 1;
                                    gc.drawImage(new Image("square" + imageNum + ".png"), 16 + k * 26 - 26, 74 + j * 26 - 26);
                                }
                            }
                        }
                    }
                }
                current.nextFigure(next);
                next.delete(gc);
                current.moveTo(startX, startY);
                figureNum[0] = nextFigureNum[0];
                nextFigureNum[0] = random.nextInt(7);
                next.newFigure(nextFigureNum[0]);
                if (current.intersectsDown(playingField)) stop[0] = true;
                if (!stop[0]) next.render(gc);
            }
            countLines[0] += linesPerTurn;
            if (linesPerTurn == 1) score[0] += 100;
            else if (linesPerTurn == 2) score[0] += 300;
            else if (linesPerTurn == 3) score[0] += 700;
            else if (linesPerTurn == 4) score[0] += 1500;
            gc.setFill(Color.rgb(20, 20, 30));
            gc.fillRect(350, 425, 100, 50);
            gc.setFill(Color.rgb(205, 215, 225));
            gc.fillText(String.valueOf(countLines[0]), 350, 440);
            gc.fillText(String.valueOf(score[0]), 350, 460);
            if (!stop[0]) current.render(gc);
        }));
        falling.setCycleCount(Timeline.INDEFINITE);

        ArrayList<String> input = new ArrayList<>();

        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });
        Timeline keyboardControl = new Timeline(new KeyFrame(Duration.seconds(0.1), actionEvent -> {
            if (!stop[0]) current.delete(gc);
            if (falling.getStatus() == Animation.Status.RUNNING) {
                gc.setFill(Color.rgb(20, 20, 30));
                gc.fillRect(292, 480, 200, 50);
                gc.setFill(Color.rgb(205, 215, 225));
                gc.fillText("press spacebar\nto stop", 292, 500);

                if (input.contains("RIGHT") && !current.intersectsRight(playingField)) {
                    current.moveRight();
                }
                if (input.contains("LEFT") && !current.intersectsLeft(playingField)) {
                    current.moveLeft();
                }
                if (input.contains("DOWN") && !current.willIntersectDown(playingField)) {
                    current.moveDown();
                }
                if (input.contains("UP")) {
                /*if (!current.intersectsLeft(playingField) && !current.intersectsRight(playingField)
                        && !current.intersectsDown(playingField)) current.rotate(figureNum[0]);
                if (current.intersectsLeft(playingField) && !current.intersectsRight(playingField)) current.moveRight();
                if (current.intersectsRight(playingField)&& !current.intersectsLeft(playingField)) current.moveLeft();*/
                    current.rotate(figureNum[0]);
                }

                if (input.contains("SPACE") || stop[0]) {
                    falling.pause();
                }
            } else {
                gc.setFill(Color.rgb(20, 20, 30));
                gc.fillRect(292, 480, 200, 50);
                gc.setFill(Color.rgb(205, 215, 225));
                gc.fillText("press spacebar\nto start", 292, 500);
                if (input.contains("SPACE")) {
                    stop[0] = false;
                    gc.setFill(Color.rgb(20, 20, 30));
                    gc.fillRect(15, 73, 261, 521);
                    gc.fillRect(291, 73, 123, 338);
                    gc.fillRect(350, 425, 100, 50);
                    gc.setFill(Color.rgb(205, 215, 225));
                    gc.fillText("0", 350, 440);
                    gc.fillText("0", 350, 460);
                    score[0] = 0;
                    countLines[0] = 0;
                    for (int i = 1; i < 11; i++ ) {
                        for (int j = 1; j < 21; j++ ) {
                            playingField[i][j] = -1;
                        }
                    }
                    figureNum[0] = nextFigureNum[0];
                    current.newFigure(figureNum[0]);
                    nextFigureNum[0] = random.nextInt(7);
                    next.newFigure(nextFigureNum[0]);
                    next.render(gc);
                    current.render(gc);
                    falling.play();
                }
            }
            if (!stop[0]) current.render(gc);
        }));
        keyboardControl.setCycleCount(Timeline.INDEFINITE);
        keyboardControl.play();
    }
}