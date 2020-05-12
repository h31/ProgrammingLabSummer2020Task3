import javafx.scene.control.Alert;

public class MessageManager {
    public void error(){




        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect data format");
        alert.setContentText("Ooops, there was an error!");

        alert.showAndWait();
        System.exit(1);
    }

}
