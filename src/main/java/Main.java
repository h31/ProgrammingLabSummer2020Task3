import Controller.Controller;
import Model.*;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);
        Player player = new Player(800, 630);
        Group root = new Group();
        root.getChildren().addAll(new SpriteData.Background().getBACKGROUND_IMG(), player.getImgView(), player.getCOLLISION(), player.rectTest);
        Scene scene = new Scene(root, 1024, 768);

        final Animation animation = new SpriteAnimation(
                Duration.millis(500),
                player
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        Controller controller = new Controller(stage, scene, player);

    }

    public static void main(String[] args) {
        Application.launch();
    }
}
