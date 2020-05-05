import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button apply;
    @FXML
    private TextField weight;
    @FXML
    private TextField radius;
    @FXML
    private TextField distance;
    @FXML
    private Slider number;

    @FXML
    public void initialize(){
        apply.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            SystemCharacteristic system = new SystemCharacteristic();
            system.setNumberOfPlanets(number.getValue());
            system.setWeightOfStar(weight.getText());
            system.setFocusDistance(distance.getText());
            system.setRadiusOfStar(radius.getText());

            System.out.println(system.toString());
            Stage stage = (Stage) apply.getScene().getWindow();
            stage.close();
        });
    }

}