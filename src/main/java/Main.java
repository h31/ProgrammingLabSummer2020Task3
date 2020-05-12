import Controller.Controller;
import Model.Level;
import Model.Player;
import View.View;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);

        Level level = new Level();
        Player player = new Player(800,600, level);
        Scene scene = updateScene(level, player);
        new Controller(scene, player); // Запускаю контроллер, который будет ловить нажатие клавиш
        stage.setScene(scene);
        stage.show();
    }

    public static Scene updateScene(Level level, Player player) {
        Group general = new Group();
        level.getLEVEL_IMG().toBack(); // Игрок находится на верхем слое
        general.getChildren().addAll(player.getImgView(), player.getCOLLISION()); // Добавление всех элементов игрока
        // Добавление всех элементов уровня
        general.getChildren().add(level.getLEVEL_IMG());
        for (Rectangle colShape : level.getCOLLISION()) {
            general.getChildren().add(colShape);
        }
        for (Rectangle colShape : level.getTRIGGERS()) {
            general.getChildren().add(colShape);
        }
        return new Scene(general, 1024, 768);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
