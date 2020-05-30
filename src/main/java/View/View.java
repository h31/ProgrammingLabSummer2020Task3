package View;

import Controller.Controller;
import Model.Level;
import Model.LevelObject;
import Model.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.naming.ldap.Control;

public class View {
    private final Stage stage;
    private Player PLAYER;
    private Level LEVEL;
    private Scene scene;
    private Controller controller;
    private final boolean DEBUG_MODE;
    private final double DEBUG_OPACITY = 0.25;

    public View(Stage stage, boolean debugMode) {
        this.stage = stage;
        this.DEBUG_MODE = debugMode;
    }

    public static void movePlayer(Player player, double posX, double posY) {
        player.setPosition(posX, posY);
    }

    public void showScene() {
        scene = createScene();
        stage.setScene(scene);
        stage.show();
    }


    private Scene createScene() {
        Group general = new Group();
        general.getChildren().addAll(PLAYER.getImgView(), PLAYER.getCOLLISION());
        PLAYER.getImgView().setViewOrder(1);
        if (DEBUG_MODE) {
            PLAYER.getCOLLISION().setOpacity(DEBUG_OPACITY);
            PLAYER.getCOLLISION().setFill(Color.BLUE);
        } else {
            PLAYER.getCOLLISION().setOpacity(0);
        }
        general.getChildren().add(LEVEL.getLEVEL_IMG());
        LEVEL.getLEVEL_IMG().setViewOrder(3);

        for (Rectangle colShape : LEVEL.getCOLLISION()) {
            general.getChildren().add(colShape);
            if (DEBUG_MODE) {
                colShape.setOpacity(DEBUG_OPACITY);
                colShape.setFill(Color.GRAY);
            } else {
                colShape.setOpacity(0);
            }
        }
        for (Rectangle colShape : LEVEL.getTRIGGERS()) {
            general.getChildren().add(colShape);
            if (DEBUG_MODE) {
                colShape.setOpacity(DEBUG_OPACITY);
                colShape.setFill(Color.YELLOW);
            } else {
                colShape.setOpacity(0);
            }
        }
        for (LevelObject object : LEVEL.getOBJECTS()) {
            general.getChildren().addAll(object.getIMG_VIEW());
            general.getChildren().addAll(object.getTOP_COLLISION(), object.getBOTTOM_COLLISION());
            if (DEBUG_MODE) {
                object.getTOP_COLLISION().setOpacity(DEBUG_OPACITY);
                object.getBOTTOM_COLLISION().setOpacity(DEBUG_OPACITY);
                object.getTOP_COLLISION().setFill(Color.GRAY);
                object.getBOTTOM_COLLISION().setFill(Color.GRAY);
            } else {
                object.getTOP_COLLISION().setOpacity(0);
                object.getBOTTOM_COLLISION().setOpacity(0);
            }
        }
        Scene newScene = new Scene(general, 1024, 768);
        if (controller == null) {
            this.controller = new Controller(newScene, PLAYER);
        } else {
            controller.changeScene(newScene);
        }
        return newScene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPLAYER(Player PLAYER) {
        this.PLAYER = PLAYER;
    }

    public void setLEVEL(Level LEVEL) {
        this.LEVEL = LEVEL;
    }

}
