package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public final class Level {
    private ImageView LEVEL_IMG;
    private Rectangle[] COLLISIONS;
    private Rectangle[] TRIGGERS;
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

    public static final Rectangle[] START_TRIGGERS = {
            new Rectangle(425, 381, 30, 30)
    };

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
            new Rectangle(490, 388, 525, 30)
    };
    public static final Rectangle[] FIRST_TRIGGERS = {

    };
    public static final LevelObject[] FIRST_OBJECTS = SpriteData.getLevelObjects("FIRST_OBJECTS");

    public static final int[] FIRST_pCOORD = {
            800,
            600
    };
    public Level() {
        this.LEVEL_IMG = new ImageView(new Image("background.png"));
        this.COLLISIONS = START_COLLISIONS;
        this.TRIGGERS = START_TRIGGERS;
        this.OBJECTS = START_OBJECTS;
        this.location = "Start";
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
                this.getLEVEL_IMG().setImage(new Image("/Monsters_Creatures_Fantasy/Skeleton/Death.png"));
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
            //System.out.println();
        }
    }

    public ImageView getLEVEL_IMG() {
        return LEVEL_IMG;
    }

    public Rectangle[] getCOLLISION() {
        return COLLISIONS;
    }

    public Rectangle[] getTRIGGERS() {
        return TRIGGERS;
    }

    public LevelObject[] getOBJECTS() {
        return OBJECTS;
    }

    public String getLocation() {
        return location;
    }
}
