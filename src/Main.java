import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 445, 510);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Supper");
        scene.setFill(Color.LIGHTGRAY);
        VBox vBox = new VBox();
        Pane gameArea = new Pane();
        MenuBar menuBar = new MenuBar();
        Menu game = new Menu("Меню");
        MenuItem setSize = new MenuItem("Задать размер поля");
        menuBar.getMenus().add(game);
        menuBar.setMinSize(445, 30);
        game.getItems().add(setSize);
        vBox.getChildren().addAll(menuBar, gameArea);
        root.getChildren().add(vBox);
        Scanning scanning = new Scanning(10, 40, 58);
        game.setOnAction(event -> {
            List<Integer> params = getFieldSize();
            if (params == null) return;
            scanning.setGameParams(params.get(0), params.get(1), params.get(2));
            scanning.restart();
            primaryStage.setWidth(10 + params.get(0) * 45);
            primaryStage.setHeight(68 + 17 * params.get(1));
            menuBar.setMinWidth(70 + params.get(0) * 45);
        });
        Renovation.play = scanning;
        Renovation.zone = gameArea;
        Renovation.init(10, 40);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private List<Integer> getFieldSize() {
        Dialog<List<Integer>> dialog = new Dialog<>();
        dialog.setTitle("Задать размер поля");
        dialog.setResizable(false);

        Label width = new Label("Ширина:");
        Label height = new Label("Высота:");
        Label mines = new Label("Количество мин:");

        TextField widthTxt = new TextField();
        TextField heightTxt = new TextField();
        TextField minesTxt = new TextField();

        GridPane grid = new GridPane();
        grid.add(width, 1, 1);
        grid.add(widthTxt, 2, 1);
        grid.add(height, 1, 2);
        grid.add(heightTxt, 2, 2);
        grid.add(mines, 1, 3);
        grid.add(minesTxt, 2, 3);

        dialog.getDialogPane().setContent(grid);
        ButtonType buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        dialog.setResultConverter(new Callback<ButtonType, List<Integer>>() {                           //?????????
            @Override
            public List<Integer> call(ButtonType param) {
                if (param == buttonOk) {
                    List<Integer> result = new ArrayList<>();
                    if (widthTxt.getText() == null || heightTxt.getText() == null || minesTxt.getText() == null)
                        return null;
                    result.add(Integer.valueOf(widthTxt.getText()));
                    result.add(Integer.valueOf(heightTxt.getText()));
                    result.add(Integer.valueOf(minesTxt.getText()));
                    return result;
                }
                return null;
            }
        });
        Optional result = dialog.showAndWait();
        if (result.isPresent()) return (List<Integer>) result.get();
        else return null;
    }
}