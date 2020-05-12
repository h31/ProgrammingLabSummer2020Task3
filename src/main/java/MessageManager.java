import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MessageManager {
    public void error(int code) throws Exception {
        String title = "";
        String text = "";
        switch (code) {
            case (11):
                title = "Incorrectly entered weight";
                text = "The weight must represent a rational number";
                break;
            case (12):
                title = "Incorrectly entered radius";
                text = "The radius must represent a rational number";
                break;
            case (21):
                title = "Incorrectly entered gravitational constant";
                text = "The gravitational constant must represent a rational number";
                break;
            case (22):
                title = "Incorrectly entered radius of the planet";
                text = "The radius must represent a rational number";
                break;
            case (23):
                title = "Incorrectly entered distance";
                text = "The distance must represent a rational number";
                break;
            case (24):
                title = "Incorrectly entered speed";
                text = "The speed and degrees must represent a rational number";
                break;
            case (25):
                title = "Incorrectly entered position";
                text = "The position must represent a rational number";
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
