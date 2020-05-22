package view;

import controller.BoardController;
import core.Board;
import core.ComputerShips;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public  class BattleShipView extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 700);
        primaryStage.getIcons().add(new Image("resources/ship.jpg"));
        primaryStage.setTitle("Морской бой");
        Board enemy = new Board(false);
        Board playerBoard = new Board(true);
        HBox boards = new HBox(50, playerBoard, enemy);
        boards.setAlignment(Pos.CENTER);
        root.setCenter(boards);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        ComputerShips computerShips = new ComputerShips(enemy);
        computerShips.placeComputersShips();
        BoardController start = new BoardController(enemy, playerBoard, scene, computerShips);
        start.placePlayerShips();
        start.playerGame();
        }

    public static void main(String[] args) {
        launch(args);

    }

}