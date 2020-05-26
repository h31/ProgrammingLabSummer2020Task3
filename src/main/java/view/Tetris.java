package view;

import controller.GameCycle;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.GameField;

public class Tetris extends Application {
    private final Canvas canvas = new Canvas(400, 500);
    private final Pane gameRoot = new Pane();
    private final Label scoreLabel = new Label("Score");
    Scene scene = new Scene(gameRoot, 600, 500);

    public Canvas getCanvas() {
        return canvas;
    }

    public Tetris() {
        canvas.getGraphicsContext2D();
    }

    @Override
    public void start(final Stage primaryStage) {
        GameField gameField = new GameField();
        GameCycle gameCycle = new GameCycle();

        scoreLabel.setText("Score");
        scoreLabel.setTextFill(Color.BLUE);
        scoreLabel.setFont(new Font(21));
        scoreLabel.setLayoutX(420);
        scoreLabel.setLayoutY(20);

        primaryStage.setResizable(false);
        primaryStage.setTitle("TETRIS");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameRoot.getChildren().addAll(scoreLabel, gameCycle, gameField);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
