package Project;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Model {
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    static final int bodySize = 50;
    static final int width = bodySize * 17;
    static final int height = bodySize * 14;
    static int score;
    static Direction direction = Direction.RIGHT;
    static ObservableList<Node> snake;
    static ObservableList<Node> barriers;
    static Node meal;
    static Node part;
    static boolean gameOver;
    static boolean alternativeGame;

    public static void classicGame() {
        alternativeGame = false;
        int radius = bodySize / 2;
        score = (snake.size() - 1);

        double bodyX = snake.get(snake.size() - 1).getTranslateX();
        double bodyY = snake.get(snake.size() - 1).getTranslateY();
        snakeMove();
        Node body = snake.get(0);
        if (body.getTranslateX() == (meal.getTranslateX() - radius)
                && body.getTranslateY() == (meal.getTranslateY() - radius)) {
            boolean mealPlacedInSnake = true;
            while (mealPlacedInSnake) {
                mealPlacedInSnake = false;
                meal.setTranslateX((int) (Math.random() * (width - bodySize)) / bodySize * bodySize + radius);
                meal.setTranslateY((int) (Math.random() * (height - bodySize)) / bodySize * bodySize + radius);
                for (Node rect : snake) {
                    if (rect.getTranslateX() == (meal.getTranslateX() - radius)
                            && rect.getTranslateY() == (meal.getTranslateY() - radius))
                        mealPlacedInSnake = true;
                }
            }
            part.setTranslateX(bodyX);
            part.setTranslateY(bodyY);
            snake.add(part);
        }
        gameOverCheck();
    }

    public static void alternativeGame() {
        Node barrier = View.createBarrier();
        score++;
        alternativeGame = true;
        snakeMove();
        if (score % 10 == 0) {
            boolean flag = true;
            while (flag) {
                flag = false;
                barrier.setTranslateX((int) (Math.random() * (width - bodySize)) / bodySize * bodySize);
                barrier.setTranslateY((int) (Math.random() * (height - bodySize)) / bodySize * bodySize);
                for (Node rect : snake) {
                    if (rect.getTranslateX() == (barrier.getTranslateX())
                            && rect.getTranslateY() == (barrier.getTranslateY()))
                        flag = true;
                }
                for (Node rect : barriers) {
                    if (rect.getTranslateX() == (barrier.getTranslateX())
                            && rect.getTranslateY() == (barrier.getTranslateY()))
                        flag = true;
                }
            }
            barriers.add(barrier);
        }

        Node body = snake.get(0);
        for (Node push : barriers) {
            if (body.getTranslateX() == push.getTranslateX()
                    && body.getTranslateY() == push.getTranslateY()) {
                snake.remove(snake.size() - 1);
            }
        }
        gameOverCheck();
    }

    public static void snakeMove() {
        boolean remove = snake.size() > 1;
        Node body = remove ? snake.remove(snake.size() - 1) : snake.get(0);
        switch (direction) {
            case RIGHT:
                body.setTranslateX(snake.get(0).getTranslateX() + bodySize);
                body.setTranslateY(snake.get(0).getTranslateY());
                break;
            case LEFT:
                body.setTranslateX(snake.get(0).getTranslateX() - bodySize);
                body.setTranslateY(snake.get(0).getTranslateY());
                break;
            case UP:
                body.setTranslateX(snake.get(0).getTranslateX());
                body.setTranslateY(snake.get(0).getTranslateY() - bodySize);
                break;
            case DOWN:
                body.setTranslateX(snake.get(0).getTranslateX());
                body.setTranslateY(snake.get(0).getTranslateY() + bodySize);
                break;
        }
        if (remove) snake.add(0, body);
    }

    public static void gameOverCheck() {
        if (snake.size() == 0) {
            Controller.gameOver();
            return;
        }

        Node body = snake.get(0);
        for (Node rect : snake) {
            if (rect != body && body.getTranslateX() == rect.getTranslateX() && body.getTranslateY() == rect.getTranslateY()) {
                Controller.gameOver();
                break;
            }
        }

        if (body.getTranslateX() < 0 || body.getTranslateX() >= width
                || body.getTranslateY() < 0 || body.getTranslateY() >= height) {
            Controller.gameOver();
        }
    }
}
