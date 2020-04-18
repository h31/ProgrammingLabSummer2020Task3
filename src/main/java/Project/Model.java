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

    static int bodySize;
    static int width;
    static int height;
    static int score;
    static Direction direction = Direction.RIGHT;
    static ObservableList<Node> snake;
    static Node meal;
    static Node part;
    static boolean gameOver;

    public static void moveSnake() {
        int radius = bodySize / 2;
        score = (snake.size() - 1);
        boolean remove = snake.size() > 1;
        Node body = remove ? snake.remove(snake.size() - 1) : snake.get(0);
        double bodyX = body.getTranslateX();
        double bodyY = body.getTranslateY();
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
        if (body.getTranslateX() == (meal.getTranslateX() - radius)
                && body.getTranslateY() == (meal.getTranslateY() - radius)) {
            boolean flag = true;
            while (flag) {
                flag = false;
                meal.setTranslateX((int) (Math.random() * (width - bodySize)) / bodySize * bodySize + radius);
                meal.setTranslateY((int) (Math.random() * (height - bodySize)) / bodySize * bodySize + radius);
                for (Node rect : snake) {
                    if (rect.getTranslateX() == (meal.getTranslateX() - radius)
                            && rect.getTranslateY() == (meal.getTranslateY() - radius))
                        flag = true;
                }
            }
            part.setTranslateX(bodyX);
            part.setTranslateY(bodyY);
            snake.add(part);
        }

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
