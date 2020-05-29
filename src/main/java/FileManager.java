import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void save(SystemCharacteristic system) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Select directory for save");
        fileChooser.setInitialFileName("configuration");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Configuration file .pss", "*.pss"));
        var file = fileChooser.showSaveDialog(App.stageFile);
        try (var writer = new FileWriter(file)) {
            writer.write(system.toStringFile());
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void open() {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Open config file");
        var filter = new FileChooser.ExtensionFilter("Configuration file .pss", "*.pss");
        fileChooser.getExtensionFilters().add(filter);
        var file = fileChooser.showOpenDialog(App.stageFile).getAbsoluteFile();
        var system = new SystemCharacteristic();
        var size = 0;
        try {
            var fr = new FileReader(file);
            var reader = new BufferedReader(fr);
            var line = reader.readLine();
            while (line != null) {
                if (line.matches("([0-9]+([.,][0-9]+)? ){4}")) {
                    var list = line.split(" ");
                    system.massOfStar = Double.parseDouble(list[0]);
                    system.radiusOfStar = Double.parseDouble(list[1]);
                    system.GC = Double.parseDouble(list[2]);
                    system.numberOfPlanets = Integer.parseInt(list[3]);
                }
                else if (line.matches("'\\S+' 0x([a-f0-9]){8} (-?[0-9]+([.,][0-9]+)? ){5}")) {
                    var list = line.split(" ");
                    var planet = new PlanetCharacteristic();
                    planet.setName(list[0].substring(1, list[0].length() - 1));
                    planet.color = list[1];
                    if (list[2].contains("-"))
                        error();
                    planet.setRadius(list[2]);
                    planet.setPositionX(list[3]);
                    planet.setPositionY(list[4]);
                    planet.speedX = Double.parseDouble(list[5]);
                    planet.speedX = Double.parseDouble(list[6]);
                    system.planet.add(planet);
                    size++;
                }
                else
                    error();
                line = reader.readLine();
            }
            if (size != system.numberOfPlanets)
                error();
            var app = new App();
            app.space(system);
        } catch (IOException e) {
            error();
        }
    }

    public static void error() {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("File invalid");
        alert.setContentText("This file cannot be used by the program.");
        alert.showAndWait();
    }

}
