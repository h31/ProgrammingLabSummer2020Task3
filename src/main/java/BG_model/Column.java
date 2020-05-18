package BG_model;

import java.util.Objects;
import java.util.Stack;

public class Column {
    private Stack<Chip> column;

    public Column() {
        this.column = new Stack<Chip>();
    }

    public Column(ChipColor color, int size ) {
        if (size < 1) throw new IllegalArgumentException("");
        this.column = new Stack<Chip>();
        for (int i=0;i<size;i++){
            this.column.push(new Chip(color));
        }
    }

    public void move(Column newColumn){

        newColumn.put(new Chip(this.onTop()));
        this.column.pop();

    }

    public int size(){
        return this.column.size();
    }

    public ChipColor onTop(){
        if (column.size() == 0) return null;
        else return column.peek().getColor();
    }

    public Chip put(Chip chip){
        return column.push(chip);
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