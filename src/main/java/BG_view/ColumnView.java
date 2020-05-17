package BG_view;

import BG_model.ChipColor;
import BG_model.Column;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class ColumnView extends VBox {
    Column column;
    boolean bottomLine;
    boolean isBlot;
    ChipColor color;

    ColumnView(Column column, boolean bottomLine,boolean isBlot) {
        this.column = column;
        this.bottomLine = bottomLine;
        int size = column.size();
        if (size != 0) color = column.onTop();
        if (bottomLine) {
            if (size > 4) {
                this.getChildren().add(columnOverFlow(size - 5));
                for (int i = 0; i < 5; i++) {
                    this.getChildren().add(chipCanvas(color));
                }
            } else {
                this.setPadding(new Insets(65+(5-size)*55,0,0,0));
                for (int i = 0; i < size; i++) {
                    this.getChildren().add(chipCanvas(color));
                }
            }
        } else if (size > 4){
            for (int i = 0; i < 5; i++) {
                this.getChildren().add(chipCanvas(color));
            } this.getChildren().add(columnOverFlow(size - 5));
        } else {
            for (int i = 0; i < size; i++) {
                this.getChildren().add(chipCanvas(color));
            }
        }
        final boolean[] firstClick = {true};
        this.setOnMouseClicked(mouseEvent -> {
            if(firstClick[0]) {
                firstClick[0] = false;
                this.setStyle("-fx-background-color: #00ffff");
            } else {
                firstClick[0] = true;
                if (isBlot){
                    this.setStyle("-fx-background-color: #bdbdbd");
                } else this.setStyle("-fx-background-color: #b87333");
            }
        });

    }

    static private Canvas chipCanvas(ChipColor color) {
        Canvas canvas = new Canvas(55, 55);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image chip;
        switch (color) {
            case WHITE:
                chip = new Image("white.png");
                break;
            case BLACK:
                chip = new Image("black.png");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + color);
        }
        gc.drawImage(chip, 0, 0);
        return canvas;
    }

    static private Canvas columnOverFlow(int i) {
        Canvas canvas = new Canvas(55, 55);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 50);
        gc.setFont(theFont);
        gc.fillText("+" + i, 0, 50);
        gc.strokeText("+" + i, 0, 50);
        return canvas;
    }
}
