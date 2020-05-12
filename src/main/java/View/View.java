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

    public static void movePlayer(Player player, double posX, double posY) {
        player.setPosition(posX, posY);
    }

//    public static void updateLocation(Level level) {
//
//    }

}
