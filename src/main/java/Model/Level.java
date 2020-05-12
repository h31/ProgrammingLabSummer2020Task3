package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public final class Level {
    private ImageView LEVEL_IMG;
    private Rectangle[] COLLISIONS;
    private Rectangle[] TRIGGERS;
    private String location;

    public static final Rectangle[] START_COLLISIONS = {
            new Rectangle(320, 250, 400, 60),
            new Rectangle(230, 152, 500, 25),
    };

    public static final Rectangle[] START_TRIGGERS = {
            new Rectangle(490, 388, 525, 30)
    };

    public static final Rectangle[] FIRST_COLLISION = {
            new Rectangle(490, 388, 525, 30)
    };
    public static final Rectangle[] FIRST_TRIGGERS = {
            new Rectangle(350, 150, 320, 30)
    };

    {
        FIRST_TRIGGERS[0].setFill(Color.YELLOW);
        START_TRIGGERS[0].setFill(Color.YELLOW);
    }

    public Level() {
        this.LEVEL_IMG = new ImageView(new Image("/Monsters_Creatures_Fantasy/All Characters.png"));
        this.COLLISIONS = START_COLLISIONS;
        this.TRIGGERS = START_TRIGGERS;
        this.location = "Start";
    }

    public void setLocation(String location) {
        if (location.equals("Start")) {
            try {
                this.getLEVEL_IMG().setImage(new Image("/Monsters_Creatures_Fantasy/All Characters.png"));
            } catch (NullPointerException e) {
                System.out.println("Произошла ошибка :" + e.toString());
            }
            this.location = "Start";
            this.COLLISIONS = START_COLLISIONS;
            this.TRIGGERS = START_TRIGGERS;
        } else if (location.equals("First")) {
            try {
                this.getLEVEL_IMG().setImage(new Image("/Monsters_Creatures_Fantasy/Skeleton/Death.png"));
            } catch (NullPointerException e) {
                System.out.println("Произошла ошибка :" + e.toString());
            }
            this.location = "First";
            this.COLLISIONS = FIRST_COLLISION;
            this.TRIGGERS = FIRST_TRIGGERS;
        } else {
            throw new IllegalArgumentException("There are no location such like this");
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

    public String getLocation() {
        return location;
    }
}
