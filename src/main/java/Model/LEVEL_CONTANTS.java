package Model;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.*;

final class LEVEL_CONTANTS {
    /*
     *  Константы для первого уровня
     */
    static final Image FIRST_IMG = SpriteData.getBackgroundImg("FirstLevel/FirstLevel.png");
    static final String FIRST_LOCATION = "First";

    static final List<Trigger> FIRST_TRIGGERS = new ArrayList<>() {{
        add(new Trigger("Enter", new Rectangle(425, 381, 30, 30), new Effect(), COLLISION_TYPE.ENTER));
        add(new Trigger("ReadNote", new Rectangle(595, 425, 25, 25), SpriteData.getSprite("FirstLevel/note.png"), COLLISION_TYPE.INTERACT));
    }};

    static final LinkedList<Rectangle> FIRST_COLLISION = new LinkedList<>(Arrays.asList(
            new Rectangle(480, 381, 540, 30), //Верхняя стена справа
            new Rectangle(270, 381, 130, 30), // Верхняя стена слева
            new Rectangle(265, 381, 10, 500), // Стена слева
            new Rectangle(265, 730, 700, 10), // Стена снизу
            new Rectangle(1010, 400, 10, 650), // Стена справа
            new Rectangle(400, 370, 80, 10)
    ));

    static final LevelObject[] FIRST_OBJECTS = SpriteData.getLevelObjects("FirstLevel/Objects");

    static {
        FIRST_OBJECTS[0].setLocation(480, 520);
        FIRST_OBJECTS[1].setLocation(755, 525);
        FIRST_OBJECTS[2].setLocation(385, 586);
        FIRST_OBJECTS[3].setLocation(385, 450);
    }

    static final int[] FIRST_pCOORD = {
            800,
            600
    };


    /*
     *  Константы для второго уровня
     */

    static final Image SECOND_IMG = SpriteData.getBackgroundImg("SecondLevel/SecondLevel.png");
    static final String SECOND_LOCATION = "Second";

    static final List<Trigger> SECOND_TRIGGERS = new ArrayList<>(Arrays.asList(
            new Trigger(
                    "OpenWall",
                    new Rectangle(70, 345, 40, 40),
                    new Effect(EFFECT_TYPE.MAGIC, 445, 560),
                    COLLISION_TYPE.INTERACT,
                    SpriteData.getSprite("SecondLevel/wall.png")
            ),
            new Trigger("MagicBall_UPPER",
                    new Rectangle(495, 535, 20, 230),
                    new Effect(EFFECT_TYPE.MAGIC_BALL, 950, 608),
                    COLLISION_TYPE.COLIDED
            ),
            new Trigger("MagicBall_BOTTOM",
                    new Rectangle(495, 535, 20, 230),
                    new Effect(EFFECT_TYPE.MAGIC_BALL, 570, 670),
                    COLLISION_TYPE.COLIDED
            ),
            new Trigger("MagicBall_NEXT_DOWN",
                    new Rectangle(0, 0, 0, 0),
                    new Effect(EFFECT_TYPE.MAGIC_BALL, 380, 270),
                    COLLISION_TYPE.COLIDED
            ),
            new Trigger("MagicBall_NEXT_UP",
                    new Rectangle(0, 0, 0, 0),
                    new Effect(EFFECT_TYPE.MAGIC_BALL, 480, 160),
                    COLLISION_TYPE.COLIDED
            ),
            new Trigger(
                    "Enter",
                    new Rectangle(35, 10, 45, 25),
                    new Effect(),
                    COLLISION_TYPE.ENTER
            ),
            new Trigger(
                    "TeslaOrb",
                    new Rectangle(965, 302, 20, 35),
                    new Effect(EFFECT_TYPE.MAGIC_ITEM, 950, 290),
                    COLLISION_TYPE.INTERACT
            )
    ));

    /*
        Зависимые триггеры смерти
     */
    static {
        SECOND_TRIGGERS.add(new Trigger(
                "DEATH",
                SECOND_TRIGGERS.get(1).getEFFECT().getCOLLISION(),
                new Effect(),
                COLLISION_TYPE.DEATH
        ));
        SECOND_TRIGGERS.add(new Trigger(
                "DEATH",
                SECOND_TRIGGERS.get(2).getEFFECT().getCOLLISION(),
                new Effect(),
                COLLISION_TYPE.DEATH
        ));
        SECOND_TRIGGERS.add(new Trigger(
                "DEATH",
                SECOND_TRIGGERS.get(3).getEFFECT().getCOLLISION(),
                new Effect(),
                COLLISION_TYPE.DEATH
        ));
        SECOND_TRIGGERS.add(new Trigger(
                "DEATH",
                SECOND_TRIGGERS.get(4).getEFFECT().getCOLLISION(),
                new Effect(),
                COLLISION_TYPE.DEATH
        ));
    }

    static {
        SECOND_TRIGGERS.get(0).setObjectPosition(479, 530);
    }

    static final LinkedList<Rectangle> SECOND_COLLISION = new LinkedList<>(Arrays.asList(
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
            new Rectangle(35, 0, 38, 5), // Стена верхняя над проходом
            new Rectangle(73, 5, 460, 15), // Стена верхняя (правая часть) от прохода
            new Rectangle(479, 530, 4, 240) // Коллизия исчезающей стены (всегда последняя)
    ));

    static final LevelObject[] SECOND_OBJECTS = new LevelObject[0];

    static final int[] SECOND_pCOORD = {
            100,
            650
    };

    static final Image teslaOrbBroken = Objects.requireNonNull(SpriteData.getSprite("Effects/TeslaOrbBroken.png")).getImage();
    static Animation teslaOrbAnim;
}
