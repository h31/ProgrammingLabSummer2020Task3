package project;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.Optional;

public class Controller {
    final private Model model = new Model();

    final private static Canvas[][] canvases = new Canvas[5][5];
    final private static int cellWidth = 90;
    final private static int cellHeight = 90;

    final Text firstPlayerText = new Text(0, 0, "1st player:");
    final Text secondPlayerText = new Text(0, 0, "2nd player:");
    final Text firstScoreText = new Text(0, 0, "");
    final Text secondScoreText = new Text(0, 0, "");

    final Button restart = new Button("Restart");
    final Button skip = new Button("Skip");
    final Button confirm = new Button("Confirm");

    final GridPane gridField = new GridPane();

    final Text whoseMove = new Text(0, 0, "");

    public void fillCanvas() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                canvases[i][j] = new Canvas(cellWidth, cellHeight);
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

        whoseMove.setText("1st player");

        firstScoreText.setText("0");
        secondScoreText.setText("0");
    }

    public static void redrawCell(int i, int j, char newLetter) {
        GraphicsContext gc = canvases[i][j].getGraphicsContext2D();

        /*if (newLetter == '^') {
            gc.clearRect(0, 0, cellWidth, cellHeight);
        }*/

        gc.clearRect(0, 0, cellWidth, cellHeight);

        gc.setFont(Font.font(null, 40));
        gc.fillText(String.valueOf(newLetter).toUpperCase(), 30, 55);
    }

    public void chooseCell() {
        gridField.getChildren().forEach(grid ->
                grid.setOnMouseReleased((e) -> {
                    if (e.getButton().equals(MouseButton.SECONDARY)) {
                        Node cell = (javafx.scene.Node) e.getSource();

                        int i = GridPane.getRowIndex(cell);
                        int j = GridPane.getColumnIndex(cell);

                        boolean isCellCorrect = false;
                        for (Pair<Integer, Integer> coordinate : model.getPossibleMoves()) {
                            if (coordinate.getKey() == i && coordinate.getValue() == j) {
                                isCellCorrect = true;
                                break;
                            }
                        }

                        if (isCellCorrect) {
                            redrawCell(i, j, '?');
                        }

                    }
                }));
    }

    public void confirmWord() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Confirm the word");
        dialog.setHeaderText("Enter the letter you want to add");

        Optional<String> result = dialog.showAndWait();
    }

}
