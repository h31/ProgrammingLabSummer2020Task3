package model.com.example.project;

import java.awt.*;
import java.util.Objects;

/**
 * Отрезок между двумя точками
 */
public class Segment{
    private Point start;
    private Point end;

    public Segment(Point start,  Point end){
        this.start = start;
        this.end = end;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment that = (Segment) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}


