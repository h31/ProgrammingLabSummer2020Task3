import com.sun.glass.ui.Robot;
import java.awt.event.KeyEvent;

public class Controller {

    public void restart() {
        Field field = new Field();
        Field.field = new  int[4][4];
        field.score = 0;
        Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
        robot.keyPress(KeyEvent.VK_UP);
    }
}
