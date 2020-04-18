package Project;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Menu {

    public static Scene playNewGame() {
        BorderPane menu = new BorderPane();
        Label topLabel = new Label("          Welcome to Snake!\nTutorial:\nUse W, A, S, D to move\n" +
                "Press ESC to pause and unpause\nPress Enter to restart!");
        topLabel.setFont(Font.font(20));
        Button btn = new Button();
        btn.setText("Start the game");
        btn.setOnAction(event -> {
            Controller a = new Controller();
            a.startNewGame();
        });
        menu.setMinSize(300, 300);
        menu.setMaxSize(300, 300);
        menu.setTop(topLabel);
        menu.setCenter(btn);
        return new Scene(menu);
    }
}
