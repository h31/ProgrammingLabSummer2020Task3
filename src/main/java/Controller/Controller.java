package Controller;

import Model.Level;
import Model.Player;
import Model.Status;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Controller {
    private Scene primaryScene;
    private final Player PLAYER;


    // 0 - up, 1 - down, 2 - left, 3 - right
    private Boolean[] keyState = {
            false,
            false,
            false,
            false
    };

    public Controller(Scene scene, Player player) {
        this.primaryScene = scene;
        this.PLAYER = player;
        moveTrigger();
    }

    public void changeScene(Scene scene) {
        this.primaryScene = scene;
        moveTrigger();
    }


    private void moveTrigger() {
        primaryScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    PLAYER.setVelY(1.4);
                    PLAYER.setAction(Status.WALK, PLAYER.getView());
                    keyState[1] = true;
                    break;
                case UP:
                    PLAYER.setVelY(-1.4);
                    PLAYER.setAction(Status.WALK, PLAYER.getView());
                    keyState[0] = true;
                    break;
                case LEFT:
                    PLAYER.setVelX(-1.4);
                    PLAYER.setAction(Status.WALK, Status.View.LEFT);
                    keyState[2] = true;
                    break;
                case RIGHT:
                    PLAYER.setVelX(1.4);
                    PLAYER.setAction(Status.WALK, Status.View.RIGHT);
                    keyState[3] = true;
                    break;
            }
            PLAYER.startWalkAnim();
        });
        primaryScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case DOWN:
                    PLAYER.setVelY(0);
                    if (!multipleKeyPressed()) {
                        PLAYER.setAction(Status.IDLE, PLAYER.getView());
                    }
                    keyState[1] = false;
                    break;
                case UP:
                    PLAYER.setVelY(0);
                    if (!multipleKeyPressed()) {
                        PLAYER.setAction(Status.IDLE, PLAYER.getView());
                    }
                    keyState[0] = false;
                    break;
                case LEFT:
                    PLAYER.setVelX(0);
                    if (!multipleKeyPressed()) {
                        PLAYER.setAction(Status.IDLE, PLAYER.getView());
                    }
                    keyState[2] = false;
                    break;
                case RIGHT:
                    PLAYER.setVelX(0);
                    if (!multipleKeyPressed()) {
                        PLAYER.setAction(Status.IDLE, PLAYER.getView());
                    }
                    keyState[3] = false;
                    break;
            }
        });
    }

    private boolean multipleKeyPressed() {
        int counter = 0;
        for (boolean state : keyState) {
            if (state) {
                counter++;
            }
        }
        return counter >= 2;
    }
}
