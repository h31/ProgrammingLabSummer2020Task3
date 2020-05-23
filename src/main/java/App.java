import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class App extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    static LogicManager logic = new LogicManager();

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
        int rand = (1 + (int) (Math.random() * 6));
        root1.setStyle("-fx-background-image: url("+"images/" + rand +".jpg "+")");
        stage.show();
    }

    public static void space(SystemCharacteristic system) throws FileNotFoundException {
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-image: url(images/background.jpg)");
        Stage stage = new Stage();
        stage.setTitle("Planet System");
        double radius = system.radiusOfStar / 2;
        Scene scene = new Scene(canvas, 1200, 600, Color.BLACK);

        Image image = new Image(new FileInputStream("src/main/resources/images/star.png"));
        ImageView star = new ImageView(image);
        star.setX(scene.getWidth() / 2 - radius);
        star.setY(scene.getHeight() / 2 - radius);
        star.setFitHeight(radius * 2);
        star.setFitWidth(radius * 2);
        star.setPreserveRatio(true);
        canvas.getChildren().add(star);

        Tooltip.install(star, new Tooltip("Star \nMass: " + system.massOfStar + "\n" +
                "Radius: " + system.radiusOfStar));

        Button pause = new Button("Pause");
        pause.setPrefWidth(80);
        pause.setLayoutX(1100);
        canvas.getChildren().addAll(pause);

        final boolean[] p = {false};

        pause.setOnAction(event -> {
            if (!p[0]) {
                p[0] = true;
                pause.setText("Play");
            } else {
                p[0] = false;
                pause.setText("Pause");
            }
        });
        Pagination animationSpeed = new Pagination(10, 0);
        TextField timePortation = new TextField();
        timePortation.setLayoutY(80);
        Button tPBtn = new Button("Portation");
        tPBtn.setLayoutY(110);
        canvas.getChildren().addAll(animationSpeed, timePortation, tPBtn);

        boolean[] timePort = new boolean[system.numberOfPlanets];
        AtomicReference<Double> a = new AtomicReference<>((double) 0);
        tPBtn.setOnAction(event -> {
            if (timePortation.getText().matches("[0-9]+(,.[0-9]+)?")) {
                Arrays.fill(timePort, true);
                a.set(Double.parseDouble(timePortation.getText().replace(',', '.')));
            }
        });

        for (int i = 0; i < system.planet.size(); i++) {
            int finalI = i;
            Circle planet = new Circle();
            planet.setRadius(system.planet.get(i).radius / 2);
            canvas.getChildren().add(planet);

            final double[] vx = {system.planet.get(finalI).speedX};
            final double[] vy = {system.planet.get(finalI).speedY};
            final double[] x = {system.planet.get(finalI).positionX + star.getX() + star.getFitWidth() / 2};
            final double[] y = {system.planet.get(finalI).positionY + star.getY() + star.getFitHeight() / 2};



            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {

                double distance = logic.distance(star.getX() + star.getFitWidth() / 2, star.getY() + star.getFitHeight() / 2, x[0],  y[0]);
                vx[0] += logic.acceleration(system.planet.get(finalI).GC, system.massOfStar, star.getX() + star.getFitWidth() / 2, x[0], distance);
                vy[0] += logic.acceleration(system.planet.get(finalI).GC, system.massOfStar, star.getY() + star.getFitHeight() / 2, y[0], distance);
                x[0] += vx[0];
                y[0] += vy[0];
                planet.setCenterX(x[0]);
                planet.setCenterY(y[0]);
                canvas.requestLayout();

                if (timePort[finalI]) {
                    double[] tpXY = logic.timePortation(a.get(), x[0], y[0], vx[0], vy[0], star.getX() + star.getFitWidth() / 2, star.getY() + star.getFitHeight() / 2, system.planet.get(finalI).GC, system.massOfStar);
                    x[0] = tpXY[0];
                    y[0] = tpXY[1];
                    vx[0] = tpXY[2];
                    vy[0] = tpXY[3];
                    timePort[finalI] = false;
                }

                RadialGradient gradient = new RadialGradient(0,
                        .1,
                        planet.getCenterX(),
                        planet.getCenterY(),
                        planet.getRadius(),
                        false,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, (Color) Paint.valueOf(system.planet.get(finalI).color)),
                        new Stop(0.9, Color.BLACK));

                planet.setFill(gradient);

                Tooltip.install(planet, new Tooltip(system.planet.get(finalI).toShortString() + "\n"
                        + "Distance to the star " + distance));
            }));
            timeline.setCycleCount(Animation.INDEFINITE);

            timeline.play();

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (p[0]) timeline.stop();
                    else timeline.play();
                    timeline.setRate(10 * (animationSpeed.getCurrentPageIndex() + 1));
                }

            }, 0, 1);

        }
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
    }
}