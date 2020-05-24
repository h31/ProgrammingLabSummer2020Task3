package Project;

import javafx.scene.Scene;

public class Controller {
    View b = new View();
    private boolean gameStopped = false;

    public Scene control() {
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

    public void startNewGame() {
        this.b = new View();
        Main.primaryStage.setScene(control());
        startGame();
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