import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerOfTheSystem {

    @FXML
    private Button apply;
    @FXML
    private TextField weight;
    @FXML
    private TextField radius;
    @FXML
    private Slider number;

    @FXML
    public void initialize() {
        apply.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) apply.getScene().getWindow();
            stage.close();
            SystemCharacteristic system = new SystemCharacteristic();
            system.setWeightOfStar(weight.getText());
            system.setRadiusOfStar(radius.getText());
            system.setNumberOfPlanets(number.getValue());

                App planet = new App();
                try {
                    planet.planetSetup(system);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        });

    }

}