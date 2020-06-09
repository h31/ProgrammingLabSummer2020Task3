package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Random;


public class FieldDrawer {

    public static int[][] a = new int[4][4];

    public static int score = 0;

    public static void shift(String direction) {
        int rotates = 0;
        switch (direction) {
            case "RIGHT" : break;
            case "DOWN": a = rotate(a); rotates = 3;
                break;
            case "LEFT": a = rotate(rotate(a)); rotates = 2;
                break;
            case "UP": a = rotate(rotate(rotate(a))); rotates = 1;
                break;
        }

        boolean moved = false;

        for (int i = 0; i < 4; i++)
            for (int s = 0; s < 3; s++)
                for (int j = 0; j < 3; j++) {
                    if (a[i][j + 1] == 0 && a[i][j] != 0) {
                        a[i][j + 1] = a[i][j];
                        a[i][j] = 0;
                        moved = true;
                    }
                }
        for (int i = 3; i >= 0; i--)
            for (int j = 3; j >= 1; j--)
                if (a[i][j] == a[i][j - 1] && a[i][j] != 0) {
                    a[i][j] *= 2;
                    a[i][j - 1] = 0;
                    score += a[i][j];
                    moved = true;
                }

        for (int i = 0; i < 4; i++)
            for (int s = 0; s < 3; s++)
                for (int j = 0; j < 3; j++) {
                    if (a[i][j + 1] == 0 && a[i][j] != 0) {
                        a[i][j + 1] = a[i][j];
                        a[i][j] = 0;
                        moved = true;
                    }
                }
        for (int i = 0; i < rotates; i++) a = rotate(a);

        if (moved) spawn();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                colours(a[i][j], Main.labels[i][j]);
            }
        }
    }

    public static int[][] rotate(int [][] a) {
        int [][] b = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                b[i][j] = a[j][3 - i];
            }
        }
        return b;
    }

    public static void spawn() {
        boolean set = false;
        while (!set) {
            Random random = new Random();
            int i = random.nextInt(4);
            int j = random.nextInt(4);
            int choice = random.nextInt(10);
            if (a[i][j] == 0) {
                set = true;
                if (choice == 9) {
                    a[i][j] = 4;
                }
                else {
                    a[i][j] = 2;
                }
            }
        }
    }

    public static void colours(int c, Label l) {
        switch (c) {
            case 0: l.setText(""); l.setStyle("-fx-background-color: #bbada0; -fx-background-radius: 5;");
                break;
            case 2: coloursSetter(2, l, "#ede4db");
                break;
            case 4: coloursSetter(4, l, "#ebdec8");
                break;
            case 8: coloursSetter(8, l, "#e9b280");
                break;
            case 16: coloursSetter(16, l, "#f59563");
                break;
            case 32: coloursSetter(32, l, "#e68668");
                break;
            case 64: coloursSetter(64, l, "#e46b4c");
                break;
            case 128: coloursSetter(128, l, "#eacc79");
                break;
            case 256: coloursSetter(256, l, "#edcc61");
                break;
            case 512: coloursSetter(512, l , "#e9c55b");
                break;
            case 1024: coloursSetter(1024, l, "#e2b913");
                break;
            case 2048: coloursSetter( 2048, l, "#e8be42");
                break;
        }
    }

    public static void coloursSetter(int value, Label l, String background) {
        int fs = 48;
        l.setStyle("-fx-background-color: " + background + ";" + " -fx-background-radius: 5;");
        l.setText(String.valueOf(value));
        if (value >= 128) {
            l.setTextFill(Color.web("#faf8ef"));
            fs = 36;
        }
        else if (value > 0) {
            if (value > 4) l.setTextFill(Color.web("#faf8ef"));
            else l.setTextFill(Color.web("#84776e"));
        }
        l.setFont(Font.font("Arial Rounded MT Bold", fs));
    }

    public static boolean possible (int[][] a) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == a[i][j + 1] || a[i][j] == 0 || a[j][i] == a[j + 1][i]) return true;
            }
        }
        return false;
    }
}
