package view;

import controller.GameCycle;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.GameField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tetris extends Application {
    private final Canvas canvas = new Canvas(400, 600);
    private final Pane gameRoot = new Pane();
    private final Scene scene = new Scene(gameRoot, 600, 600);

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void start(final Stage primaryStage) throws FileNotFoundException {
        GameField gameField = new GameField();
        GameCycle gameCycle = new GameCycle();
        GameFieldView gameFieldView = new GameFieldView();

        //установка картинки на задний фон
        Image image = new Image(new FileInputStream("src\\main\\resources\\image.jpg"),
                600, 600, false, true);
        BackgroundSize backgroundSize = new BackgroundSize(700, 600, false, false, true, false);
        Background imageBack = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, backgroundSize));

        //иконка для игры
        primaryStage.getIcons().add(new Image(new FileInputStream("src\\main\\resources\\icon.png")));

        //настройка подмостков
        primaryStage.setResizable(false);
        primaryStage.setTitle("TETRIS");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameRoot.setBackground(imageBack);
        gameRoot.getChildren().addAll(gameCycle, gameField, gameFieldView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
