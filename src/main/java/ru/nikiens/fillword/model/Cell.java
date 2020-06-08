package ru.nikiens.fillword.model;

import java.util.Objects;

public class Cell {
    private CellState state;
    private char letter;

    public Cell() {
        this.state = CellState.UNMARKED;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return getLetter() == cell.getLetter() &&
                getState() == cell.getState();
    }

    public int hashCode() {
        return Objects.hash(getState(), getLetter());
    }
}
