package view;

import controller.GameCycle;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.GameField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tetris extends Application {
    private  final Canvas canvas = new Canvas(400, 500);
    private final Pane gameRoot = new Pane();
    private final Label scoreLabel = new Label("Score");
    Scene scene = new Scene(gameRoot, 600, 500);

    public  Canvas getCanvas() {
        return canvas;
    }

    public Tetris()  {
        canvas.getGraphicsContext2D();
    }

    @Override
    public void start(final Stage primaryStage) throws FileNotFoundException {
        GameField gameField = new GameField();
        GameCycle gameCycle = new GameCycle();
        //установка картинки на задний фон
        Image image = new Image(new FileInputStream("C:\\Users\\timco\\IdeaProjects\\ProgrammingLabSummer2020Task3\\src\\main\\resources\\image.jpg"),
                600,500,false,true);

        BackgroundSize backgroundSize = new BackgroundSize(500, 600,false,false,true,false);

        Background imageBack = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,backgroundSize));

        //эффекты для надписи
        scoreLabel.setText("Score");
        scoreLabel.setTextFill(Color.YELLOW);
        scoreLabel.setFont(new Font(23));
        scoreLabel.setLayoutX(420);
        scoreLabel.setLayoutY(20);

        primaryStage.setResizable(false);
        primaryStage.setTitle("TETRIS");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameRoot.setBackground(imageBack);
        gameRoot.getChildren().addAll(scoreLabel, gameCycle, gameField);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
