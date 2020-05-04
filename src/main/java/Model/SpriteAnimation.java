package Model;

import javafx.animation.Transition;
import javafx.util.Duration;

public final class SpriteAnimation extends Transition {
    private Player player;

    public SpriteAnimation(Duration duration, Player player) {
        this.player = player;
        setCycleDuration(duration);
    }


    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor((k * player.getImgArray().length)), player.getImgArray().length - 1);
        player.getImgView().setImage(player.getImgArray()[index]);
    }

}
