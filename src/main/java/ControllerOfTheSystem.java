import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerOfTheSystem {

    Logger log = LogManager.getLogger(MessageManager.class.getName());

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
            try {
                system.setWeightOfStar(weight.getText());
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