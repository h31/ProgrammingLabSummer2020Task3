package project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {
    final private Model model = new Model();

    final private static Canvas[][] canvases = new Canvas[5][5];

    final Text firstPlayerText = new Text(0, 0, "1st player:");
    final Text secondPlayerText = new Text(0, 0, "2nd player:");
    final Text firstScoreText = new Text(0, 0, "");
    final Text secondScoreText = new Text(0, 0, "");

    final Button restart = new Button("Restart");
    final Button skip = new Button("Skip");

    final GridPane gridField = new GridPane();

    final Text whoseMove = new Text(0, 0, "");

    public void fillCanvas() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                canvases[i][j] = new Canvas(90, 90);
                gridField.add(canvases[i][j], j, i);
            }
        }
    }

    public void start() {
        model.fillField();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++)
                redrawCell(i, j, model.getCharFromField(i, j));
        }

        //добавить начальное заполнение клеток, в которые можно сходить

        whoseMove.setText("1st player");

        firstScoreText.setText("0");
        secondScoreText.setText("0");
    }

    public static void redrawCell(int i, int j, char newLetter) {
        GraphicsContext gc = canvases[i][j].getGraphicsContext2D();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.strokeRect(0, 0, 90, 90);

        gc.clearRect(0, 0, 90, 90);
        gc.setGlobalAlpha(1.0);

        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(newLetter).toUpperCase(), 40, 50);

    }

}
