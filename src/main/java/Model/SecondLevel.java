package Model;
import View.View;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.LinkedList;

public class SecondLevel extends Level {

    private static final Image IMG = SpriteData.getBackgroundImg("firstlevel.png");
    private static final String LOCATION = "Second";

    private static final LinkedList<Trigger> TRIGGERS = new LinkedList<>() {{
        add(new Trigger(
                "OpenWall",
                new Rectangle(70, 345, 40, 40),
                new Effect(EFFECT_TYPE.MAGIC, 50, 325),
                COLLISION_TYPE.INTERACT,
                SpriteData.getSprite("wall.png")
        ));

        add(new Trigger("MagicBall",
                new Rectangle(100, 600, 20, 20),
                new Effect(EFFECT_TYPE.MAGIC_BALL,30,325),
                COLLISION_TYPE.COLIDED
        ));

        add(new Trigger(
                "Enter",
                new Rectangle(35,5,45,25),
                new Effect(),
                COLLISION_TYPE.ENTER
        ));


    }};

    static {
        TRIGGERS.get(0).setObjectPosition(479, 530);
    }

    private static final LinkedList<Rectangle> COLLISION = new LinkedList<>(Arrays.asList(
            new Rectangle(0, 730, 1000, 4), // Вся нижняя стена
            new Rectangle(0, 520, 4, 240), //Первая левая стена
            new Rectangle(0, 520, 251, 20), // Верхняя стена
            new Rectangle(247, 470, 4, 50), // Коридор в комнатку налево
            new Rectangle(310, 442, 4, 70), // Коридор в комнатку направо
            new Rectangle(45, 465, 200, 4), // Стена нижняя в комнатке
            new Rectangle(42, 330, 4, 135), // Стена левая в комнатке
            new Rectangle(42, 330, 245, 8), // Стена верхняя в комнате
            new Rectangle(287, 330, 35, 20), // Стена верхняя в комнате
            new Rectangle(320, 330, 4, 120), // Стена правая в комнате
            new Rectangle(82, 338, 30, 10), // Стена правая в комнате
            new Rectangle(310, 520, 501, 20), // Начальный коридор сверху
            new Rectangle(310, 520, 501, 20), // Начальный коридор сверху
            new Rectangle(811, 420, 4, 100), // Стена левая в конце прохода
            new Rectangle(530, 5, 4, 270), // Стена правая в конце прохода
            new Rectangle(530, 260, 490, 20), // Стена верхняя в проходе
            new Rectangle(1010, 300, 4, 470), // Стена правая в проходе
            new Rectangle(340, 415, 476, 4), // Стена нижняя в проходе
            new Rectangle(340, 130, 4, 280), // Стена левая в проходе
            new Rectangle(0, 130, 340, 4), // Стена нижняя последний проход
            new Rectangle(0, 5, 35, 15), // Стена верхняя (левая часть) от прохода
            new Rectangle(35, 0, 38, 5), // Стена вехрняя над проходом
            new Rectangle(73, 5, 460, 15), // Стена вехрняя (правая часть) от прохода
            new Rectangle(479, 530, 4, 240) // Коллизия исчезающей стены (всегда последняя)
    ));

    private static final LevelObject[] OBJECTS = SpriteData.getLevelObjects("FIRST_OBJECTS");

    static final int[] pCOORD = {
            100,
            650
    };

    public SecondLevel() {
        super(LOCATION, IMG, COLLISION, TRIGGERS, OBJECTS, pCOORD);
    }

    @Override
    public void interact(Trigger trigger) {
        if (trigger.getNAME().equals("OpenWall")) openWall();
        if (trigger.getNAME().equals("MagicBall")) launchMagicBall();
    }

    /**
     * Открытие двери при попадании на триггер OpenWall
     */
    private void openWall() {
        Trigger trigger = TRIGGERS.element();
        Effect effect = trigger.getEFFECT();
        View.showEffect(effect);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), trigger.getInteractedObject().getKey());
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setOnFinished(actionEvent -> COLLISION.remove(COLLISION.getLast()));
        ft.play();
    }

    private void launchMagicBall() {
        Trigger trigger = TRIGGERS.element();
        Effect effect = trigger.getEFFECT();
        View.showEffect(effect);
        Path path = new Path();
        MoveTo moveTo = new MoveTo(100, 150);
        //Creating the Cubic curve path element
        CubicCurveTo cubicCurveTo = new CubicCurveTo(400, 40, 175, 250, 500, 150);
        path.getElements().add(moveTo);
        path.getElements().add(cubicCurveTo);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setNode(effect.getImgView());
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }
}
