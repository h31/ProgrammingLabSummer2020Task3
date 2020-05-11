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
    public TextField weightPl;
    @FXML
    public ColorPicker colorPl;
    @FXML
    public TextField perihelionPl;
    @FXML
    public TextField radiusPl;
    @FXML
    public TextField positionPl;
    @FXML
    public Button applyPl;



    @FXML
    public void initialize(SystemCharacteristic system){

        applyPl.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) applyPl.getScene().getWindow();
            stage.close();
            PlanetCharacteristic planet = new PlanetCharacteristic();
            planet.setName(namePl.getText());
            planet.setWeight(weightPl.getText());
            planet.setColor(colorPl.getValue());
            planet.setRadius(radiusPl.getText());
            planet.setPerihelion(perihelionPl.getText());
            planet.setPosition(positionPl.getText());
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