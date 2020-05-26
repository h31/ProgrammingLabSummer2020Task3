import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;


class Painter {

    static Group draw(int[][] field) {
        Group group = new Group();
        group.getChildren().add(Main.fieldTexture);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 0)
                    continue;
                InputStream block = Painter.class.getResourceAsStream("/texture/" + field[i][j] + ".png");
                Image img = new Image(block);
                ImageView imageView = new ImageView(img);
                imageView.setLayoutX(i * 100);
                imageView.setLayoutY(20 + j * 100);
                group.getChildren().add(imageView);
        }
        return group;
    }
}
