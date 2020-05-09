import Controller.Controller;
import Model.Level;
import Model.Player;
import Model.SpriteAnimation;
import View.View;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        Level level = new Level();
        level.setLocation("First");
        Player player = new Player(800,600, level);
        Group root = getFirstGroup(level, player);
        Scene scene = new Scene(root, 1024, 768);
        runAnimation(player); // Запускаю анимацию персонажа
        new Controller(scene, player); // Запускаю контроллер, который будет ловить нажатие клавиш
        View view = new View(stage, level, scene); // Создаю View, который будет отображать все изменения
        view.show();
    }

    public Group getFirstGroup(Level level, Player player) {
        Group playerGroup = new Group();
        Group levelGroup = new Group();
        Group general = new Group(playerGroup, levelGroup);
        playerGroup.toFront(); // Игрок находится на верхем слое
        playerGroup.getChildren().addAll(player.getImgView(), player.getCOLLISION()); // Добавление всех элементов игрока
        // Добавление всех элементов уровня
        levelGroup.getChildren().add(level.getLEVEL_IMG());
        for (Rectangle colShape : level.getCOLLISION()) {
            levelGroup.getChildren().add(colShape);
        }
        for (Rectangle colShape : level.getTRIGGERS()) {
            levelGroup.getChildren().add(colShape);
        }
        return general;
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
