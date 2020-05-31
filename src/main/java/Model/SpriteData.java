package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class SpriteData {
    static ImageView[] getSprites(String folder) {
        List<ImageView> imgList = new ArrayList<>();
        ImageView[] imgView = new ImageView[0];
        File directory;
        try {
            directory = new File("src/main/resources/" + folder);
            for (File element : Objects.requireNonNull(directory.listFiles())) {
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
            for (File element : Objects.requireNonNull(directory.listFiles())) {
                LevelObject object = new LevelObject(new ImageView(new Image(element.toURI().toString())));
                imgList.add(object);
            }
        } catch (NullPointerException e) {
            System.out.println("Произошла ошибка, проверьте целостность данных.");
        }
        return imgList.toArray(levelObjects);
    }

    static ImageView getSprite(String path) {
        File directory = new File("src/main/resources/" + path);
        ImageView image;
        if (directory.isFile()) {
            image = new ImageView(new Image(directory.toURI().toString()));
            return image;
        } else return null;
    }

    static Rectangle spriteToCollision(ImageView img) {
        return new Rectangle(img.getX(), img.getY(), img.getImage().getWidth(), img.getImage().getHeight());
    }
}

