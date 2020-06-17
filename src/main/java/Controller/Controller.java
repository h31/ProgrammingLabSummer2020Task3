package Controller;

import Model.Player;
import Model.Status;
import javafx.scene.Scene;

public final class Controller {
    private Scene primaryScene;
    private final Player PLAYER;


    // 0 - up, 1 - down, 2 - left, 3 - right, 4 - E, 5 - ESC
    public final static Boolean[] keyState = {
            false,
            false,
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
                    PLAYER.setVelY(PLAYER.SPEED);
                    PLAYER.setAction(Status.WALK, PLAYER.getView());
                    keyState[1] = true;
                    PLAYER.startWalkAnim();
                    break;
                case UP:
                    PLAYER.setVelY(-PLAYER.SPEED);
                    PLAYER.setAction(Status.WALK, PLAYER.getView());
                    keyState[0] = true;
                    PLAYER.startWalkAnim();
                    break;
                case LEFT:
                    PLAYER.setVelX(-PLAYER.SPEED);
                    PLAYER.setAction(Status.WALK, Status.View.LEFT);
                    keyState[2] = true;
                    PLAYER.startWalkAnim();
                    break;
                case RIGHT:
                    PLAYER.setVelX(PLAYER.SPEED);
                    PLAYER.setAction(Status.WALK, Status.View.RIGHT);
                    keyState[3] = true;
                    PLAYER.startWalkAnim();
                    break;
                case E:
                    keyState[4] = true;
                    break;
                case ESCAPE:
                    keyState[5] = true;
                    PLAYER.closeNote();
                    break;
            }
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
                case E:
                    keyState[4] = false;
                    break;
                case ESCAPE:
                    keyState[5] = false;
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
