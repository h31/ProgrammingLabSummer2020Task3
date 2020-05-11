import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/SystemParameters.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void planetSetup(SystemCharacteristic system) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/PlanetParameters.fxml").toURI().toURL());
        Parent root1 = loader.load();
        ControllerOfThePlanet controller = loader.getController();
        controller.initialize(system);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void space(SystemCharacteristic system) throws Exception {
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        int radius = 20;
        Scene scene = new Scene(canvas, 1200, 600, Color.BLACK);
        Circle ball = new Circle(10, Color.RED);
        ball.relocate(0, 10);
        Circle sun = new Circle(scene.getWidth() / 2 + (system.focusDistance / 2), scene.getHeight() / 2, radius, Color.YELLOW);
        {
            Line crossLine1 = new Line();
            crossLine1.setStartX(scene.getWidth() / 2 - (system.focusDistance / 2) + (radius / 2));
            crossLine1.setStartY(scene.getHeight() / 2 - (radius / 2));
            crossLine1.setEndX(scene.getWidth() / 2 - (system.focusDistance / 2) - (radius / 2));
            crossLine1.setEndY(scene.getHeight() / 2 + (radius / 2));
            crossLine1.setStrokeWidth(1.5);
            crossLine1.setStroke(Color.RED);
            Line crossLine2 = new Line();
            crossLine2.setStartX(scene.getWidth() / 2 - (system.focusDistance / 2) - (radius / 2));
            crossLine2.setStartY(scene.getHeight() / 2 - (radius / 2));
            crossLine2.setEndX(scene.getWidth() / 2 - (system.focusDistance / 2) + (radius / 2));
            crossLine2.setEndY(scene.getHeight() / 2 + (radius / 2));
            crossLine2.setStrokeWidth(1.5);
            crossLine2.setStroke(Color.RED);
            canvas.getChildren().add(crossLine1);
            canvas.getChildren().add(crossLine2);
        }

        canvas.getChildren().add(sun);
        canvas.getChildren().add(ball);
        Stage stage = new Stage();
        stage.setTitle("Planet System");
        stage.setScene(scene);
        stage.show();



    }

}