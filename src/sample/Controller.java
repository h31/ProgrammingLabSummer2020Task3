package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Controller {
    @FXML
    private Button newGame;

    @FXML
    void newGame() {
        try {
            restart(newGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button tryAgain;

    @FXML
    void tryAgain() {
        try {
            restart(tryAgain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button exit;

    @FXML
    void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
        Main.stage.close();
    }

    void win() throws Exception {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (FieldDrawer.a[i][j] == 2048) {
                    Pane pane = FXMLLoader.load(getClass().getResource("win.fxml"));
                    Group root = new Group();
                    root.getChildren().add(pane);
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }
    void lose() throws Exception {
        boolean lose = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (FieldDrawer.a[i][j] == 0 || FieldDrawer.possible()) lose = false;
            }
        }
        if (lose) {
            Pane pane = FXMLLoader.load(getClass().getResource("lose.fxml"));
            Group root = new Group();
            root.getChildren().add(pane);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
    }
    static void restart(Button button) {
        FieldDrawer.score = 0;
        FieldDrawer.a = new int[4][4];
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        Main main = new Main();
        try {
            main.start(Main.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
