package project;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Application {
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        final Controller controller = new Controller();

        stage.setHeight(550);
        stage.setWidth(1000);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Балда");

        controller.firstPlayerText.setFont(Font.font(null, 20));
        controller.secondPlayerText.setFont(Font.font(null, 20));

        controller.firstScoreText.setFont(Font.font(null, 20));
        controller.secondScoreText.setFont(Font.font(null, 20));

        controller.restart.setLayoutX(950);
        controller.restart.setLayoutY(100);

        stage.show();
    }
}
