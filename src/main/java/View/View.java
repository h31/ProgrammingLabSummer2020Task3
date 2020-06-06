package View;

import Controller.Controller;
import Model.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

public class View {
    private final Stage STAGE;
    private Player PLAYER;
    private Level LEVEL;
    private Scene scene;
    private Controller controller;

    private final boolean DEBUG_MODE;
    private final double DEBUG_OPACITY = 0.25;

    public View(Stage stage, boolean debugMode) {
        this.STAGE = stage;
        this.DEBUG_MODE = debugMode;
    }

    public static void movePlayer(Player player, double posX, double posY) {
        player.setPosition(posX, posY);
    }

    public void showScene() {
        scene = createScene();
        STAGE.setScene(scene);
        STAGE.show();
    }

    /*
        Создание сцены
     */
    private Scene createScene() {
        Group general = new Group();

        loadLevelIMG(general);
        loadPlayer(general);
        loadCollision(general);
        loadTriggers(general);
        loadObjects(general);

        Scene newScene = new Scene(general, 1024, 768);
        if (controller == null) {
            this.controller = new Controller(newScene, PLAYER);
        } else {
            controller.changeScene(newScene);
        }
        return newScene;
    }


    private void loadLevelIMG(Group general) {
        general.getChildren().add(LEVEL.getLEVEL_IMG()); 
        LEVEL.getLEVEL_IMG().setViewOrder(3);
    }
    private void loadPlayer(Group general) {
        general.getChildren().addAll(PLAYER.getImgView(), PLAYER.getCOLLISION()); // Загрузка персонажа
        PLAYER.getImgView().setViewOrder(1);
        checkDebugMode(PLAYER.getCOLLISION(), DEBUG_MODE);
    }

    private void loadCollision(Group general) {
        for (Rectangle colShape : LEVEL.getCOLLISION()) {
            general.getChildren().add(colShape);
            checkDebugMode(colShape, DEBUG_MODE);
        }
    }

    private void loadTriggers(Group general) {
        for (Trigger trigger : LEVEL.getTRIGGERS()) {
            Rectangle rect = trigger.getRECT();
            ImageView effect = trigger.getEFFECT().getImgView();
            Pair<ImageView, Rectangle> interactedObject = trigger.getInteractedObject();
            if (effect != null) {
                general.getChildren().add(effect);
                effect.setVisible(false);
            }
            if (interactedObject.getValue() != null && interactedObject.getKey() != null) {
                general.getChildren().add(interactedObject.getValue()); // Коллизия
                checkDebugMode(interactedObject.getValue(), DEBUG_MODE);
                general.getChildren().add(interactedObject.getKey());
            }
            general.getChildren().add(rect);
            checkDebugMode(rect, DEBUG_MODE);
        }
    }

    private void loadObjects(Group general) {
        for (LevelObject object : LEVEL.getOBJECTS()) {
            general.getChildren().addAll(object.getIMG_VIEW());
            general.getChildren().addAll(object.getTOP_COLLISION(), object.getBOTTOM_COLLISION());
            checkDebugMode(object.getTOP_COLLISION(), DEBUG_MODE);
            checkDebugMode(object.getBOTTOM_COLLISION(), DEBUG_MODE);
        }
    }

    private void checkDebugMode(Rectangle rect, boolean status) {
        if (status) {
            rect.setOpacity(DEBUG_OPACITY);
            rect.setFill(Color.BLUE);
        } else {
            rect.setOpacity(0);
        }
    }

    public static void showEffect(Effect effect) {
        System.out.println("Now in View");
        effect.runAnimation();
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
