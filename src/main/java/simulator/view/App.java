package simulator.view;

import simulator.controller.ControllerOfThePlanet;
import simulator.model.FileManager;
import simulator.model.LogicManager;
import simulator.model.SystemCharacteristic;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class App extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Stage stageFile;
    static LogicManager logic = new LogicManager();

    public void start(Stage stage) throws Exception {
        var loader = new FXMLLoader(new File("src/main/resources/SystemParameters.fxml").toURI().toURL());
        Parent root = loader.load();
        var scene = new Scene(root);
        stage.setTitle("Configuring the system");
        stage.setScene(scene);
        stage.show();
        stageFile = stage;
    }

    public void planetSetup(SystemCharacteristic system) throws Exception {
        var loader = new FXMLLoader(new File("src/main/resources/PlanetParameters.fxml").toURI().toURL());
        Parent root = loader.load();
        ControllerOfThePlanet controller = loader.getController();
        controller.initialize(system);
        var stage = new Stage();
        stage.setTitle("Configuring the planet");
        stage.setScene(new Scene(root));
        var rand = (1 + (int) (Math.random() * 6));
        root.setStyle(String.format("-fx-background-image: url(images/%d.jpg)", rand));
        stage.show();
    }

    public void space(SystemCharacteristic system) throws FileNotFoundException {
        var canvas = new Pane();
        canvas.setStyle("-fx-background-image: url(images/background.jpg)");
        var stage = new Stage();
        stage.setTitle("Planet System");
        var radius = system.radiusOfStar / 2;
        var scene = new Scene(canvas, 1200, 600, Color.BLACK);

        var image = new Image(new FileInputStream("src/main/resources/images/star.png"));
        var star = new ImageView(image);
        star.setX(scene.getWidth() / 2 - radius);
        star.setY(scene.getHeight() / 2 - radius);
        star.setFitHeight(radius * 2);
        star.setFitWidth(radius * 2);
        star.setPreserveRatio(true);
        var starCenterX = star.getX() + star.getFitWidth() / 2;
        var starCenterY = star.getY() + star.getFitHeight() / 2;
        canvas.getChildren().add(star);



        Tooltip.install(star, new Tooltip("Star \nMass: " + system.massOfStar + "\n" +
                "Radius: " + system.radiusOfStar));

        var pause = new Button("Pause");
        pause.setPrefWidth(80);
        pause.setLayoutX(1100);
        canvas.getChildren().addAll(pause);

        var p = new AtomicBoolean(false);

        pause.setOnAction(event -> {
            if (!p.get()) {
                p.set(true);
                pause.setText("Play");
            } else {
                p.set(false);
                pause.setText("Pause");
            }
        });
        var animationSpeed = new Pagination(10, 0);
        var timePortation = new TextField();
        timePortation.setMaxWidth(50);
        timePortation.setLayoutX(75);
        timePortation.setLayoutY(50);
        var tPBtn = new Button("Portation");
        tPBtn.setLayoutY(50);
        tPBtn.setLayoutX(135);

        var timePort = new boolean[system.numberOfPlanets];
        var a = new AtomicReference<>((double) 0);
        tPBtn.setOnAction(event -> {
            if (timePortation.getText().matches("[0-9]+(,.[0-9]+)?")) {
                Arrays.fill(timePort, true);
                a.set(Double.parseDouble(timePortation.getText().replace(',', '.')));
            }
        });

        var saveBtn = new Button("Save config");
        saveBtn.setLayoutY(570);
        saveBtn.setLayoutX(10);
        saveBtn.setOnAction(event -> {
            IntStream.range(0, system.planet.size()).forEach(i -> {
                system.planet.get(i).positionX -= starCenterX;
                system.planet.get(i).positionY -= starCenterY;
            });
            FileManager.save(system);
        });

        canvas.getChildren().addAll(animationSpeed, timePortation, tPBtn, saveBtn);

        IntStream.range(0, system.planet.size()).forEach(i -> {
            var planet = new Circle();
            planet.setRadius(system.planet.get(i).radius / 2);
            canvas.getChildren().add(planet);
            final double[] vx = {system.planet.get(i).speedX};
            final double[] vy = {system.planet.get(i).speedY};
            final double[] x = {system.planet.get(i).positionX + starCenterX};
            final double[] y = {system.planet.get(i).positionY + starCenterY};
            var timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {

                var distance = logic.distance(starCenterX, starCenterY, x[0], y[0]);
                vx[0] += logic.acceleration(system.GC, system.massOfStar, starCenterX, x[0], distance);
                vy[0] += logic.acceleration(system.GC, system.massOfStar, starCenterY, y[0], distance);
                x[0] += vx[0];
                y[0] += vy[0];
                system.planet.get(i).positionX = x[0];
                system.planet.get(i).positionY = y[0];
                system.planet.get(i).speedX = vx[0];
                system.planet.get(i).speedY = vy[0];
                planet.setCenterX(x[0]);
                planet.setCenterY(y[0]);
                canvas.requestLayout();

                if (timePort[i]) {
                    var tpXY = logic.timePortation(a.get(), x[0], y[0], vx[0], vy[0], starCenterX, starCenterY, system.GC, system.massOfStar);
                    x[0] = tpXY[0];
                    y[0] = tpXY[1];
                    vx[0] = tpXY[2];
                    vy[0] = tpXY[3];
                    timePort[i] = false;
                }

                var gradient = new RadialGradient(0,
                        .1,
                        planet.getCenterX(),
                        planet.getCenterY(),
                        planet.getRadius(),
                        false,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, (Color) Paint.valueOf(system.planet.get(i).color)),
                        new Stop(0.9, Color.BLACK));

                planet.setFill(gradient);

                Tooltip.install(planet, new Tooltip(system.planet.get(i).toShortString() + "\n"
                        + "Distance to the star " + distance));
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            var timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (p.get()) timeline.stop();
                    else timeline.play();
                    timeline.setRate(10 * (animationSpeed.getCurrentPageIndex() + 1));
                }
            }, 0, 1);
        });
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
    }
}