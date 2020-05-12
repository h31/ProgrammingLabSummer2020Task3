import Controller.Controller;
import Model.Level;
import Model.Player;
import View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);
        View view = new View(stage);
        Level level = new Level();
        Player player = new Player(view, level, 800,600);
        view.setPLAYER(player);
        view.setLEVEL(level);
        view.showScene();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
