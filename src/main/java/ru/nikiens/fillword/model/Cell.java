package ru.nikiens.fillword.model;

import javafx.scene.control.Label;

public class Cell extends Label {
    private CellState state;
    private int x;
    private int y;

    public Cell(int x, int y) {
        super();

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
