package ru.nikiens.fillword.model;

import java.util.Objects;

public class Cell {
    public enum State {
        MARKED,
        UNMARKED
    }

    private State state;
    private char letter;

    public Cell() {
        this.state = State.UNMARKED;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
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

    public String toString() {
        return "Cell{" +
                "state=" + state +
                ", letter=" + letter +
                '}';
    }
}

