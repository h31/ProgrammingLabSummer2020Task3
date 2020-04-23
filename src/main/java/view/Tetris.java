package view;

import controller.GameCycle;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.GameField;


public class Tetris extends Application {
    GraphicsContext graphicsContext;
    Canvas canvas = new Canvas(400, 500);
    Label score = new Label("Score");
    private final Pane gameRoot = new Pane();
    GameCycle gameCycle;
    GameField gameField;
    Scene scene = new Scene(gameRoot, 600, 500);

    public Label getScore() {
        return score;
    }

    public void setScore(Label score) {
        this.score = score;
    }


    private final int cell = 25;

    public int getCell() {
        return cell;
    }

    private double lowBound = 500;

    public double getLowBound() {
        return lowBound;
    }

    public void setLowBound(double lowBound) {
        this.lowBound = lowBound;
    }

    double leftBound = 0;

    public double getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(double leftBound) {
        this.leftBound = leftBound;
    }

    double rightBound = 350;

    public void setRightBound(double rightBound) {
        this.rightBound = rightBound;
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public Tetris() {
        graphicsContext = canvas.getGraphicsContext2D();

    }

    public void paint() {
        int widthCell = 20;
        for (int i = 0; i < widthCell * cell; i += cell) {
            graphicsContext.strokeLine(i, 0, i, widthCell * cell);
        }

        int heightCell = 20;
        for (int y = 0; y < heightCell * cell; y += cell) {
            graphicsContext.strokeLine(0, y, heightCell * cell, y);
        }
    }


    @Override
    public void start(final Stage primaryStage) {
        gameField = new GameField();
        gameCycle = new GameCycle();

        graphicsContext = canvas.getGraphicsContext2D();

        score.setFont(new Font(20));
        score.setLayoutX(470);
        score.setLayoutY(20);

        primaryStage.setTitle("TETRIS");
        primaryStage.setScene(scene);
        primaryStage.show();
        paint();

        gameRoot.getChildren().addAll(canvas, score, gameCycle,gameField);

    }

    public static void main(String[] args) {
        launch(args);
    }


}
