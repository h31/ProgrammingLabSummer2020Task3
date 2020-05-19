package model;

public abstract class Figure extends GameField {

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract boolean stop();
}

