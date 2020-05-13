package view;
import core.Board;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BattleShipApp extends Application {
    private static Board enemy, playerBoard;
     Parent justDoIt() {
        BorderPane root = new BorderPane();
        enemy = new Board(false);
        playerBoard = new Board(true);
        root.setPrefSize(800, 700);
        HBox boards = new HBox();
         boards.setSpacing(50);
         boards.getChildren().addAll(playerBoard,enemy);
         boards.setAlignment(Pos.CENTER);
        root.setCenter(boards);
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("resources/ship.jpg"));
        primaryStage.setTitle("Морской бой");
        primaryStage.setScene(new Scene(justDoIt()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}


