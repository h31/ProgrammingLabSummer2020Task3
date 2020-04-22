package Model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Character {
    private final SpriteData.Player sd = new SpriteData.Player();
    private double velY = 0;
    private double velX = 0;

    private final AnimationTimer movementAnim = new AnimationTimer() {
        @Override
        public void handle(long l) {
            getImgView().setX(getImgView().getX() + getVelX());
            getImgView().setY(getImgView().getY() + getVelY());
            setPosition(getImgView().getX(), getImgView().getY());
        }
    };

    public Player(int x, int y) {
        super(new SpriteData.Player().getSKELETON_IDLE(Status.View.LEFT), x, y);
    }


    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
    }

    @Override
    public Status getAction() {
        return super.getAction();
    }

    @Override
    public Status.View getView() {
        return super.getView();
    }

    @Override
    public void setAction(Status action, Status.View view) {
        super.setAction(action, view);
        if (action == Status.IDLE) {
            super.setImgArray(sd.getSKELETON_IDLE(view));
        }
        if (action == Status.WALK) {
            super.setImgArray(sd.getSKELETON_WALK(view));
        }
    }

    @Override
    public ImageView getImgView() {
        return super.getImgView();
    }

    @Override
    public void move() {
        getMovementAnim().start();
    }

    public AnimationTimer getMovementAnim() {
        return movementAnim;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }
}
