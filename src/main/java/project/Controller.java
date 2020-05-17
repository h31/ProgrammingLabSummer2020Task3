package project;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {
    final private Model model = new Model();

    final private Canvas[][] canvases = new Canvas[5][5];

    final Text firstScoreText = new Text(0, 0, "");
    final Text secondScoreText = new Text(0, 0, "");

    final Button restart = new Button("R");
    final Button skip = new Button("Skip");

    final GridPane gridPane = new GridPane();

    final Text whoseMove = new Text("");

    public void fillCanvas() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                canvases[i][j] = new Canvas(90, 90);
                gridPane.add(canvases[i][j], j, i);
            }
        }
    }
}
