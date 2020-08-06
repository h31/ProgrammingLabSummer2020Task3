package model.com.example.project;

import java.awt.*;

public final class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cell cell = (Cell) object;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public String toString() {
        return "" + x + ":" + y;
    }
}