package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

final class SpriteData {
    static ImageView[] getSprites(String folder) {
        List<ImageView> imgList = new ArrayList<>();
        ImageView[] imgView = new ImageView[0];
        File directory;
        try {
            directory = new File("src/main/resources/" + folder);
            for (File element : directory.listFiles()) {
                ImageView image = new ImageView(new Image(element.toURI().toString()));
                imgList.add(image);
            }
        } catch (NullPointerException e) {
            System.out.println("Произошла ошибка, проверьте целостность данных.");
        }
        return imgList.toArray(imgView);
    }

    static LevelObject[] getLevelObjects(String folder) {
        List<LevelObject> imgList = new ArrayList<>();
        LevelObject[] levelObjects = new LevelObject[0];
        File directory;
        try {
            directory = new File("src/main/resources/" + folder);
            for (File element : directory.listFiles()) {
                LevelObject object = new LevelObject(new ImageView(new Image(element.toURI().toString())));
                imgList.add(object);
            }
        } catch (NullPointerException e) {
            System.out.println("Произошла ошибка, проверьте целостность данных.");
        }
        return imgList.toArray(levelObjects);
    }
}

