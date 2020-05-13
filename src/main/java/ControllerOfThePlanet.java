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
    private TextField namePl;
    @FXML
    private ColorPicker colorPl;
    @FXML
    private TextField radiusPl;
    @FXML
    private Button applyPl;
    @FXML
    private TextField gPl;
    @FXML
    private TextField positionXPl;
    @FXML
    private TextField positionYPl;
    @FXML
    private TextField degreesPl;
    @FXML
    private TextField speedPl;

    private int filled = 0;

    private void enabler() {
        applyPl.setDisable(filled != 7);
    }

    private void checker (String text) {
        if (!text.equals("")) filled++;
        else filled--;
        enabler();
    }

    @FXML
    public void initialize(SystemCharacteristic system){

        namePl.setOnKeyPressed(event -> checker(namePl.getText()));
        radiusPl.setOnKeyPressed(event -> checker(radiusPl.getText()));
        gPl.setOnKeyPressed(event -> checker(gPl.getText()));
        positionXPl.setOnKeyPressed(event -> checker(positionXPl.getText()));
        positionYPl.setOnKeyPressed(event -> checker(positionYPl.getText()));
        degreesPl.setOnKeyPressed(event -> checker(degreesPl.getText()));
        speedPl.setOnKeyPressed(event -> checker(speedPl.getText()));

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