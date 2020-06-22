package Project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class View {
    static Timeline timeline = new Timeline();
    private int bodySize = 50;

    public Parent createParent() throws IOException {
        final int width = Model.width ;
        final int height = Model.height;
        Pane root = new FXMLLoader(new File("src/main/resources/Grid.fxml").toURI().toURL()).load();
        root.setPrefSize(width, height);


        Group snakeBody = new Group();
        Model.snake = snakeBody.getChildren();

        Group barriersBody = new Group();
        Model.barriers = barriersBody.getChildren();

        Circle meal = new Circle(bodySize / 2);
        meal.setFill(Color.DARKRED);
        meal.setTranslateX((int) (Math.random() * (width - bodySize)) / bodySize * bodySize + bodySize / 2);
        meal.setTranslateY((int) (Math.random() * (height - bodySize)) / bodySize * bodySize + bodySize / 2);
        Model.meal = meal;

        Text scoreShower = new Text(0, 27, "Score: 0");
        scoreShower.setStroke(Color.BLACK);
        scoreShower.setFill(Color.WHITE);
        scoreShower.setFont(Font.font(35));

        Text gameOver = new Text(250, 320, "Game Over");
        gameOver.setFont(Font.font(75));
        gameOver.setFill(Color.RED);
        gameOver.setVisible(false);

        KeyFrame frame = new KeyFrame(Duration.seconds(0.15), event -> {
            Model.part = createSnakePart();

            if (Model.alternativeGame) Model.alternativeGame();
            else Model.classicGame();
            scoreShower.setText("Score: " + Model.score);
            if (Model.gameOver) gameOver.setVisible(true);
            else gameOver.setVisible(false);
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        if (Model.alternativeGame) root.getChildren().addAll(barriersBody, snakeBody, scoreShower, gameOver);
            else root.getChildren().addAll(meal, snakeBody, scoreShower, gameOver);
        return root;
    }

    public Node createSnakePart() {
        Rectangle head = new Rectangle(bodySize, bodySize);
        head.setFill(Color.YELLOW);
        return head;
    }

    public static Node createBarrier() {
        int bodySize = Model.bodySize;
        Rectangle rect = new Rectangle(bodySize, bodySize);
        rect.setFill(Color.PURPLE);

        return rect;
    }

    public  ImageView addBackground() throws MalformedURLException {
         return new ImageView(new Image(new File("background/field.jpg").toURI().toURL().toString(),
                Model.width, Model.height, false, true));
    }
}
