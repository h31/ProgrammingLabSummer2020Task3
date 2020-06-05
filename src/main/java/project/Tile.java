package project;

import javafx.scene.layout.StackPane;


public class Tile extends StackPane {
    private int x, y;
    private int bombsAround = 0;
    private boolean hasBomb;
    private boolean isOpened = false;
    private boolean hasFlag = false;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isMarked() {
        return hasFlag;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setBombsAround(int bombs) {
        bombsAround = bombs;
    }

    public void setOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    public void setFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public void setBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
