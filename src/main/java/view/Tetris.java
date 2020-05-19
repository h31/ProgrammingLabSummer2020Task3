package view;

import controller.GameCycle;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.GameField;

public class Tetris extends Application {

    GraphicsContext graphicsContext;
    Canvas canvas = new Canvas(400, 500);
    Label scoreLabel = new Label();
    private final Pane gameRoot = new Pane();
    GameCycle gameCycle;
    GameField gameField;
    Scene scene = new Scene(gameRoot, 600, 500);

    public Canvas getCanvas() {
        return canvas;
    }

    public Tetris() {
        graphicsContext = canvas.getGraphicsContext2D();
    }


    @Override
    public void start(final Stage primaryStage) {
        gameField = new GameField();
        gameCycle = new GameCycle();

      //  graphicsContext = canvas.getGraphicsContext2D();

        scoreLabel.setText("Score");
        scoreLabel.setTextFill(Color.BLUE);
        scoreLabel.setFont(new Font(21));
        scoreLabel.setLayoutX(420);
        scoreLabel.setLayoutY(20);

        primaryStage.setResizable(false);
        primaryStage.setTitle("TETRIS");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameRoot.getChildren().addAll(scoreLabel, gameCycle, gameField,canvas);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
