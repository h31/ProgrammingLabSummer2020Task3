import Model.FirstLevel;
import Model.Level;
import Model.Player;
import Model.SecondLevel;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private static final boolean debugMode = true;

    @Override
    public void start(Stage stage) {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);
        View view = new View(stage, debugMode);
        Level level = new SecondLevel();
        Player player = new Player(view, level);
        view.setLEVEL(level);
        view.setPlayer(player);
        view.showScene();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
