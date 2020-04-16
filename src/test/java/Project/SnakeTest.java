package Project;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    View a = new View();
    Controller b = new Controller();

    @Test
    public void modelInitializationTest() {
        a.createParent();
        Model.snake.add(new Rectangle());
        Model.snake.add(new Rectangle());
        Model.snake.add(new Rectangle());
        assertEquals(3, Model.snake.size());
        assertTrue(Model.width != 0);
        assertTrue(Model.height != 0);
        assertTrue(Model.bodySize != 0);
        assertTrue(Model.meal.getTranslateY() >= 0 && Model.meal.getTranslateY() <= Model.height - Model.bodySize);
        assertTrue(Model.meal.getTranslateX() >= 0 && Model.meal.getTranslateX() <= Model.width - Model.bodySize);
        assertEquals(0, Model.score);
    }

    @Test
    public void startTest() {
        a.createParent();
        b.startGame();
        View.timeline.stop();
        assertNotEquals(0, Model.snake.size());
        assertEquals(1, Model.snake.size());
    }

    @Test
    public void modelTest() {
        a.createParent();
        b.startGame();
        View.timeline.stop();
        Model.part = a.createSnakePart();
        Model.moveSnake();
        assertEquals(Model.bodySize, Model.snake.get(0).getTranslateX());
        assertEquals(0, Model.snake.get(0).getTranslateY());

        Model.meal.setTranslateX(Model.bodySize * 2.5);
        Model.meal.setTranslateY(Model.bodySize / 2);
        Model.moveSnake();
        assertEquals(2, Model.snake.size());
        assertTrue(Model.meal.getTranslateX() != Model.bodySize * 2 || Model.meal.getTranslateX() != 0);
    }

    @Test
    public void gameOverTest() {
        a.createParent();
        Model.snake.add(new Rectangle());
        Model.snake.add(new Rectangle());
        Model.snake.add(new Rectangle());
        Controller.gameOver();
        assertEquals(0, Model.snake.size());

        b.startGame();
        View.timeline.stop();
        Rectangle part1 = new Rectangle(Model.bodySize, Model.bodySize);
        part1.setTranslateX(Model.bodySize);
        part1.setTranslateY(Model.bodySize);
        Rectangle part2 = new Rectangle(Model.bodySize, Model.bodySize);
        part2.setTranslateX(Model.bodySize * 2);
        part2.setTranslateY(Model.bodySize);
        Rectangle part3 = new Rectangle(Model.bodySize, Model.bodySize);
        part3.setTranslateX(Model.bodySize * 2);
        part3.setTranslateY(0);
        Rectangle part4 = new Rectangle(Model.bodySize, Model.bodySize);
        part4.setTranslateX(Model.bodySize);
        part4.setTranslateY(0);
        Rectangle part5 = new Rectangle(Model.bodySize, Model.bodySize);
        part5.setTranslateX(0);
        part5.setTranslateY(0);
        Model.direction = Model.Direction.UP;
        Model.moveSnake();
        assertEquals(0, Model.snake.size());

        b.startGame();
        View.timeline.stop();
        Model.direction = Model.Direction.UP;
        Model.moveSnake();
        assertEquals(0, Model.snake.size());

        b.startGame();
        View.timeline.stop();
        Model.direction = Model.Direction.LEFT;
        Model.moveSnake();
        assertEquals(0, Model.snake.size());

        b.startGame();
        View.timeline.stop();
        Model.snake.get(0).setTranslateX(Model.width - Model.bodySize);
        Model.moveSnake();
        assertEquals(0, Model.snake.size());

        b.startGame();
        View.timeline.stop();
        Model.snake.get(0).setTranslateY(Model.height - Model.bodySize);
        Model.direction = Model.Direction.DOWN;
        Model.moveSnake();
        assertEquals(0, Model.snake.size());
    }
}