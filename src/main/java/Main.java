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
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application{


    double x = 400;
    double y = 400;
    double vx = 1;
    double vy = 1;
    double g = 6.67*Math.pow(10, -11);


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
        Stage stage = new Stage();
        stage.setTitle("Planet System");
        int radius = 20;
        Scene scene = new Scene(canvas, 1200, 600, Color.BLACK);
        Circle sun = new Circle(scene.getWidth() / 2, scene.getHeight() / 2, radius, Color.YELLOW);




        Circle point = new Circle();
        point.setFill(Color.GREEN);
        point.setCenterX(5);
        point.setCenterY(5);
        point.setRadius(15);

        canvas.getChildren().addAll(point);
        canvas.getChildren().add(sun);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double r = Math.sqrt(Math.pow(sun.getCenterX() - x, 2) + Math.pow(sun.getCenterY() - y, 2));
                double ax = g * system.weightOfStar * (sun.getCenterX()-x) / Math.pow(r, 3);
                double ay = g * system.weightOfStar * (sun.getCenterY()-y) / Math.pow(r, 3);
                vx = vx + 1 * ax;
                vy = vy + 1 * ay;
                x = x + 1 * vx;
                y = y + 1 * vy;
                point.setCenterX(x);
                point.setCenterY(y);
                canvas.requestLayout();
            }
        }, 0, 50);




        stage.setScene(scene);
        stage.show();



    }

}