package ru.nikiens.fillword.model;

public class Cell {
    private String letter;
    private CellState state;
    private int x;
    private int y;

    public Cell(int x, int y, String letter) {
        this.state = CellState.UNMARKED;
        this.x = x;
        this.y = y;
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String  letter) {
        this.letter = letter;
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

    public String toString() {
        return super.toString();
    }
}
