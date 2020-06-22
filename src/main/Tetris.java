import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        primaryStage.getIcons().add(new Image("/icon.png"));


        int pieceSize = 25;
        int startY = (pieceSize+1)*4+1;
        int startX = (pieceSize+1)*5+1;
        final int[] countLines = {0};
        final int[] score = {0};
        Random random = new Random();
        final int[] figureNum = {random.nextInt(7)};
        final int[] nextFigureNum1 = {random.nextInt(7)};
        final int[] nextFigureNum2 = {random.nextInt(7)};
        final int[] nextFigureNum3 = {random.nextInt(7)};
        final Boolean[] stop = {true};

        Canvas canvas = new Canvas(17*(pieceSize+1)+1, 24*(pieceSize+1)+1);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Integer[][] playingField = new Integer[12][22];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 22; j++) {
                if (i == 0 || j == 0 || j == 21 || i == 11) playingField[i][j] = 10;
                else playingField[i][j] = -1;
            }
        }

        gc.setFill(Color.rgb(20, 20, 30));
        gc.fillRect(0, 0, canvas.getWidth()+1, canvas.getHeight()+1);
        gc.setFill(Color.rgb(205, 215, 225));
        gc.fillRect(pieceSize, (pieceSize+1)*3, (pieceSize+1)*10+3, (pieceSize+1)*20+3);
        gc.setFill(Color.rgb(20, 20, 30));
        gc.fillRect(pieceSize+1, (pieceSize+1)*3+1, (pieceSize+1)*10+1, (pieceSize+1)*20+1);
        gc.setFill(Color.rgb(205, 215, 225));
        javafx.scene.text.Font theFont = javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, (pieceSize+1)*2);
        gc.setFont(theFont);
        gc.fillText("TETRIS", pieceSize+2,(pieceSize+1)*2-1 );
        theFont = javafx.scene.text.Font.font("Roboto", 19);
        gc.setFont(theFont);
        gc.fillText("lines: ", (pieceSize+1)*12+1, (pieceSize+1)*19-1);
        gc.fillText("score: ", (pieceSize+1)*12+1, (pieceSize+1)*20-1);
        gc.fillText("0", (pieceSize+1)*14+1, (pieceSize+1)*19-1);
        gc.fillText("0", (pieceSize+1)*14+1, (pieceSize+1)*20-1);

        primaryStage.show();
        Figure current = new Figure(startX, startY, pieceSize);
        current.newFigure(figureNum[0]);
        Figure next1 = new Figure(13*(pieceSize+1)+1, 4*(pieceSize+1)+1, pieceSize);
        Figure next2 = new Figure(13*(pieceSize+1)+1, 8*(pieceSize+1)+1, pieceSize);
        Figure next3 = new Figure(13*(pieceSize+1)+1, 12*(pieceSize+1)+1, pieceSize);
        nextFigureNum1[0] = random.nextInt(7);
        nextFigureNum2[0] = random.nextInt(7);
        nextFigureNum3[0] = random.nextInt(7);
        next1.newFigure(nextFigureNum1[0]);
        next2.newFigure(nextFigureNum2[0]);
        next2.newFigure(nextFigureNum3[0]);

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
                playingField[(current.piece1.positionX - 1) / (pieceSize+1)][(current.piece1.positionY - startY) / (pieceSize+1) + 1] = figureNum[0];
                playingField[(current.piece2.positionX - 1) / (pieceSize+1)][(current.piece2.positionY - startY) / (pieceSize+1) + 1] = figureNum[0];
                playingField[(current.piece3.positionX - 1) / (pieceSize+1)][(current.piece3.positionY - startY) / (pieceSize+1) + 1] = figureNum[0];
                playingField[(current.piece4.positionX - 1) / (pieceSize+1)][(current.piece4.positionY - startY) / (pieceSize+1) + 1] = figureNum[0];
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
                            gc.fillRect(pieceSize+1, (pieceSize+1)*3+1, (pieceSize+1)*10, (pieceSize+1) * j);
                            for (int k = 1; k <= 10; k++) {
                                if (j != 1) playingField[k][j] = playingField[k][j - 1];
                                else playingField[k][1] = -1;
                                if (playingField[k][j] != -1) {
                                    imageNum = playingField[k][j] + 1;
                                    gc.drawImage(new Image("square" + imageNum + ".png"), k * (pieceSize+1)+1, (j+2) * (pieceSize+1)+1);
                                }
                            }
                        }
                    }
                }
                current.nextFigure(next1);
                next1.delete(gc);
                next2.delete(gc);
                next3.delete(gc);
                next1.nextFigure(next2);
                next2.nextFigure(next3);
                current.moveTo(startX, startY);
                figureNum[0] = nextFigureNum1[0];
                nextFigureNum1[0] = nextFigureNum2[0];
                nextFigureNum2[0] = nextFigureNum3[0];
                nextFigureNum3[0] = random.nextInt(7);
                next1.newFigure(nextFigureNum1[0]);
                next2.newFigure(nextFigureNum2[0]);
                next3.newFigure(nextFigureNum3[0]);
                if (current.intersectsDown(playingField)) stop[0] = true;
                if (!stop[0]) {
                    next1.render(gc);
                    next2.render(gc);
                    next3.render(gc);
                }
            }
            countLines[0] += linesPerTurn;
            if (linesPerTurn == 1) score[0] += 100;
            else if (linesPerTurn == 2) score[0] += 300;
            else if (linesPerTurn == 3) score[0] += 700;
            else if (linesPerTurn == 4) score[0] += 1500;
            gc.setFill(Color.rgb(20, 20, 30));
            gc.fillRect((pieceSize+1)*14+1, (pieceSize+1)*18-1, 4*(pieceSize+1), 2* (pieceSize+1));
            gc.setFill(Color.rgb(205, 215, 225));
            gc.fillText(String.valueOf(countLines[0]), (pieceSize+1)*14+1, (pieceSize+1)*19-1);
            gc.fillText(String.valueOf(score[0]), (pieceSize+1)*14+1, (pieceSize+1)*20-1);
            if (!stop[0]) current.render(gc);
        }));
        falling.setCycleCount(Timeline.INDEFINITE);

        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> inputTyped = new ArrayList<>();

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
                gc.fillRect((pieceSize+1)*12+1, (pieceSize+1)*21-1, (pieceSize+1)*5, (pieceSize+1)*3);
                gc.setFill(Color.rgb(205, 215, 225));
                gc.setFont(javafx.scene.text.Font.font("Roboto", 19));
                gc.fillText("press space\nto stop", (pieceSize+1)*12+1, (pieceSize+1)*21-1);

                if (input.contains("RIGHT") && !current.intersectsRight(playingField)) {
                    current.moveRight();
                }
                if (input.contains("LEFT") && !current.intersectsLeft(playingField)) {
                    current.moveLeft();
                }
                if (input.contains("DOWN") && !current.willIntersectDown(playingField)) {
                    current.moveDown();
                }
                if (input.contains("UP") && figureNum[0] != 0) {
                    current.rotate(figureNum[0], playingField);
                    if (!current.possibleToRotate(playingField)) {
                        current.moveRight();
                        if (!current.possibleToRotate(playingField)) {
                            current.moveLeft();
                            current.moveLeft();
                            if (!current.possibleToRotate(playingField)) {
                                current.moveRight();
                                current.moveUp();
                                if (!current.possibleToRotate(playingField)) {
                                    current.moveDown();
                                    current.rotate(figureNum[0], playingField);
                                    current.rotate(figureNum[0], playingField);
                                    current.rotate(figureNum[0], playingField);
                                }
                            }
                        }
                    }
                }

                if (input.contains("SPACE") || stop[0]) {
                    gc.setFill(Color.rgb(154, 0, 0));
                    gc.setFont(javafx.scene.text.Font.font("Roboto", FontWeight.BOLD, (pieceSize+1)*1.3));
                    if (stop[0]) gc.fillText("GAME OVER", 2*(pieceSize+1), 11*(pieceSize+1));
                    falling.pause();
                }
            } else {
                gc.setFill(Color.rgb(20, 20, 30));
                gc.fillRect((pieceSize+1)*12+1, (pieceSize+1)*21-1, (pieceSize+1)*5, (pieceSize+1)*3);
                gc.setFill(Color.rgb(205, 215, 225));
                gc.setFont(javafx.scene.text.Font.font("Roboto", 19));
                gc.fillText("press space\nto start", (pieceSize+1)*12+1, (pieceSize+1)*21-1);
                if (input.contains("SPACE")) {
                    stop[0] = false;
                    gc.setFill(Color.rgb(20, 20, 30));
                    gc.fillRect(pieceSize+1, (pieceSize+1)*3+1, (pieceSize+1)*10+1, (pieceSize+1)*20+1);
                    gc.fillRect(12*(pieceSize+1), (pieceSize+1)*4+1, (pieceSize+1)*4, (pieceSize+1)*12);
                    gc.fillRect(350, 425, 100, 50);
                    gc.setFill(Color.rgb(205, 215, 225));
                    gc.fillText("0", (pieceSize+1)*14+1, (pieceSize+1)*19-1);
                    gc.fillText("0", (pieceSize+1)*14+1, (pieceSize+1)*20-1);
                    score[0] = 0;
                    countLines[0] = 0;
                    for (int i = 1; i < 11; i++ ) {
                        for (int j = 1; j < 21; j++ ) {
                            playingField[i][j] = -1;
                        }
                    }
                    figureNum[0] = nextFigureNum1[0];
                    current.newFigure(figureNum[0]);
                    nextFigureNum1[0] = nextFigureNum2[0];
                    nextFigureNum2[0] = nextFigureNum3[0];
                    nextFigureNum3[0] = random.nextInt(7);
                    next1.newFigure(nextFigureNum1[0]);
                    next2.newFigure(nextFigureNum2[0]);
                    next3.newFigure(nextFigureNum3[0]);
                    next1.render(gc);
                    next2.render(gc);
                    next3.render(gc);
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