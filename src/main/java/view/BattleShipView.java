package view;

import controller.BoardController;
import core.Board;
import core.ComputerShips;
import core.Shoot;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.Optional;

public class BattleShipView extends Application {
    public Board playerBoard = new Board();
    public Board enemyBoard = new Board();
    public TextFlow message = new TextFlow();
    public ComputerShips computerShips = new ComputerShips(this);
    public BoardController controller = new BoardController(this);
    public Reaction reaction = new Reaction(this);
    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 700);
        primaryStage.setTitle("Морской бой");
        Text battleShip = new Text("Морской бой");
        battleShip.setFont(Font.font(40));
        battleShip.setFill(Color.BLUE);
        TextFlow title = new TextFlow(battleShip);
        title.setTextAlignment(TextAlignment.CENTER);
        root.setTop(title);
        Text nameOfBoard1 = new Text("Поле игрока");
        nameOfBoard1.setFont(Font.font(30));
        Text nameOfBoard2 = new Text("Поле врага");
        nameOfBoard2.setFont(Font.font(30));
        HBox boards = new HBox(50, playerBoard, enemyBoard);
        boards.setAlignment(Pos.CENTER);
        HBox text = new HBox(200, nameOfBoard1, nameOfBoard2);
        text.setAlignment(Pos.CENTER);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setLineSpacing(20);
        VBox game = new VBox(20, boards, text, message);
        root.setCenter(game);
        Scene scene = new Scene(root);
        computerShips.placeComputerShips();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showDialog() {
        Alert newGame = new Alert(Alert.AlertType.CONFIRMATION);
        newGame.setTitle("Начать новую игру?");
        newGame.setHeaderText("Хотите начать новую игру?");
        Optional<ButtonType> option = newGame.showAndWait();
        if (option.get() == ButtonType.OK) {
            BattleShipView restart = new BattleShipView();
            Stage primaryStage = new Stage();
            restart.start(primaryStage);
            newGame.close();
            this.primaryStage.close();
        } else if (option.get() == ButtonType.CANCEL) {
            newGame.close();
            this.primaryStage.close();
        }
    }

}