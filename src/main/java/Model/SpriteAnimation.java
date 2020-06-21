package Model;

import javafx.animation.Transition;
import javafx.util.Duration;

public final class SpriteAnimation extends Transition {
    private Animated animated;

    SpriteAnimation(Duration duration, Animated animated) {
        this.animated = animated;
        setCycleDuration(duration);
    }

    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor((k * animated.getImgArray().length)), animated.getImgArray().length - 1);
        animated.getImgView().setImage(animated.getImgArray()[index].getImage());
    }

}
