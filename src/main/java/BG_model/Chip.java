package BG_model;

import static java.util.Objects.hash;

public class Chip{

    private ChipColor color;

    public Chip(ChipColor color){
        this.color = color;
    }

    public ChipColor getColor(){
        return this.color;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Chip chip = (Chip) object;
        return color == chip.color;
    }

    public int hashCode() {
        return hash(super.hashCode(), color);
    }

    @Override
    public String toString() {
        return "Chip{" + color + '}';
    }
}

