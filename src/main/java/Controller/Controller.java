package Controller;

import Model.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Controller {
        private final Stage primaryStage;
        private Scene primaryScene;
        private Player player;

    public Controller(Stage stage, Scene scene, Player player) {
        this.primaryStage = stage;
        this.primaryScene = scene;
        this.player = player;
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.show();
        moveTrigger();
    }

    private void moveTrigger() {
        primaryScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    player.setVelY(1.5);
                    break;
                case UP:
                    player.setVelY(-1.5);
                    break;
                case LEFT:
                    player.setVelX(-1.5);
                    break;
                case RIGHT:
                    player.setVelX(1.5);
                    break;
            }
            player.move();
        });
        primaryScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case DOWN:
                case UP:
                    player.setVelY(0);
                    break;
                case LEFT:
                case RIGHT:
                    player.setVelX(0);
                    break;
            }
        });
    }
}
