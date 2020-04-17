package Model;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public final class SpriteAnimation extends Transition {
    private ImageView imgView;
    private Character character;

    public SpriteAnimation(Duration duration, ImageView imgView, Character character) {
        this.imgView = imgView;
        this.character = character;
        setCycleDuration(duration);
    }


    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor((k * character.getImgArray().length)), character.getImgArray().length - 1);
        imgView.setImage(character.getImgArray()[index]);
    }

}
