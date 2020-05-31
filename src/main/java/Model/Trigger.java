package Model;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

public class Trigger {
    private final Rectangle RECT;
    private final Effect EFFECT;
    private final COLLISION_TYPE TYPE;
    private final Pair<ImageView, Rectangle> interactedObject;


    Trigger(Rectangle rect, Effect effect, COLLISION_TYPE type, ImageView interactedObject) {
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
        this.interactedObject = new Pair<>(interactedObject, SpriteData.spriteToCollision(interactedObject));
        setObjectPosition(479,530);
    }

    Trigger(Rectangle rect, Effect effect, COLLISION_TYPE type) {
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
        this.interactedObject = new Pair<>(null, null);
    }

    void runObjectAnim() {
        if (getIntObjectImg().getImage().getUrl().contains("wall.png")) {
            rotateObject();
        }
    }

    private void rotateObject() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), getIntObjectImg());
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setOnFinished(actionEvent -> System.out.println("MAGIC"));
        ft.play();
    }

    private void setObjectPosition(int x, int y) {
        getIntObjectCol().setX(x);
        getIntObjectCol().setY(y);
        getIntObjectImg().setX(x);
        getIntObjectImg().setY(y);
    }


    public Rectangle getRECT() {
        return RECT;
    }

    public Effect getEFFECT() {
        return EFFECT;
    }

    COLLISION_TYPE getTYPE() {
        return TYPE;
    }

    public Pair<ImageView, Rectangle> getInteractedObject() {
        return interactedObject;
    }

    private Rectangle getIntObjectCol() {
        return getInteractedObject().getValue();
    }

    private ImageView getIntObjectImg() {
        return getInteractedObject().getKey();
    }
}

enum COLLISION_TYPE {
    ENTER,
    INTERACT
}