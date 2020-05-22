import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerOfThePlanet {

    Logger log = LogManager.getLogger(ControllerOfThePlanet.class.getName());

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

    boolean[] filled = new boolean[7];

    private boolean check = false;

    private void enabler(boolean test, int num) {
        filled[num] = test;
        for (boolean b : filled) {
            if (!b) {
                check = false;
                break;
            } else {
                check = true;
            }
        }
        applyPl.setDisable(!check);
    }

    @FXML
    public void initialize(SystemCharacteristic system){
        String regex = "[0-9]+([.,][0-9]+)?";
        namePl.setOnKeyTyped(event -> enabler(!namePl.getText().isBlank(), 0));
        radiusPl.setOnKeyTyped(event -> enabler(radiusPl.getText().matches(regex), 1));
        gPl.setOnKeyTyped(event -> enabler(gPl.getText().matches(regex), 2));
        positionXPl.setOnKeyTyped(event -> enabler(positionXPl.getText().matches("-?" + regex), 3));
        positionYPl.setOnKeyTyped(event -> enabler(positionYPl.getText().matches("-?" + regex), 4));
        degreesPl.setOnKeyTyped(event -> enabler(degreesPl.getText().matches("-?" + regex), 5));
        speedPl.setOnKeyTyped(event -> enabler(speedPl.getText().matches(regex), 6));

        applyPl.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {


            Stage stage = (Stage) applyPl.getScene().getWindow();
            stage.close();
            PlanetCharacteristic planet = new PlanetCharacteristic();
            planet.setName(namePl.getText());
            planet.setColor(colorPl.getValue());
            try {
                planet.setRadius(radiusPl.getText());
                planet.setGC(gPl.getText());
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