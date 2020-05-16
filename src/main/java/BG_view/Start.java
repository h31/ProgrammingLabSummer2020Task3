package BG_view;

import BG_control.Turn;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) {

        theStage.setTitle("BackGammon");

        Group root = new Group();
        Scene theScene = new Scene(root, 815,720);
        theStage.setScene(theScene);

        BG_view.Board board = new BG_view.Board();
        Turn t = new Turn();
        GridPane grid = board.board(t);
        //
        grid.setGridLinesVisible(true);
        //

        root.getChildren().add(grid);
        theStage.show();


    }
}
