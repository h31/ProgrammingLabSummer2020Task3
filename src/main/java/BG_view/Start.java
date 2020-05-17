package BG_view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Start extends Application {
    public static final int TILE_WIDTH =55;
    public static void main(String[] args) {
        launch(args);
    }

    private Group columnGroup = new Group();
    private Group chipGroup = new Group();
    public void start(Stage theStage) {

        theStage.setTitle("BackGammon");

        Group root = new Group();
        for (int y =0; y < 2; y++){
            for (int x =0; x <13; x++){
                ColumnView columnView = new ColumnView(x,y );
                columnGroup.getChildren().add(columnView);
            }
        }
        root.getChildren().addAll(columnGroup,chipGroup);
        Scene theScene = new Scene(root, TILE_WIDTH*14,TILE_WIDTH*12);
        theStage.setScene(theScene);



        theStage.show();


    }
}
