package Model;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Effect extends Animated {

    public Effect(EFFECT_TYPE type, int x, int y) {
        ImageView[] effect;
        if (type == EFFECT_TYPE.MAGIC) {
            effect = SpriteData.getSprites("vortexmagic");
        } else if (type == EFFECT_TYPE.EXPLOSION) {
            effect = SpriteData.getSprites("X_plosion");
        } else {
            throw new IllegalArgumentException();
        }
        super.setImgArray(effect);
        super.setImgView(effect[0]);
        setPosition(x,y);
    }

    public Effect() {

    }

    public void runAnimation() {
        System.out.println("Run Animation");
        this.getImgView().setVisible(true);
        final Animation animation = new SpriteAnimation(
                Duration.millis(1500),
                this
        );
        animation.setOnFinished(actionEvent -> {
            System.out.println("bruh");
            Player.freezed = false;
            this.getImgView().setVisible(false);
        });
        animation.setCycleCount(1);
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

    public void setPosition(int x, int y) {
        super.getImgView().setX(x);
        super.getImgView().setY(y);
    }
}

