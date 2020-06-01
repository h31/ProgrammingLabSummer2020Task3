package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;


public class Main {
    public static int[][] a = {{2, 4, 8, 8},
                               {2, 2, 2, 2},
                               {0, 2, 4, 8},
                               {0, 0, 0, 2}};

    /*public static Stage primaryStage = new Stage();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage = this.primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public void setter (Label l, float x, float y) {
        l.setLayoutX(x);
        l.setLayoutY(y);
        l.setText("4");
        l.setFont(Font.font("Arial Black", 24));
    }*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("a")) left();
        if (str.equals("w")) up();
        if (str.equals("d")) right();
        if (str.equals("s")) down();


        for (int i = 0; i < 4; i++) {
            System.out.println();
            for (int j = 0; j < 4; j++) {
                System.out.print(a[i][j] + " ");
            }
        }
    }

    public static void left() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == a[i][j + 1]) {
                    a[i][j] *= 2;
                    a[i][j + 1] = 0;
                }
            }
        for (int i = 3; i >= 0; i--) {
            for (int s = 0; s < 3; s++)
                for (int j = 3; j >= 1; j--) {
                    if (a[i][j - 1] == 0) {
                        a[i][j - 1] = a[i][j];
                        a[i][j] = 0;
                    }
                }
        }

    }

    public static void up() {
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 3; i++) {
                if (a[i][j] == a[i + 1][j]) {
                    a[i][j] *= 2;
                    a[i + 1][j] = 0;
                }
            }
        for (int j = 3; j >= 0; j--)
            for (int s = 0; s < 3; s++)
                for (int i = 3; i >= 1; i--) {
                    if (a[i - 1][j] == 0) {
                        a[i - 1][j] = a[i][j];
                        a[i][j] = 0;
                    }
                }
    }

    public static void right() {
        for (int i = 3; i >= 0; i--)
            for (int j = 3; j >= 1; j--) {
                if (a[i][j] == a[i][j - 1]) {
                    a[i][j] *= 2;
                    a[i][j - 1] = 0;
                }
            }
        for (int i = 0; i < 4; i++)
            for (int s = 0; s < 3; s++)
                for (int j = 0; j < 3; j++) {
                    if (a[i][j + 1] == 0) {
                        a[i][j + 1] = a[i][j];
                        a[i][j] = 0;
                    }
                }
    }

    public static void down() {
        for (int j = 3; j >= 0; j--)
            for (int i = 3; i >= 1; i--) {
                if (a[i][j] == a[i - 1][j]) {
                    a[i][j] *= 2;
                    a[i - 1][j] = 0;
                }
            }
        for (int j = 0; j < 4; j++)
            for (int s = 0; s < 3; s++)
                for (int i = 0; i < 3; i++) {
                    if (a[i + 1][j] == 0) {
                        a[i + 1][j] = a[i][j];
                        a[i][j] = 0;
                    }
                }
    }
}
