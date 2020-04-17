package Model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class SpriteData {
    // Скелетон
    private final Image[] SKELETON_IDLE;
    //private final Image[] SKELETON_WALK_LEFT;
    //private final Image[] SKELETON_WALK_RIGHT;
    //private final Image[] SKELETON_ATTACK;

    // Игрок




    public SpriteData() {
        this.SKELETON_IDLE = getSprites("Skeleton_Idle");
        //this.SKELETON_WALK_LEFT = getSprites("Skeleton_Walk_Left");
        //this.SKELETON_WALK_RIGHT = getSprites("Skeleton_Walk_Right");
        //this.SKELETON_ATTACK = getSprites("Skeleton_Attack");
        System.out.println("Спрайты загружены");
    }

    private static Image[] getSprites(String action)  {
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

    public Image[] getSKELETON_IDLE() {
        return SKELETON_IDLE;
    }
}
