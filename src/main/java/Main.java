import Model.Character;
import Model.Skeleton;
import Model.SpriteAnimation;
import Model.SpriteData;
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
        Skeleton skeleton = new Skeleton(sd.getSKELETON_IDLE(), 800, 630);
        Group root = new Group();
        root.getChildren().addAll(imgView, skeleton.getImgView());
        Scene scene = new Scene(root, 1024, 768);

        final Animation animation = new SpriteAnimation(
                Duration.millis(500),
                skeleton.getImgView(),
                skeleton
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
