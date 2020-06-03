import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        //поле
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sapper");
        stage.setWidth(460);
        stage.setHeight(520);
        scene.setFill(Color.LIGHTGRAY);

        VBox vBox = new VBox();
        Pane gameArea = new Pane();
        vBox.getChildren().add( gameArea);
        root.getChildren().add(vBox);

        Scanning.play = new Scanning();
        Scanning.zone = gameArea;
        Scanning.init();
        stage.setResizable(false);
        stage.show();
    }
}