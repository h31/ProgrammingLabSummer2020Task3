import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerOfTheSystem {

    Logger log = LogManager.getLogger(ControllerOfTheSystem.class.getName());

    @FXML
    private Button help;
    @FXML
    private Button apply;
    @FXML
    private TextField mass;
    @FXML
    private TextField radius;
    @FXML
    private Slider number;
    @FXML
    private void enabler(boolean[] w, boolean[] r) {
        apply.setDisable(!w[0] || !r[0]);
    }

    @FXML
    public void initialize() {
        help.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setContentText("In this window, specify the mass and radius of the star in accordance with the specified explanations.\n" +
                    "For example, mass = 900, radius = 80");
            alert.showAndWait();
        });
        final boolean[] weightB = {false};
        final boolean[] radiusB = {false};
        String regex = "[0-9]+([.,][0-9]+)?";
        mass.setOnKeyTyped(event -> {
            weightB[0] = mass.getText().matches(regex);
            enabler(radiusB, weightB);
        });
        radius.setOnKeyTyped(event -> {
            radiusB[0] = radius.getText().matches(regex);
            enabler(radiusB, weightB);
        });
        apply.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) apply.getScene().getWindow();
            stage.close();
            SystemCharacteristic system = new SystemCharacteristic();
            try {
                system.setMassOfStar(mass.getText());
                system.setRadiusOfStar(radius.getText());
            } catch (Exception e) {
                log.error("Exception " + e);
                e.printStackTrace();
            }
            system.setNumberOfPlanets(number.getValue());

            log.info(system.toString());
                App planet = new App();
                try {
                    planet.planetSetup(system);
                } catch (Exception e) {
                    log.error("Exception " + e);
                    e.printStackTrace();
                }
        });
    }
}