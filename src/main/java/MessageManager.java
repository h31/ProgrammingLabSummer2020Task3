import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageManager {

    Logger log = LogManager.getLogger(MessageManager.class.getName());

    public void error(int code) {
        String title;
        String text;
        switch (code) {
            case (11):
                title = "Incorrectly entered weight of the star";
                log.error(title);
                text = "The weight must represent a rational number";
                break;
            case (12):
                title = "Incorrectly entered radius of the star";
                log.error(title);
                text = "The radius must represent a rational number";
                break;
            case (21):
                title = "Incorrectly entered gravitational constant of the star";
                log.error(title);
                text = "The gravitational constant must represent a rational number";
                break;
            case (22):
                title = "Incorrectly entered radius of the planet";
                log.error(title);
                text = "The radius must represent a rational number";
                break;
            case (23):
                title = "Incorrectly entered distance";
                log.error(title);
                text = "The distance must represent a rational number";
                break;
            case (24):
                title = "Incorrectly entered speed";
                log.error(title);
                text = "The speed and degrees must represent a rational number";
                break;
            case (25):
                title = "Incorrectly entered position";
                log.error(title);
                text = "The position must represent a rational number";
                break;
            default:
                title = "Unknown exception";
                text = "";
                break;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(text);
        alert.showAndWait();
        System.exit(1);
    }

}
