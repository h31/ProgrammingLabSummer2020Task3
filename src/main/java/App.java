import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class App extends Application{


    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/SystemParameters.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void planetSetup(SystemCharacteristic system) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/PlanetParameters.fxml").toURI().toURL());
        Parent root1 = loader.load();
        ControllerOfThePlanet controller = loader.getController();
        controller.initialize(system);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void space(SystemCharacteristic system) {
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        Stage stage = new Stage();
        stage.setTitle("Planet System");
        double radius = system.radiusOfStar / 2;
        Scene scene = new Scene(canvas, 1200, 600, Color.BLACK);
        Circle star = new Circle(scene.getWidth() / 2, scene.getHeight() / 2, radius, Color.YELLOW);
        canvas.getChildren().add(star);
        Tooltip.install(star, new Tooltip("Star \nWeight: " + system.weightOfStar + "\n" +
                "Radius: " + system.radiusOfStar));

        Button slower = new Button("Slower");
        slower.setPrefWidth(80);
        slower.setLayoutX(470);
        canvas.getChildren().addAll(slower);

        Button pause = new Button("Pause");
        pause.setPrefWidth(80);
        pause.setLayoutX(560);
        canvas.getChildren().addAll(pause);

        var ref = new Object() {
            boolean p = false;
        };
        pause.setOnAction(event -> {
            if (!ref.p) {
                ref.p = true;
                pause.setText("Play");
            }
            else {
                ref.p = false;
                pause.setText("Pause");
            }
        });

        Button faster = new Button("Faster");
        faster.setPrefWidth(80);
        faster.setLayoutX(650);
        canvas.getChildren().addAll(faster);
        AtomicInteger s = new AtomicInteger(1);


        for (int i = 0; i < system.planet.size(); i++) {
            int finalI = i;
            Circle planet = new Circle();

            planet.setFill(Paint.valueOf(system.planet.get(i).color));
            planet.setRadius(system.planet.get(i).radius / 2);
            canvas.getChildren().addAll(planet);

            final double[] vx = {system.planet.get(finalI).speedX};
            final double[] vy = {system.planet.get(finalI).speedY};
            final double[] x = {system.planet.get(finalI).positionX + star.getCenterX()};
            final double[] y = {system.planet.get(finalI).positionY + star.getCenterY()};


            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

                    double r = Math.sqrt(Math.pow(star.getCenterX() - x[0], 2) + Math.pow(star.getCenterY() - y[0], 2));
                    double ax = system.planet.get(finalI).G * system.weightOfStar * (star.getCenterX() - x[0]) / Math.pow(r, 3);
                    double ay = system.planet.get(finalI).G * system.weightOfStar * (star.getCenterY() - y[0]) / Math.pow(r, 3);
                    vx[0] = vx[0] + 1 * ax;
                    vy[0] = vy[0] + 1 * ay;
                    x[0] = (x[0] + vx[0] + Math.random() * 0.00001);
                    y[0] = (y[0] + vy[0] + Math.random() * 0.00001);
                    planet.setCenterX(x[0]);
                    planet.setCenterY(y[0]);
                    canvas.requestLayout();

                Tooltip.install(planet, new Tooltip(system.planet.get(finalI).toString() + "\n"
                        + "Distance to the star " + r));
            }));
            timeline.setCycleCount(Animation.INDEFINITE);

            timeline.play();

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (ref.p) {
                        timeline.stop();
                    }
                    else {
                        timeline.play();
                    }
                    slower.setOnAction(event -> s.getAndDecrement());
                    faster.setOnAction(event -> s.getAndIncrement());
                    timeline.setRate(50 + 5 * s.get());
                }


            }, 0, 20);

        }
        stage.setScene(scene);

        stage.show();


    }

}