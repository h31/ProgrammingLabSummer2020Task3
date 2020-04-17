import Controller.Controller;
import Model.*;
import Model.Character;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);
        SpriteData sd = new SpriteData();
        ImageView imgView = new ImageView(new Image("background.png"));
        //Skeleton skeleton = new Skeleton(sd.getSKELETON_IDLE(), 800, 630);
        Player player = new Player(sd.getSKELETON_IDLE(), 800, 630);
        Group root = new Group();
        root.getChildren().addAll(imgView, player.getImgView());
        Scene scene = new Scene(root, 1024, 768);

        final Animation animation = new SpriteAnimation(
                Duration.millis(500),
                player.getImgView(),
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
