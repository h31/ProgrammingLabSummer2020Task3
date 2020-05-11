import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

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
        Stage stage = new Stage();
        stage.setTitle("Planet System");
        int radius = 20;
        Scene scene = new Scene(canvas, 1200, 600, Color.BLACK);
        Circle sun = new Circle(scene.getWidth() / 2, scene.getHeight() / 2, radius, Color.YELLOW);
        canvas.getChildren().add(sun);


        for (int i = 0; i < system.planet.size(); i++) {
            int finalI = i;
            Circle point = new Circle();

            point.setFill(Paint.valueOf(system.planet.get(i).color));
            point.setCenterX(5);
            point.setCenterY(5);
            point.setRadius(15);
            canvas.getChildren().addAll(point);

            final double[] vx = {system.planet.get(finalI).speedX};
            final double[] vy = {system.planet.get(finalI).speedY};
            final double[] x = {system.planet.get(finalI).positionX + sun.getCenterX()};
            final double[] y = {system.planet.get(finalI).positionY + sun.getCenterY()};


            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    double r = Math.sqrt(Math.pow(sun.getCenterX() - x[0], 2) + Math.pow(sun.getCenterY() - y[0], 2));
                    double ax = system.planet.get(finalI).G * system.weightOfStar * (sun.getCenterX() - x[0]) / Math.pow(r, 3);
                    double ay = system.planet.get(finalI).G * system.weightOfStar * (sun.getCenterY() - y[0]) / Math.pow(r, 3);
                    vx[0] = vx[0] + 1 * ax;
                    vy[0] = vy[0] + 1 * ay;
                    x[0] = x[0] + 1 * vx[0];
                    y[0] = y[0] + 1 * vy[0];
                    point.setCenterX(x[0]);
                    point.setCenterY(y[0]);
                    canvas.requestLayout();
                }
            }, 0, 50);

        }
        stage.setScene(scene);
        stage.show();



    }

}