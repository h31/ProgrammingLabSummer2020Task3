import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

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
        double radius = system.radiusOfStar / 2;
        Scene scene = new Scene(canvas, 1200, 600, Color.BLACK);
        Circle sun = new Circle(scene.getWidth() / 2, scene.getHeight() / 2, radius, Color.YELLOW);
        canvas.getChildren().add(sun);


        Button slower = new Button("Slower");
        slower.setPrefWidth(80);
        slower.setLayoutX(470);
        canvas.getChildren().addAll(slower);

        Button pause = new Button("Pause");
        pause.setPrefWidth(80);
        pause.setLayoutX(560);
        canvas.getChildren().addAll(pause);

        Button faster = new Button("Faster");
        faster.setPrefWidth(80);
        faster.setLayoutX(650);
        canvas.getChildren().addAll(faster);



        for (int i = 0; i < system.planet.size(); i++) {
            int finalI = i;
            Circle point = new Circle();

            point.setFill(Paint.valueOf(system.planet.get(i).color));
            point.setRadius(system.planet.get(i).radius / 2);
            canvas.getChildren().addAll(point);

            final double[] vx = {system.planet.get(finalI).speedX};
            final double[] vy = {system.planet.get(finalI).speedY};
            final double[] x = {system.planet.get(finalI).positionX + sun.getCenterX()};
            final double[] y = {system.planet.get(finalI).positionY + sun.getCenterY()};


            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

                    double r = Math.sqrt(Math.pow(sun.getCenterX() - x[0], 2) + Math.pow(sun.getCenterY() - y[0], 2));
                    double ax = system.planet.get(finalI).G * system.weightOfStar * (sun.getCenterX() - x[0]) / Math.pow(r, 3);
                    double ay = system.planet.get(finalI).G * system.weightOfStar * (sun.getCenterY() - y[0]) / Math.pow(r, 3);
                    vx[0] = vx[0] + 1 * ax;
                    vy[0] = vy[0] + 1 * ay;
                    x[0] = (x[0] + vx[0] + Math.random() * 0.00001);
                    y[0] = (y[0] + vy[0] + Math.random() * 0.00001);
                    point.setCenterX(x[0]);
                    point.setCenterY(y[0]);
                    canvas.requestLayout();


            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.setRate(50);
            timeline.play();
            var ref = new Object() {
                boolean p = false;
            };
            pause.setOnAction(event -> {
                if (!ref.p) {
                    timeline.pause();
                    ref.p = true;
                    pause.setText("Play");
                }
                else {
                    timeline.play();
                    ref.p = false;
                    pause.setText("Pause");
                }
            });

            slower.setOnAction(event -> {
                timeline.setRate(timeline.getRate() - 5);
            });

            faster.setOnAction(event -> {
                timeline.setRate(timeline.getRate() + 5);
            });

        }
        stage.setScene(scene);

        stage.show();


    }

}