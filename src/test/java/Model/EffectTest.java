package Model;

import View.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class EffectTest {
    int magicBallOffset = 7;

    @Start
    public void start(Stage stage) {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setResizable(false);
        Level level = new SecondLevel();
        View view = new View(stage, false, level);
        Player player = new Player(view, level);
        view.setPlayer(player);
        view.showScene();
    }

    @Test
    void moveCollision() {
        Effect effect = new Effect(EFFECT_TYPE.MAGIC_BALL, 380, 270);
        effect.runAnimation();
        effect.moveCollision();
        System.out.println(effect.getImgView().getX());
        System.out.println(effect.getCOLLISION().getX());
        assertEquals(effect.getImgView().getX() + magicBallOffset, effect.getCOLLISION().getX());
        assertEquals(effect.getImgView().getY() + magicBallOffset, effect.getCOLLISION().getY());
    }
}