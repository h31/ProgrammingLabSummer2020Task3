package Model;

import View.View;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class SecondLevel extends Level {
    private PathTransition dMagicBallAnim1;
    private PathTransition dMagicBallAnim2;

    private List<PathTransition> pathTransitionList = new ArrayList<>();

    SecondLevel() {
        super(LEVEL_CONTANTS.SECOND_LOCATION,
                LEVEL_CONTANTS.SECOND_IMG,
                LEVEL_CONTANTS.SECOND_COLLISION,
                LEVEL_CONTANTS.SECOND_TRIGGERS,
                LEVEL_CONTANTS.SECOND_OBJECTS.clone(),
                LEVEL_CONTANTS.SECOND_pCOORD
        );
    }

    @Override
    public void interact(Trigger trigger) {
        if (trigger.getNAME().equals("OpenWall")) openWall(trigger);
        if (trigger.getNAME().equals("TeslaOrb")) {
            stopTeslaOrb(trigger);
            dMagicBallAnim1.stop();
            dMagicBallAnim2.stop();
            dMagicBallAnim1.setDuration(Duration.millis(4000));
            dMagicBallAnim2.setDuration(Duration.millis(4000));
            dMagicBallAnim1.play();
            dMagicBallAnim2.play();
        }
    }

    @Override
    void reload() {
        super.setCOLLISION(super.createCollisionList(LEVEL_CONTANTS.SECOND_COLLISION));
        super.setTRIGGERS(super.createTriggerList(LEVEL_CONTANTS.SECOND_TRIGGERS));
        LEVEL_CONTANTS.teslaOrbAnim.play();
        pathTransitionList.forEach(Animation::stop);
    }


    /**
     * Открытие двери при попадании на триггер OpenWall
     */
    void openWall(Trigger trigger) {
        if (trigger.getUsed()) return;
        trigger.setUsed(true);
        Effect effect = trigger.getEFFECT();
        View.showEffect(effect);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), trigger.getInteractedObject().getKey());
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setOnFinished(actionEvent -> super.getCOLLISION().remove(super.getCOLLISION().getLast()));
        ft.play();
    }

    public PathTransition launchMagicBall(Trigger trigger, double toX, double toY, int duration) {
        if (trigger.getUsed()) return null;
        trigger.setUsed(true);
        Effect effect = trigger.getEFFECT();
        View.showEffect(effect);
        Path path = new Path();
        path.getElements().add(new MoveTo(trigger.getEFFECT().getImgView().getX(), trigger.getEFFECT().getImgView().getY()));
        path.getElements().add(new LineTo(toX, toY));
        PathTransition pathTransition = new PathTransition();
        pathTransitionList.add(pathTransition);
        pathTransition.setDuration(Duration.millis(duration));
        pathTransition.setNode(effect.getImgView());
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
        return pathTransition;
    }

    public void launchTeslaOrb() {
        Trigger trigger = getTRIGGERS().get(6);
        Effect effect = trigger.getEFFECT();
        View.showEffect(effect);
    }

    void stopTeslaOrb(Trigger trigger) {
        if (trigger.getUsed()) return;
        trigger.setUsed(true);
        trigger.getEFFECT().stopAnimation();
    }

    public void setdMagicBallAnim1(PathTransition dMagicBallAnim1) {
        this.dMagicBallAnim1 = dMagicBallAnim1;
    }

    public void setdMagicBallAnim2(PathTransition dMagicBallAnim2) {
        this.dMagicBallAnim2 = dMagicBallAnim2;
    }

}
