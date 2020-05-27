package BG_control;

import BG_model.ChipColor;
import BG_view.Board;
import BG_view.Start;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static BG_view.Board.CHIP_SIZE;

public class Turn {
    private int turnCount = 0;
    private List<Integer> moveList = new ArrayList<>();
    private List<Boolean> notEmptyBar = Arrays.asList(false, false);


    void setNotEmptyBar(boolean notEmptyBar, int i) {
        this.notEmptyBar.set(i, notEmptyBar);
    }

    public void startTurn(Board board) {
        turnCount++;
        moveList(diceRoll());
        if (notEmptyBar.get(playerNumber())) {
            Move.setBarMove(playerNumber() == 0 ? ChipColor.WHITE : ChipColor.BLACK, this, board);
        } else if (board.getHomes().get(playerNumber()) == 15) {
            Move.setEndspielMove(playerNumber() == 0 ? ChipColor.WHITE : ChipColor.BLACK, this, board);
        } else Move.setNormalMove(this, board);

        newTurnAlert();
        board.getGrid().add(diceView(), 13, 0);
    }

    private void newTurnAlert() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Turn:" + (turnCount + 1) / 2);
        alert.setHeaderText("Player " + (playerNumber() + 1) + " turn");
        alert.setContentText(null);
        alert.showAndWait();


    }

    void winnerAlert(Board board) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText("Player " + (playerNumber() + 1) + " wins");
        alert.setContentText("Start new game ?");

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            Stage stage = (Stage) board.getGrid().getScene().getWindow();
            stage.close();
            Start s = new Start();
            s.start(new Stage());
        } else {
            Stage stage = (Stage) board.getGrid().getScene().getWindow();
            stage.close();
        }
    }

    private int[] diceRoll() {
        int d1 = (int) (Math.random() * 6) + 1;
        int d2 = (int) (Math.random() * 6) + 1;
        if (d1 == d2) return new int[]{d1, d1, d1, d1};
        else return new int[]{d1, d2};

    }

    private void moveList(int[] dices) {
        moveList = new ArrayList<>();
        if (dices.length == 2) {
            moveList.add(dices[0]);
            moveList.add(dices[1]);
            moveList.add(dices[0] + dices[1]);

        } else {
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0] * 2);
            moveList.add(dices[0]);
            moveList.add(dices[0]);
            moveList.add(dices[0] * 2);
            moveList.add(dices[0] * 3);
            moveList.add(dices[0] * 4);
        }
    }

    private Canvas dicePrint(int i) {
        Canvas canvas = new Canvas(CHIP_SIZE, CHIP_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, CHIP_SIZE * 0.9);
        gc.setFont(theFont);

        gc.fillRect(2, 2, CHIP_SIZE * 0.9, CHIP_SIZE * 0.9);
        gc.strokeRect(0, 0, CHIP_SIZE, CHIP_SIZE);

        gc.strokeText(String.valueOf(i), 10, 45);

        return canvas;
    }

    private VBox diceView() {
        VBox v = new VBox();
        v.setSpacing(0);
        v.setPadding(new Insets(CHIP_SIZE * 3, 0, 0, 0));
        v.getChildren().addAll(Board.chipCanvas(ChipColor.values()[playerNumber()]), dicePrint(moveList.get(0)), dicePrint(moveList.get(1)));
        return v;
    }


    List<Integer> getMoveList() {
        return moveList;
    }

    public int playerNumber() {
        return ((turnCount - 1) % 2);
    }

    public void setTurnCount(int i) {
        turnCount = i;
    }

}
