class Scanning {
    private Field field;
    private boolean gameOver;
    private boolean win;
    private int width;
    private int height;
    private int minesCount;

    public Scanning(int width, int height, int minesCount) {
        setGameParams(width, height, minesCount);
        field = new Field(width, height, minesCount);
    }

    public Field getField() { return field; }

    public boolean gameOver() { return gameOver; }

    public boolean win() { return win; }

    public void clickHexagon(int x, int y) {
        if (gameOver || win) return;
        if (!field.clickHexagon(x, y)) gameOver = true;
        if (field.getClosed() == field.getMines()) win = true;
        Renovation.update();
    }

    void changeFlag(int x, int y) {
        field.changeFlag(x, y);
        Renovation.update();
    }

    public void restart() {
        field = new Field(width, height, minesCount);
        gameOver = false;
        win = false;
        Renovation.init(width, height);
        Renovation.update();
    }

    public void setGameParams(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
    }

}