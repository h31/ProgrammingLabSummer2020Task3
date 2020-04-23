package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class SpriteData {
    public SpriteData() {
    }

    private static Image[] getSprites(String action) {
        List<Image> imgList = new ArrayList<>();
        Image[] imgView = new Image[0];
        File directory;
        try {
            directory = new File("src/main/resources/" + action);
            for (File element : directory.listFiles()) {
                imgList.add(new Image(element.toURI().toString()));
            }
        } catch (NullPointerException e) {
            System.out.println("Произошла ошибка, проверьте целостность данных.");
        }
        return imgList.toArray(imgView);
    }

    static class Player {
        private final Image[] SKELETON_IDLE_RIGHT;
        private final Image[] SKELETON_IDLE_LEFT;
        private final Image[] SKELETON_WALK_RIGHT;
        private final Image[] SKELETON_WALK_LEFT;
        //private final Image[] SKELETON_ATTACK_RIGHT;
        //private final Image[] SKELETON_ATTACK_LEFT;

        Player() {
            this.SKELETON_IDLE_LEFT = getSprites("Skeleton_Idle_Left");
            this.SKELETON_IDLE_RIGHT = getSprites("Skeleton_Idle_Right");
            this.SKELETON_WALK_LEFT = getSprites("Skeleton_Walk_Left");
            this.SKELETON_WALK_RIGHT = getSprites("Skeleton_Walk_Right");
            //this.SKELETON_ATTACK = getSprites("Skeleton_Attack");
        }

        public Image[] getSKELETON_IDLE(Status.View view) {
            if (view == Status.View.RIGHT) {
                return SKELETON_IDLE_RIGHT;
            } else {
                return SKELETON_IDLE_LEFT;
            }
        }


        public Image[] getSKELETON_WALK(Status.View view) {
            if (view == Status.View.RIGHT) {
                return SKELETON_WALK_RIGHT;
            } else {
                return SKELETON_WALK_LEFT;
            }
        }

    }

    static class Skeleton {
    }

    public static class Background {
        private final ImageView BACKGROUND_IMG;

        public Background() {
            this.BACKGROUND_IMG = new ImageView(new Image("background.png"));
        }
        public ImageView getBACKGROUND_IMG() {
            return BACKGROUND_IMG;
        }
    }
}
