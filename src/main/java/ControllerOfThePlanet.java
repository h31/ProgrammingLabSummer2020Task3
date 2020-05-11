import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerOfThePlanet {

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
            planet.setRadius(radiusPl.getText());
            planet.setG(gPl.getText());
            planet.setPositionX(positionXPl.getText());
            planet.setPositionY(positionYPl.getText());
            planet.setSpeed(speedPl.getText());
            planet.setDegrees(degreesPl.getText());
            system.planet.add(planet);
            if (system.planet.size() == system.numberOfPlanets) {
                Main main = new Main();
                try {

                    main.space(system);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}