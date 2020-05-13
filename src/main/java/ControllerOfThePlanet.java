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

    boolean[] filled = new boolean[7];

    private boolean check = false;

    private void enabler(String text, int num) {
        filled[num] = !text.equals("");
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

        namePl.setOnKeyTyped(event -> enabler(namePl.getText(), 0));
        radiusPl.setOnKeyTyped(event -> enabler(radiusPl.getText(), 1));
        gPl.setOnKeyTyped(event -> enabler(gPl.getText(), 2));
        positionXPl.setOnKeyTyped(event -> enabler(positionXPl.getText(), 3));
        positionYPl.setOnKeyTyped(event -> enabler(positionYPl.getText(), 4));
        degreesPl.setOnKeyTyped(event -> enabler(degreesPl.getText(), 5));
        speedPl.setOnKeyTyped(event -> enabler(speedPl.getText(), 6));

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