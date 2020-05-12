package View;

import Controller.Controller;
import Model.Level;
import Model.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.naming.ldap.Control;

public class View {
    private final Stage stage;
    private Player PLAYER;
    private Level LEVEL;
    private Scene scene;
    private Controller controller;

    public View(Stage stage) {
        this.stage = stage;
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
        PLAYER.getImgView().toFront();
        general.getChildren().add(LEVEL.getLEVEL_IMG());
        LEVEL.getLEVEL_IMG().toBack();

        for (Rectangle colShape : LEVEL.getCOLLISION()) {
            general.getChildren().add(colShape);
        }
        for (Rectangle colShape : LEVEL.getTRIGGERS()) {
            general.getChildren().add(colShape);
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
