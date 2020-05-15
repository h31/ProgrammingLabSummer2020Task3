import com.sun.glass.ui.Robot;
import javafx.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Controller {

    public void restart(ActionEvent actionEvent) {
        Field.field = new  int[4][4];
        Field.score = 0;
        Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
        robot.keyPress(KeyEvent.VK_UP);
    }
}
