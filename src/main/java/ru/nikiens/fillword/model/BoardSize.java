package ru.nikiens.fillword.model;

public enum BoardSize {
    SMALL(12),
    MEDIUM(16),
    LARGE(20);

    private int size;

    BoardSize(int size) {
        this.size = size;
    }

    public int value() {
        return this.size;
    }

}


