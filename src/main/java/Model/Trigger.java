package Model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Trigger {
    private final String NAME;
    private final Rectangle RECT;
    private final Effect EFFECT;
    private final COLLISION_TYPE TYPE;
    private final Pair<ImageView, Rectangle> interactedObject;
    private boolean used;


    Trigger(String name, Rectangle rect, Effect effect, COLLISION_TYPE type, ImageView interactedObject) {
        this.NAME = name;
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
        this.interactedObject = new Pair<>(interactedObject, SpriteData.spriteToCollision(interactedObject));
    }

    Trigger(Trigger trigger) {
        this.NAME = trigger.getNAME();
        this.RECT = trigger.getRECT();
        this.EFFECT = trigger.getEFFECT();
        this.TYPE = trigger.getTYPE();
        this.interactedObject = trigger.getInteractedObject();
        this.used = false;
    }

    Trigger(String name, Rectangle rect, Effect effect, COLLISION_TYPE type) {
        this.NAME = name;
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
        this.interactedObject = new Pair<>(new ImageView(), null);
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

    public void setUsed(boolean state) {
        used = state;
    }
    public boolean getUsed() {
        return used;
    }
}

enum COLLISION_TYPE {
    ENTER,
    INTERACT,
    COLIDED,
    DEATH
}