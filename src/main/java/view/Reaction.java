package view;

import controller.BattleShipApp;
import core.Board;
import core.Ship;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class Reaction {
    private BattleShipApp controller;
    private File file = new File("C:/Users/Admin/ProgrammingLabSummer2020Task3/src/main/resources/message.properties");
    public Properties properties = new Properties();

    public Reaction(BattleShipApp battleShipApp) throws IOException {
        controller = battleShipApp;
        properties.load(new FileReader(file));
    }

    private void concludingPhrase(boolean isEnemyMove) throws IOException {
        Text text;
        if (isEnemyMove) text = new Text(properties.getProperty("youLose"));
        else text = new Text(properties.getProperty("youWin"));
        text.setFont(Font.font(50));
        text.setFill(Color.RED);
        controller.message.getChildren().add(text);
        showDialog();

    }

    public void showState(String string) {
        Text text = new Text(string);
        text.setFont(Font.font("Verdana", 30));
        text.setFill(Color.DARKGREEN);
        controller.message.getChildren().add(text);
    }

    public void setColorMissed(Cell cell) {
        cell.setFill(Color.VIOLET);
    }

    public void setColorInjured(Cell cell) {
        cell.setFill(Color.RED);
    }

    public void setShipColor(Cell cell) {
        cell.setFill(Color.DARKBLUE);
    }

    public void killingShip(Ship ship, Board board, Board_view board_view) throws IOException {
        board.counter_ships--;
        for (Board.Cell e : ship.cellsAroundShip()) {
            Cell element = board_view.getCell(e.x, e.y);
            controller.reaction.setColorMissed(element);
        }
        if (board.counter_ships == 0) controller.reaction.concludingPhrase(controller.isEnemyMove);
        else controller.hitTheShip();
    }

    private void showDialog() throws IOException {
        Alert newGame = new Alert(Alert.AlertType.CONFIRMATION);
        newGame.setTitle(properties.getProperty("restartGame"));
        newGame.setHeaderText(properties.getProperty("newGame"));
        Optional<ButtonType> option = newGame.showAndWait();

        if (option.get() == ButtonType.OK) {
            BattleShipView.primaryStage.close();
            BattleShipView restart = new BattleShipView();
            Stage primaryStage = new Stage();
            restart.start(primaryStage);
            newGame.close();
        } else if (option.get() == ButtonType.CANCEL) {
            newGame.close();
            BattleShipView.primaryStage.close();
        }
    }

}
