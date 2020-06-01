package sample;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private Button newGame;
    @FXML
    void handleButtonAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
        //Main.primaryStage.close();

    }
    @FXML
    private Button tryAgain;
    @FXML
    void tryAgain(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
        Stage gameOver = (Stage) tryAgain.getScene().getWindow();
        gameOver.close();
    }

    @FXML
    private Button playAgain;
    @FXML
    void playAgain(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
        Stage newG = (Stage) playAgain.getScene().getWindow();
        newG.close();
    }
    @FXML
    public Label l11;
}
