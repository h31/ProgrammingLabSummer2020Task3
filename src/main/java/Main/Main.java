package Main;

import Model.Level;
import Model.Player;
import Model.SecondLevel;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private static final boolean debugMode = true;

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    @Override
    public void start(Stage stage) {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("Game");
        stage.setResizable(false);
        Level level = new SecondLevel();
        View view = new View(stage, debugMode, level);
        Player player = new Player(view, level);
        view.setPlayer(player);
        view.showScene();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
