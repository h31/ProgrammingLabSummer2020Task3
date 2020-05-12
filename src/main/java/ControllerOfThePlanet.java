import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerOfThePlanet {

    Logger log = LogManager.getLogger(MessageManager.class.getName());


    @FXML
    public TextField namePl;
    @FXML
    public ColorPicker colorPl;
    @FXML
    public TextField radiusPl;
    @FXML
    public Button applyPl;
    @FXML
    public TextField gPl;
    @FXML
    public TextField positionXPl;
    @FXML
    public TextField positionYPl;
    @FXML
    public TextField degreesPl;
    @FXML
    public TextField speedPl;


    @FXML
    public void initialize(SystemCharacteristic system){

        applyPl.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) applyPl.getScene().getWindow();
            stage.close();
            PlanetCharacteristic planet = new PlanetCharacteristic();
            planet.setName(namePl.getText());
            planet.setColor(colorPl.getValue());
            try {
                planet.setRadius(radiusPl.getText());
                planet.setG(gPl.getText());
                planet.setPositionX(positionXPl.getText());
                planet.setPositionY(positionYPl.getText());
                planet.setSpeed(speedPl.getText(), degreesPl.getText());

            } catch (Exception e) {
                log.error("Exception " + e);
                e.printStackTrace();
            }
            system.planet.add(planet);
            log.info(planet.toString());
            App main = new App();
            try {
                if (system.planet.size() == system.numberOfPlanets) {
                    main.space(system);
                }
                else {
                    main.planetSetup(system);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}