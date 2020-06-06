package Model;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Trigger {
    private final String NAME;
    private final Rectangle RECT;
    private final Effect EFFECT;
    private final COLLISION_TYPE TYPE;
    private final Pair<ImageView, Rectangle> interactedObject;


    Trigger(String name, Rectangle rect, Effect effect, COLLISION_TYPE type, ImageView interactedObject) {
        this.NAME = name;
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
        this.interactedObject = new Pair<>(interactedObject, SpriteData.spriteToCollision(interactedObject));
    }

    Trigger(String name, Rectangle rect, Effect effect, COLLISION_TYPE type) {
        this.NAME = name;
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
        this.interactedObject = new Pair<>(null, null);
    }

    public void setObjectPosition(int x, int y) {
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

    public COLLISION_TYPE getTYPE() {
        return TYPE;
    }

    public String getNAME() {
        return NAME;
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