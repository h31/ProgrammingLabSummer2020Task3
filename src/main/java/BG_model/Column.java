package BG_model;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Column {
    private Stack<Chip> column;

    public Column() {
        this.column = new Stack<Chip>();
    }

    public Column(Stack<Chip> column){
        this.column = column;
    }

    public Column(Chip[] column){
        this.column = new Stack<Chip>();
        for (Chip chip: column){
            this.column.push(chip);
        }
    }

    public Column(List<Chip> column){
        this.column = new Stack<Chip>();
        for (Chip chip: column){
            this.column.push(chip);
        }
    }

    public boolean move(Column newColumn){
        Stack<Chip> oldColumn = this.column;
        newColumn.put(new Chip(this.onTop()));
        this.column.pop();
        return oldColumn != this.column;
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