package Model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FirstLevel extends Level {

    private static final Image IMG = SpriteData.getBackgroundImg("background.png");
    private static final String LOCATION = "First";

    private static final List<Rectangle> COLLISIONS = Arrays.asList(
            new Rectangle(480, 381, 540, 30), //Верхняя стена справа
            new Rectangle(270, 381, 130, 30), // Верхняя стена слева
            new Rectangle(265, 381, 10, 500), // Стена слева
            new Rectangle(265, 730, 700, 10), // Стена снизу
            new Rectangle(1010, 400, 10, 650), // Стена справа
            new Rectangle(400, 370, 80, 10)
    );

    private static final LinkedList<Trigger> TRIGGERS = new LinkedList<>() {{
        add(new Trigger(new Rectangle(425, 381, 30, 30), new Effect(), COLLISION_TYPE.ENTER));
    }};

    private static final LevelObject[] OBJECTS = SpriteData.getLevelObjects("Objects");

    {
        OBJECTS[0].setLocation(480, 520);
        OBJECTS[1].setLocation(755, 525);
        OBJECTS[2].setLocation(385, 586);
        OBJECTS[3].setLocation(570, 520);
    }

    static final int[] pCOORD = {
            800,
            600
    };


    public FirstLevel() {
        super(LOCATION, IMG, COLLISIONS, TRIGGERS, OBJECTS, pCOORD);
    }
}
