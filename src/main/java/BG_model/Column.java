package BG_model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Column {
    private Deque<Chip> column;

    Column() {
        this.column = new ArrayDeque<>();
    }

    Column(ChipColor color, int size) {
        if (size < 1) throw new IllegalArgumentException("");
        this.column = new ArrayDeque<>();
        for (int i=0;i<size;i++){
            this.column.addFirst(new Chip(color));
        }
    }

    boolean move(Column newColumn){
        if (this.size() != 0) {
            newColumn.put(new Chip(this.onTop()));
            this.column.removeFirst();
            return true;
        } else {
            return false;
        }

    }

    public int size(){
        return this.column.size();
    }

    public ChipColor onTop(){
        if (column.size() == 0) return null;
        else return column.peekFirst().getColor();
    }

    public void remove(){
        column.removeFirst();
    }

    private void put(Chip chip){
        column.addFirst(chip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column1 = (Column) o;
        return Objects.equals(column, column1.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column);
    }

    @Override
    public String toString() {
        return "Column{" + column +  '}';
    }
}