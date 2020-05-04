import Controller.Controller;
import Model.Level;
import Model.Player;
import Model.SpriteAnimation;
import View.View;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);
        Level level = new Level("Start");
        Player player = new Player(800,600);
        Scene scene = loadFirstScene(level, player);
        runAnimation(player); // Запускаю анимацию персонажа
        new Controller(scene, player); // Запускаю контроллер, который будет ловить нажатие клавиш//
        stage.setScene(scene);
        View view = new View(stage, level);
        view.show();

    }

    private Scene loadFirstScene(Level level, Player player) {
        Group root = new Group();
        root.getChildren().addAll(level.getLEVEL_IMG(), player.getImgView(), player.getCOLLISION(), player.rectTest);
        Scene scene = new Scene(root, 1024, 768);
        return scene;
    }

    private void runAnimation(Player player) {
        final Animation animation = new SpriteAnimation(
                Duration.millis(500),
                player
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
