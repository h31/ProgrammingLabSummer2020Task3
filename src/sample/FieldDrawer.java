package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;


public class FieldDrawer {

    /*public static void startField(Pane root, Label[][] labels) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                labels[i][j].setLayoutX(30 + 100 * j);
                labels[i][j].setLayoutY(120 + 100 * i);
                labels[i][j].setPrefSize(90, 90);
                labels[i][j].setStyle("-fx-background-color: #bbada0");
                labels[i][j].setFont(Font.font("Arial Black", 36));
                root.getChildren().add(labels[i][j]);
            }
    }*/


    public static int[][] a =
            {{0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};

    public static void left() {

        int[][] b = a;

        for (int i = 3; i >= 0; i--)
            for (int s = 0; s < 3; s++)
                for (int j = 3; j >= 1; j--)
                    if (a[i][j - 1] == 0 && a[i][j] != 0) {
                        a[i][j - 1] = a[i][j];
                        a[i][j] = 0;
                    }
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == a[i][j + 1] && a[i][j] != 0) {
                    a[i][j] *= 2;
                    a[i][j + 1] = 0;
                }
            }

        for (int i = 3; i >= 0; i--)
            for (int s = 0; s < 3; s++)
                for (int j = 3; j >= 1; j--)
                    if (a[i][j - 1] == 0 && a[i][j] != 0) {
                        a[i][j - 1] = a[i][j];
                        a[i][j] = 0;
                    }

        if (b == a) spawn();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
             colours(a[i][j], Main.labels[i][j]);
            }
    }

    public static void up() {

        int[][] b = a;

        for (int j = 3; j >= 0; j--)
            for (int s = 0; s < 3; s++)
                for (int i = 3; i >= 1; i--)
                    if (a[i - 1][j] == 0 && a[i][j] != 0) {
                        a[i - 1][j] = a[i][j];
                        a[i][j] = 0;
                    }

        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 3; i++)
                if (a[i][j] == a[i + 1][j] && a[i][j] != 0) {
                    a[i][j] *= 2;
                    a[i + 1][j] = 0;
                }

        for (int j = 3; j >= 0; j--)
            for (int s = 0; s < 3; s++)
                for (int i = 3; i >= 1; i--)
                    if (a[i - 1][j] == 0 && a[i][j] != 0) {
                        a[i - 1][j] = a[i][j];
                        a[i][j] = 0;
                    }

        if (b == a) spawn();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                colours(a[i][j], Main.labels[i][j]);
            }

    }

    public static void right() {

        int[][] b = a;

        for (int i = 0; i < 4; i++)
            for (int s = 0; s < 3; s++)
                for (int j = 0; j < 3; j++) {
                    if (a[i][j + 1] == 0 && a[i][j] != 0) {
                        a[i][j + 1] = a[i][j];
                        a[i][j] = 0;
                    }
                }
        for (int i = 3; i >= 0; i--)
            for (int j = 3; j >= 1; j--)
                if (a[i][j] == a[i][j - 1] && a[i][j] != 0) {
                    a[i][j] *= 2;
                    a[i][j - 1] = 0;
                }

        for (int i = 0; i < 4; i++)
            for (int s = 0; s < 3; s++)
                for (int j = 0; j < 3; j++) {
                    if (a[i][j + 1] == 0 && a[i][j] != 0) {
                        a[i][j + 1] = a[i][j];
                        a[i][j] = 0;
                    }
                }

        if (b == a) spawn();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                colours(a[i][j], Main.labels[i][j]);
            }

    }

    public static void down() {

        int[][] b = a;

        for (int j = 0; j < 4; j++)
            for (int s = 0; s < 3; s++)
                for (int i = 0; i < 3; i++)
                    if (a[i + 1][j] == 0 && a[i][j] != 0) {
                        a[i + 1][j] = a[i][j];
                        a[i][j] = 0;
                    }
        for (int j = 3; j >= 0; j--)
            for (int i = 3; i >= 1; i--)
                if (a[i][j] == a[i - 1][j] && a[i][j] != 0) {
                    a[i][j] *= 2;
                    a[i - 1][j] = 0;
                }

        for (int j = 0; j < 4; j++)
            for (int s = 0; s < 3; s++)
                for (int i = 0; i < 3; i++)
                    if (a[i + 1][j] == 0 && a[i][j] != 0) {
                        a[i + 1][j] = a[i][j];
                        a[i][j] = 0;
                    }

         if (b == a) spawn();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                colours(a[i][j], Main.labels[i][j]);
            }

    }


    public static void colours(int c, Label l) {
        switch (c) {
            case 0:
                l.setText("");
                l.setTextFill(Color.web("#000000"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #bbada0");
                break;
            case 2:
                l.setText("2");
                l.setTextFill(Color.web("#84776e"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #ede4db");
                break;
            case 4:
                l.setText("4");
                l.setTextFill(Color.web("#84776e"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #ebdec8");
                break;
            case 8:
                l.setText("8");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #e9b280");
                break;
            case 16:
                l.setText("16");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #f59563");
                break;
            case 32:
                l.setText("32");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #e68668");
                break;
            case 64:
                l.setText("64");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 48));
                l.setStyle("-fx-background-color: #e46b4c");
                break;
            case 128:
                l.setText("128");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 36));
                l.setStyle("-fx-background-color: #eacc79");
                break;
            case 256:
                l.setText("256");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 36));
                l.setStyle("-fx-background-color: #edcc61");
                break;
            case 512:
                l.setText("512");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 36));
                l.setStyle("-fx-background-color: #e9c55b");
                break;
            case 1024:
                l.setText("1024");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 36));
                l.setStyle("-fx-background-color: #e2b913");
                break;
            case 2048:
                l.setText("2048");
                l.setTextFill(Color.web("#faf8ef"));
                l.setFont(Font.font("Arial Rounded MT Bold", 36));
                l.setStyle("-fx-background-color: #e8be42");
                break;
        }
    }
    public static void lose() {
        boolean l = true;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (a[i][j] == 0) {
                    l = false;
                }
        if (l == true) {

        }
    }
    public static void win() {
        boolean w = false;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (a[i][j] == 2048) {
                    w = true;
                }
    }
    public static void spawn() {
        boolean set = false;
        while (set == false) {
            Random random = new Random();
            int i = random.nextInt(4);
            int j = random.nextInt(4);
            if (a[i][j] == 0) {
                set = true;
                a[i][j] = 2;
            }
        }
    }
}
