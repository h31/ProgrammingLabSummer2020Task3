package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Level {
    private final ImageView LEVEL_IMG;

    public Level(String location) {
        switch (location) {
            case "Start": this.LEVEL_IMG = new ImageView(new Image("background.png"));
            break;
            default: throw new IllegalArgumentException("There are no location such like this");
        }
    }
    public ImageView getLEVEL_IMG() {
        return LEVEL_IMG;
    }
}
