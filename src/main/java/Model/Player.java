package Model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Character {
    private double velY = 0;
    private double velX = 0;
    private AnimationTimer movementAnim = new AnimationTimer() {
        @Override
        public void handle(long l) {
            getImgView().setX(getImgView().getX() + getVelX());
            getImgView().setY(getImgView().getY() + getVelY());
            setPosition(getImgView().getX(), getImgView().getY());
        }
    };

    public Player(Image[] imgArray, int x, int y) {
        super(imgArray, x, y);
    }


    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
    }

    @Override
    public ImageView getImgView() {
        return super.getImgView();
    }

    @Override
    public void move() {
        movementAnim.start();
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
