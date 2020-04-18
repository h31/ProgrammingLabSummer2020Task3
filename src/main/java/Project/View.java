package Project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class View {
    static Timeline timeline = new Timeline();
    private int bodySize = 50;

    public Parent createParent() {
        int width = bodySize * 17;
        int height = bodySize * 14;
        Model.bodySize = bodySize;
        Model.width = width;
        Model.height = height;
        Pane root = new Pane();
        root.setPrefSize(width, height);

        Group snakeBody = new Group();
        Model.snake = snakeBody.getChildren();

        Circle meal = new Circle(bodySize / 2);
        meal.setFill(Color.RED);
        meal.setTranslateX((int) (Math.random() * (width - bodySize)) / bodySize * bodySize + bodySize / 2);
        meal.setTranslateY((int) (Math.random() * (height - bodySize)) / bodySize * bodySize + bodySize / 2);
        Model.meal = meal;

        Text scoreShower = new Text(0, 20, "Score: 0");
        scoreShower.setFont(Font.font(25));

        Text gameOver = new Text(250, 320, "Game Over");
        gameOver.setFont(Font.font(75));
        gameOver.setFill(Color.RED);
        gameOver.setVisible(false);

        KeyFrame frame = new KeyFrame(Duration.seconds(0.15), event -> {
            Model.part = createSnakePart();
            Model.moveSnake();
            scoreShower.setText("Score: " + Model.score);
            if (Model.gameOver) gameOver.setVisible(true);
            else gameOver.setVisible(false);
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        root.getChildren().addAll(gameOver, scoreShower, meal, snakeBody);
        return root;
    }

    public Node createSnakePart() {
        Rectangle head = new Rectangle(bodySize, bodySize);
        head.setFill(Color.GREEN);
        return head;
    }
}
