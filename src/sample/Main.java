package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Main extends Application {
    /*public static int[][] a = {{2, 4, 8, 8},
                               {2, 2, 2, 2},
                               {0, 2, 4, 8},
                               {0, 0, 0, 2}};*/
    public static Label[][] labels = new Label[4][4];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        root.getChildren().add(pane);
        Scene scene = new Scene(root);
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();
        FieldDrawer.spawn();
        FieldDrawer.spawn();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                Label l = new Label();
                l.setLayoutX(30 + 100 * j);
                l.setLayoutY(120 + 100 * i);
                l.setPrefSize(90, 90);
                l.setStyle("-fx-background-color: #bbada0");
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setAlignment(Pos.CENTER);
                labels[i][j] = l;
                FieldDrawer.colours(FieldDrawer.a[i][j], labels[i][j]);
                root.getChildren().add(labels[i][j]);
            }
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    /*Label la = new Label();
                    la.setStyle("-fx-background-color: #000000");
                    la.setFont(Font.font("Arial Black", 36));
                    la.setTextFill(Color.WHITE);
                    la.setAlignment(Pos.CENTER);
                    la.setLayoutX(30 + 100);
                    la.setLayoutY(120 + 100);
                    la.setPrefSize(90, 90);
                    la.setText("4");
                    root.getChildren().add(la);*/
                    FieldDrawer.up();
               }
                if (event.getCode() == KeyCode.A) {
                    FieldDrawer.left();
                }
                if (event.getCode() == KeyCode.S) {
                    FieldDrawer.down();
                }
                if (event.getCode() == KeyCode.D) {
                    FieldDrawer.right();
                }
            }
        });
    }
}
