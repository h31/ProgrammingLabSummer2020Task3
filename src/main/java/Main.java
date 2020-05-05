import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
   static Parent fieldTexture;
   private static Parent winTexture;
   private static int record;

    static {
        try {
            record = Field.reader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fieldTexture = FXMLLoader.load(Main.class.getResource("/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            winTexture = FXMLLoader.load(Main.class.getResource("/win.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("2048");
        InputStream iconStream = getClass().getResourceAsStream("/texture/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        Group root = new Group();
        root.getChildren().add(fieldTexture);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                   {
                    if (Field.winCheck(Field.field)) {
                        root.getChildren().add(winTexture);
                    } else {
                        if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                            Field.down();
                            Group group = Painter.draw(Field.field);
                            root.getChildren().add(group);
                        }
                        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                            Field.up();
                            Group group = Painter.draw(Field.field);
                            root.getChildren().add(group);
                        }
                        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                            Field.right();
                            Group group = Painter.draw(Field.field);
                            root.getChildren().add(group);
                        }
                        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                            Field.left();
                            Group group = Painter.draw(Field.field);
                            root.getChildren().add(group);
                        }
                        if (Field.score > record){
                            record = Field.score;
                            try {
                                Field.writer(record);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Label rec = new Label(String.valueOf(record));
                        rec.minWidth(50);
                        rec.minHeight(20);
                        rec.setLayoutX(173);
                        rec.setLayoutY(3);
                        Label score = new Label(String.valueOf(Field.score));
                        score.minWidth(50);
                        score.minHeight(20);
                        score.setLayoutX(50);
                        score.setLayoutY(3);
                        root.getChildren().addAll(score, rec);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
