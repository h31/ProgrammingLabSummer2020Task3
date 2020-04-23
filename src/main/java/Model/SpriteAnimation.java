package Model;

import javafx.animation.Transition;
import javafx.util.Duration;

public final class SpriteAnimation extends Transition {
    private Character character;

    public SpriteAnimation(Duration duration, Character character) {
        this.character = character;
        setCycleDuration(duration);
    }


    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor((k * character.getImgArray().length)), character.getImgArray().length - 1);
        character.getImgView().setImage(character.getImgArray()[index]);
    }

}
