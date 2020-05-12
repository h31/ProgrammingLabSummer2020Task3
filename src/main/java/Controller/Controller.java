package Controller;

import Model.Level;
import Model.Player;
import Model.Status;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Controller {
        private Scene primaryScene;
        private final Player PLAYER;

    public Controller(Scene scene, Player player) {
        this.primaryScene = scene;
        this.PLAYER = player;
        moveTrigger();
    }

    private void moveTrigger() {
        primaryScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    PLAYER.setVelY(1.4);
                    PLAYER.setAction(Status.WALK, PLAYER.getView());
                    break;
                case UP:
                    PLAYER.setVelY(-1.4);
                    PLAYER.setAction(Status.WALK, PLAYER.getView());
                    break;
                case LEFT:
                    PLAYER.setVelX(-1.4);
                    PLAYER.setAction(Status.WALK, Status.View.LEFT);
                    break;
                case RIGHT:
                    PLAYER.setVelX(1.4);
                    PLAYER.setAction(Status.WALK, Status.View.RIGHT);
                    break;
            }
            PLAYER.startWalkAnim();
        });
        primaryScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case DOWN:
                case UP:
                    PLAYER.setVelY(0);
                    PLAYER.setAction(Status.IDLE, PLAYER.getView());
                    break;
                case LEFT:
                case RIGHT:
                    PLAYER.setVelX(0);
                    PLAYER.setAction(Status.IDLE, PLAYER.getView());
                    break;
            }
        });
    }
}
