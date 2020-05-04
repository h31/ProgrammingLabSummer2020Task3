package Model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

final class SpriteData {
    static Image[] getSprites(String action) {
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
}
