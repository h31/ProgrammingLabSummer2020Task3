package Model;
import View.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class PlayerTest {
    Player player;
    Level level;

    @Start
    public void start(Stage stage) {
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setResizable(false);
        level = new SecondLevel();
        View view = new View(stage, false, level);
        player = new Player(view, level);
        view.setPlayer(player);
        view.showScene();
    }

    @Test
    void isCollision() {
        level = new SecondLevel();
        player.setPosition(247, 470);
        assertTrue(player.isCollision());
        player.setPosition(100, 650);
        assertFalse(player.isCollision());
    }

    @Test
    void isWallCollision() {
        level = new SecondLevel();
        player.setPosition(247, 470);
        assertTrue(player.isCollision());
        player.setPosition(100, 650);
        assertFalse(player.isCollision());
    }

    @Test
    void isTriggerCollision() {
        level = new SecondLevel();
        player.setPosition(70, 30); // Триггер входа в другую локацию
        assertTrue(player.isTriggerCollision());
        player.setPosition(100,650);
        assertFalse(player.isTriggerCollision());
    }

    @Test
    void isObjectCollision() {
        level = new FirstLevel();
        player.setPosition(480, 520);
        assertFalse(player.isObjectCollision());
        player.setPosition(500, 400);
        assertFalse(player.isObjectCollision());
    }

    @Test
    void readNote() {
        level = new FirstLevel();
        ((FirstLevel) level).readNote();
        assertEquals(true, Player.reading.isVisible());
    }

    @Test
    void closeNote() {
        level = new FirstLevel();
        ((FirstLevel) level).readNote();
        player.closeNote();
        assertEquals(false, Player.reading.isVisible());
    }
}