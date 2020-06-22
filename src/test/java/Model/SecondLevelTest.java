package Model;

import View.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class SecondLevelTest {
    private SecondLevel level;

    @Start
    public void start(Stage stage) {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setResizable(false);
        level = new SecondLevel();
        View view = new View(stage, false, level);
        Player player = new Player(view, level);
        view.setPlayer(player);
        view.showScene();
    }

    @Test
    void openWall() {
        level.openWall(level.getTRIGGERS().get(0));
        assertTrue(level.getTRIGGERS().get(0).getUsed());
    }

    @Test
    void stopTeslaOrb() {
        level.stopTeslaOrb(level.getTRIGGERS().get(6));
        assertTrue(level.getTRIGGERS().get(6).getUsed());
    }
}