package View;

import Controller.Controller;
import Model.Level;
import Model.Player;
import Model.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View {
    private final Stage stage;
    private Level level;
    private Scene scene;


    public View(Stage stage, Level level, Scene scene) {
        this.stage = stage;
        this.level = level;
        this.scene = scene;
    }

    public void show() {
        stage.setScene(this.scene);
        stage.show();
    }

}
