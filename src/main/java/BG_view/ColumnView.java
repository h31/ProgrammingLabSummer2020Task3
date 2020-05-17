package BG_view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static BG_view.Start.TILE_WIDTH;

class ColumnView extends Rectangle {
    public ColumnView(int x, int y){
        setWidth(TILE_WIDTH);
        setHeight(6 * TILE_WIDTH);
        relocate(x*TILE_WIDTH, y * 6 * TILE_WIDTH);

        setFill(Color.valueOf("#b87333"));
        setStroke(Color.valueOf("#000000"));
}}
