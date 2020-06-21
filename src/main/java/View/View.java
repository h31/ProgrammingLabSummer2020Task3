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
    private final Stage Stage;
    private Player Player;
    private Level LEVEL;
    private Scene scene;
    private Controller controller;

    private final boolean DEBUG_MODE;
    private final double DEBUG_OPACITY = 0.25;

    public View(Level level) {
        this.Stage = new Stage();
        this.DEBUG_MODE = false;
        this.LEVEL = level;
    }

    public View(Stage stage, boolean debugMode, Level level) {
        this.Stage = stage;
        this.DEBUG_MODE = debugMode;
        this.LEVEL = level;
    }

    private void runDefaultAnim() {
        if (LEVEL.getLocation().equals("Second")) {
            SecondLevel level = (SecondLevel) LEVEL;
            level.launchTeslaOrb();
            level.launchMagicBall(level.getTRIGGERS().get(1), 570, 608, 3400);
            level.launchMagicBall(level.getTRIGGERS().get(2), 950, 670, 3400);
            level.setdMagicBallAnim1(level.launchMagicBall(level.getTRIGGERS().get(3), 480, 270, 300));
            level.setdMagicBallAnim2(level.launchMagicBall(level.getTRIGGERS().get(4), 380, 160, 300));
        }
    }

    public static void movePlayer(Player player, double posX, double posY) {
        player.setPosition(posX, posY);
    }

    public void showScene() {
        scene = createScene();
        Stage.setScene(scene);
        runDefaultAnim();
        Stage.show();
    }

    /*
     *  Создание сцены
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
            this.controller = new Controller(newScene, Player);
        } else {
            controller.changeScene(newScene);
        }
        return newScene;
    }


    private void loadLevelIMG(Group general) {
        general.getChildren().add(LEVEL.getLEVEL_IMG());
        if (LEVEL.getLocation().equals("First"))
            general.getChildren().add(SpriteData.getSprite("FirstLevel/Intro.png"));
        LEVEL.getLEVEL_IMG().setViewOrder(3);
    }

    private void loadPlayer(Group general) {
        general.getChildren().addAll(Player.getImgView(), Player.getCOLLISION()); // Загрузка персонажа
        Player.getImgView().setViewOrder(1);
        checkDebugMode(Player.getCOLLISION(), DEBUG_MODE);
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
            Pair<ImageView, Rectangle> interactedObject = trigger.getInteractedObject();
            Effect triggerEffect = trigger.getEFFECT();
            if (!triggerEffect.isEmpty()) {
                ImageView triggerEffectImg = triggerEffect.getImgView();
                general.getChildren().add(triggerEffectImg);
                triggerEffectImg.setVisible(false);
                triggerEffectImg.setViewOrder(2);
            }
            ImageView triggerImage = trigger.getIMAGE();
            if (triggerImage != null) {
                general.getChildren().add(triggerImage);
                triggerImage.setVisible(false);
                triggerImage.setViewOrder(0);
                SpriteData.toCenter(triggerImage);
            }
            if (interactedObject.getValue() != null && interactedObject.getKey() != null) {
                general.getChildren().add(interactedObject.getValue()); // Коллизия
                checkDebugMode(interactedObject.getValue(), DEBUG_MODE);
                general.getChildren().add(interactedObject.getKey());
                interactedObject.getKey().setOpacity(1); // Видимость пропадет, если игрок умрет
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
            object.getIMG_VIEW().setViewOrder(1);
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
        effect.runAnimation();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPlayer(Player player) {
        this.Player = player;
    }

    public void setLEVEL(Level LEVEL) {
        this.LEVEL = LEVEL;
    }
}
