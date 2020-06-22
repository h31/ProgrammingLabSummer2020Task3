package Model;

import View.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class FirstLevelTest {
    private FirstLevel level;
    private Player player;

    @Start
    public void start(Stage stage) {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setResizable(false);
        level = new FirstLevel();
        View view = new View(stage, false, level);
        player = new Player(view, level);
        view.setPlayer(player);
        view.showScene();
    }


    @Test
    void checkObjectView() {
        player.setBOTTOM_COLLISION(0);
        level.checkObjectView(player);
        for (LevelObject colShape : level.getOBJECTS()) {
            assertEquals(OBJECT_VIEW.FRONT, colShape.getView());
        }
        player.setBOTTOM_COLLISION(768);
        level.checkObjectView(player);
        for (LevelObject colShape : level.getOBJECTS()) {
            assertEquals(OBJECT_VIEW.BACK, colShape.getView());
        }
    }
}