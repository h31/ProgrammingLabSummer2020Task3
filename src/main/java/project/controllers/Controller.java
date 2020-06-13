package project.controllers;

import project.models.Field;
import project.models.Tile;
import project.views.View;

public class Controller {
    private Field field;
    private View view;

    public Controller(Field field, View view) {
        this.field = field;
        this.view = view;
    }

    public void playNewGame() {

    }

    public void mouseClickLeft(Tile tile) {
        field.openTile(tile);
        view.redraw();
    }

    public void mouseClickRight(Tile tile) {
        field.markTile(tile);
        view.redraw();
    }
}
