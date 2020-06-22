package project.models;


import java.util.Objects;

public class Tile {
    private int x, y;
    private int bombsAround = 0;
    private boolean hasBomb = false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x &&
                y == tile.y &&
                bombsAround == tile.bombsAround &&
                hasBomb == tile.hasBomb &&
                isOpened == tile.isOpened &&
                hasFlag == tile.hasFlag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, bombsAround, hasBomb, isOpened, hasFlag);
    }
}
