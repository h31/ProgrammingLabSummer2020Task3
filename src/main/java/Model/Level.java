package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.LinkedList;


public final class Level {
    private ImageView LEVEL_IMG = new ImageView();
    private Rectangle[] COLLISIONS;
    private LinkedList<Trigger> TRIGGERS;
    private LevelObject[] OBJECTS;
    private String location;

    public static final Rectangle[] START_COLLISIONS = {
            new Rectangle(480, 381, 540, 30), //Верхняя стена справа
            new Rectangle(270, 381, 130, 30), // Верхняя стена слева
            new Rectangle(265, 381, 10, 500), // Стена слева
            new Rectangle(265, 730, 700, 10), // Стена снизу
            new Rectangle(1010, 400, 10, 650), // Стена справа
            new Rectangle(400, 370, 80, 10)
    };

    public static final LinkedList<Trigger> START_TRIGGERS = new LinkedList<>() {{
        add(new Trigger(new Rectangle(425, 381, 30, 30), new Effect(), COLLISION_TYPE.ENTER));
    }};

    public static final LevelObject[] START_OBJECTS = SpriteData.getLevelObjects("Objects");

    {
        START_OBJECTS[0].setLocation(480, 520);
        START_OBJECTS[1].setLocation(755, 525);
        START_OBJECTS[2].setLocation(385, 586);
        START_OBJECTS[3].setLocation(570, 520);
    }

    public static final int[] START_pCOORD = {
            800,
            600
    };


    public static final Rectangle[] FIRST_COLLISION = {
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
    };


    public static final LinkedList<Trigger> FIRST_TRIGGERS = new LinkedList<>() {{
        //new Pair<>(new Rectangle(320, 330, 4, 120), COLLISION_TYPE.ENTER),
        add(new Trigger(new Rectangle(70, 345, 40, 40), new Effect(EFFECT_TYPE.MAGIC, 50, 325),  COLLISION_TYPE.INTERACT));
    }};
    public static final LevelObject[] FIRST_OBJECTS = SpriteData.getLevelObjects("FIRST_OBJECTS");

    public static final int[] FIRST_pCOORD = {
            100,
            650
    };

    public Level() {
        setLocation("Start");
    }

    public int[] setLocation(String location) {
        if (location.equals("Start")) {
            try {
                this.getLEVEL_IMG().setImage(new Image("background.png"));
            } catch (NullPointerException e) {
                System.out.println("Произошла ошибка :" + e.toString());
            }
            this.location = "Start";
            this.COLLISIONS = START_COLLISIONS;
            this.TRIGGERS = START_TRIGGERS;
            this.OBJECTS = START_OBJECTS;
            return START_pCOORD;
        } else if (location.equals("First")) {
            try {
                this.getLEVEL_IMG().setImage(new Image("firstlevel.png"));
            } catch (NullPointerException e) {
                System.out.println("Произошла ошибка :" + e.toString());
            }
            this.location = "First";
            this.COLLISIONS = FIRST_COLLISION;
            this.TRIGGERS = FIRST_TRIGGERS;
            this.OBJECTS = FIRST_OBJECTS;
            return FIRST_pCOORD;
        } else {
            throw new IllegalArgumentException("There are no location such like this");
        }
    }

    void checkObjectView(Player player) {
        double playerCol = player.getBOTTOM_COLLISION();
        for (LevelObject colShape : getOBJECTS()) {
            if (playerCol > colShape.getIMG_VIEW().getY() + colShape.getIMG_VIEW().getImage().getHeight()) {
                colShape.setView(OBJECT_VIEW.BACK);
            } else {
                colShape.setView(OBJECT_VIEW.FRONT);
            }
        }
    }

    public ImageView getLEVEL_IMG() {
        return LEVEL_IMG;
    }

    public Rectangle[] getCOLLISION() {
        return COLLISIONS;
    }

    public LinkedList<Trigger> getTRIGGERS() {
        return TRIGGERS;
    }

    public LevelObject[] getOBJECTS() {
        return OBJECTS;
    }

    public String getLocation() {
        return location;
    }
}
