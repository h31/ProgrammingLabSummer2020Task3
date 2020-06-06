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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Main extends Application {

    public static Label[][] labels = new Label[4][4];
    public static Label score = new Label();
    public static Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage = stage;
        Pane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        root.getChildren().add(pane);
        Scene scene = new Scene(root);
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();
        FieldDrawer.spawn();
        FieldDrawer.spawn();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Label l = new Label();
                labelSetter(l, 30 + 100 * j, 120 + 100 * i, 90, 90, "#bbada0",
                        48, "#bbada0", "");
                l.setAlignment(Pos.CENTER);
                labels[i][j] = l;
                FieldDrawer.colours(FieldDrawer.a[i][j], labels[i][j]);
                root.getChildren().add(labels[i][j]);
            }
        }

        labelSetter(score, 300, 45, 130, 30, "#84776e", 16, "#faf8ef", " Score: ");
        score.setText(" Score: " + FieldDrawer.score);
        root.getChildren().add(score);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Controller controller = new Controller();
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                    FieldDrawer.shift("UP");
               }
                if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                    FieldDrawer.shift("LEFT");
                }
                if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                    FieldDrawer.shift("DOWN");
                }
                if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                    FieldDrawer.shift("RIGHT");
                }
                score.setText(" Score: " + FieldDrawer.score);
                try {
                    controller.win();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    controller.lose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void labelSetter
            (Label l, int x, int y, int w, int h, String background, int fs, String tf, String text) {
        l.setLayoutX(x);
        l.setLayoutY(y);
        l.setPrefSize(w, h);
        l.setStyle("-fx-background-color: " + background + ";" + " -fx-background-radius: 5;");
        l.setFont(Font.font("Arial Rounded MT Bold", fs));
        l.setTextFill(Color.web(tf));
        l.setText(text);
    }
}
