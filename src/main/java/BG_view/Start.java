package BG_view;

import BG_control.Turn;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static BG_view.Board.CHIP_SIZE;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) {

        theStage.setTitle("BackGammon");

        Group root = new Group();
        Scene theScene = new Scene(root, CHIP_SIZE*14,CHIP_SIZE*12);
        theStage.setScene(theScene);
        theStage.setResizable(false);
        theStage.show();
        Turn t = new Turn();
        BG_view.Board board = new BG_view.Board(t);
        GridPane grid = board.getGrid();
        grid.setGridLinesVisible(true);

        root.getChildren().add(grid);

        t.startTurn(board);


    }

}