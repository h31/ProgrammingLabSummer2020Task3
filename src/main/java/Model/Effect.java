package Model;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Effect extends Animated {
    private EFFECT_TYPE TYPE;
    private Rectangle COLLISION;

    Effect(EFFECT_TYPE type, int x, int y) {
        this.TYPE = type;
        ImageView[] effect;
        if (TYPE == EFFECT_TYPE.MAGIC) {
            effect = SpriteData.getSprites("vortexmagic");
        } else if (TYPE == EFFECT_TYPE.MAGIC_BALL) {
            effect = SpriteData.getSprites("MagicBall");
        } else {
            throw new IllegalArgumentException();
        }
        super.setImgArray(effect);
        super.setImgView(effect[0]);
        setPosition(x, y);
        COLLISION = SpriteData.spriteToCollision(super.getImgView());
    }

    Effect() {

    }


    public void runAnimation() {
        this.getImgView().setVisible(true);
        if (TYPE == EFFECT_TYPE.MAGIC) {
            runMagicAnim();
        }
        if (TYPE == EFFECT_TYPE.MAGIC_BALL) {
            runMagicBallAnim();
        }
    }

    private void runMagicAnim() {
        final Animation animation = new SpriteAnimation(
                Duration.millis(1500),
                this
        );
        animation.setOnFinished(actionEvent -> {
            Player.freezed = false;
            this.getImgView().setVisible(false);
        });
        animation.setCycleCount(1);
        animation.play();
    }

    private void runMagicBallAnim() {
        final Animation animation = new SpriteAnimation(
                Duration.millis(1500),
                this
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public ImageView getImgView() {
        return super.getImgView();
    }

    public void setImgView(ImageView imgView) {
        super.setImgView(imgView);
    }

    public ImageView[] getImgArray() {
        return super.getImgArray();
    }

    public void setImgArray(ImageView[] imgArray) {
        super.setImgArray(imgArray);
        getImgView().setImage(imgArray[0].getImage());
    }

    private void setPosition(int x, int y) {
        super.getImgView().setX(x);
        super.getImgView().setY(y);
    }

    public Rectangle getCOLLISION() {
        return COLLISION;
    }
}

