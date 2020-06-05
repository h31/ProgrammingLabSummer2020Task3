package Model;
import View.View;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SecondLevel extends Level {

    private static final Image IMG = SpriteData.getBackgroundImg("firstlevel.png");
    private static final String LOCATION = "Second";

    private static final LinkedList<Trigger> TRIGGERS = new LinkedList<>() {{
        add(new Trigger("OpenWall",new Rectangle(70, 345, 40, 40),
                new Effect(EFFECT_TYPE.MAGIC, 50, 325),
                COLLISION_TYPE.INTERACT,
                SpriteData.getSprite("wall.png")));
    }};

    private static final List<Rectangle> COLLISION = Arrays.asList(
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
            new Rectangle(310, 520, 501, 20) // Начальный коридор сверху
    );

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
        ft.setOnFinished(actionEvent -> System.out.println("MAGIC"));
        ft.play();
    }
}
