import Model.Level;
import Model.Player;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
private static final boolean debugMode = true;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("Game");
        stage.setResizable(false);
        View view = new View(stage, debugMode);
        Level level = new Level();
        Player player = new Player(view, level);
        view.setPLAYER(player);
        view.setLEVEL(level);
        view.showScene();

    }

    public static void main(String[] args) {
        Application.launch();
    }
}
