package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import java.util.LinkedList;
import java.util.List;


public abstract class Level {
    static private ImageView LEVEL_IMG = new ImageView();
    private List<Rectangle> COLLISIONS;
    private LinkedList<Trigger> TRIGGERS;
    private LevelObject[] OBJECTS;
    private String location;
    private int[] pCoord;

    public Level(String location, Image image, List<Rectangle> COLLISIONS, LinkedList<Trigger> TRIGGERS, LevelObject[] OBJECTS, int[] pCoord) {
        LEVEL_IMG.setImage(image);
        this.location = location;
        this.COLLISIONS = COLLISIONS;
        this.TRIGGERS = TRIGGERS;
        this.OBJECTS = OBJECTS;
        this.pCoord = pCoord;
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

    public List<Rectangle> getCOLLISION() {
        return COLLISIONS;
    }

    public LinkedList<Trigger> getTRIGGERS() {
        return TRIGGERS;
    }

    public LevelObject[] getOBJECTS() {
        return OBJECTS;
    }

    public int[] getpCoord() {
        return pCoord;
    }

    String getLocation() {
        return location;
    }
}
