package Project;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;

import java.io.IOException;


public class Controller {
    @FXML
    CheckBox alternative;
    View b = new View();
    private boolean gameStopped = false;

    public Scene control() throws IOException {
        Scene scene = new Scene(b.createParent());
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    if (Model.direction != Model.Direction.DOWN) Model.direction = Model.Direction.UP;
                    break;
                case A:
                    if (Model.direction != Model.Direction.RIGHT) Model.direction = Model.Direction.LEFT;
                    break;
                case S:
                    if (Model.direction != Model.Direction.UP) Model.direction = Model.Direction.DOWN;
                    break;
                case D:
                    if (Model.direction != Model.Direction.LEFT) Model.direction = Model.Direction.RIGHT;
                    break;
                case ENTER:
                    startGame();
                    break;
                case ESCAPE:
                    returnToMainMenu();
                    break;
                case SPACE:
                    if (!gameStopped) {
                        gameStopped = true;
                        View.timeline.stop();
                        gameStopped = true;
                    } else {
                        gameStopped = false;
                        View.timeline.play();
                        gameStopped = false;
                    }

            }
        });
        return scene;
    }

    public void startNewGame() throws IOException {
        if (alternative.isSelected()) Model.alternativeGame = true;
        else Model.alternativeGame = false;
        this.b = new View();
        Main.primaryStage.setScene(control());
        startGame();
    }

    public void returnToMainMenu() {
        View.timeline.stop();
        View.timeline.getKeyFrames().remove(0);
        Model.snake.clear();
        Main.primaryStage.setScene(Main.mainMenu);
    }

    public void startGame() {
        Model.gameOver = false;
        Model.direction = Model.Direction.RIGHT;
        Model.snake.clear();
        Model.barriers.clear();
        Model.score = 0;
        Model.snake.add(b.createSnakePart());
        if (Model.alternativeGame) {
            for (int i = 0; i < 5; i++)
                Model.snake.add(b.createSnakePart());
        }
        View.timeline.play();
    }

    public static void gameOver() {
        Model.gameOver = true;
        View.timeline.stop();
        Model.snake.clear();
    }
}