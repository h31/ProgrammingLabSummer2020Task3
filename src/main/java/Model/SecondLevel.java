package Model;

import View.View;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class SecondLevel extends Level {

    public SecondLevel() {
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
        if (trigger.getNAME().equals("OpenWall")) openWall();
        if (trigger.getNAME().equals("MagicBall")) launchMagicBall();
    }

    @Override
    void reload() {
        super.setCOLLISION(super.createCollisionList(LEVEL_CONTANTS.SECOND_COLLISION));
        super.setTRIGGERS(super.createTriggerList(LEVEL_CONTANTS.SECOND_TRIGGERS));
    }


    /**
     * Открытие двери при попадании на триггер OpenWall
     */
    private void openWall() {
        Trigger trigger = super.getTRIGGERS().stream().filter(e -> e.getNAME().equals("OpenWall")).findAny().get();
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

    private void launchMagicBall() {
        Trigger trigger = super.getTRIGGERS().stream().filter(e -> e.getNAME().equals("MagicBall")).findAny().get();
        System.out.println(trigger);
        if (trigger.getUsed()) return;
        trigger.setUsed(true);
        Effect effect = trigger.getEFFECT();
//        super.getTRIGGERS().add(new Trigger(
//                "DEATH",
//                effect.getCOLLISION(),
//                new Effect(),
//                COLLISION_TYPE.DEATH
//        ));
        View.showEffect(effect);
        Path path = new Path();
        path.getElements().add(new MoveTo(950, 640));
        path.getElements().add(new LineTo(550, 640));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(5000));
        pathTransition.setNode(effect.getImgView());
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }

}
