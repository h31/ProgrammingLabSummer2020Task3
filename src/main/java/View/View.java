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
    //private final Player player;
    private final Stage stage;
    private Level level;


    public View(Stage stage, Level level) {
        this.stage = stage;
        this.level = level;
    }

    public void show() {
        stage.show();
    }


}
