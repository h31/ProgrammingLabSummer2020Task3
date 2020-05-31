package Model;

import javafx.scene.shape.Rectangle;

public class Trigger {
    private final Rectangle RECT;
    private final Effect EFFECT;
    private final COLLISION_TYPE TYPE;

    public Trigger(Rectangle rect, Effect effect, COLLISION_TYPE type) {
        this.RECT = rect;
        this.EFFECT = effect;
        this.TYPE = type;
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
}

enum COLLISION_TYPE {
    ENTER,
    INTERACT
}