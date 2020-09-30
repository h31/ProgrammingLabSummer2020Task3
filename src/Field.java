import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Field {
    private Hexagon[][] zone;
    private int width;
    private int height;
    private int minesCount;
    private int closedHexagon;

    public Field(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        zone = new Hexagon[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                zone[i][j] = new Hexagon(j, i);
            }
        }
        setMines();
        setMinesCount();
        closedHexagon = width * height;
    }

    private void setMines() {
        int minesRemained = 58;
        while (minesRemained != 0) {
            int x = ThreadLocalRandom.current().nextInt(0, width);
            int y = ThreadLocalRandom.current().nextInt(0, height);
            if (!zone[y][x].getMine()) {
                zone[y][x].setMine();
                minesRemained--;
            }
        }
    }

    private void setMinesCount() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int count = 0;
                for (Hexagon hexagon : getHexagon(j, i)) {
                    if (hexagon.getMine()) {
                        count++;
                    }
                }
                zone[i][j].setMinesCount(count);
            }
        }
    }

    private void addHexagon(List<Hexagon> list, int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        list.add(zone[y][x]);
    }

    private List<Hexagon> getHexagon(int x, int y) {
        LinkedList<Hexagon> list = new LinkedList<>();
        if (y % 2 == 0) {
            addHexagon(list, x, y - 2);
            addHexagon(list, x, y + 2);
            addHexagon(list, x - 1, y + 1);
            addHexagon(list, x - 1, y - 1);
            addHexagon(list, x, y + 1);
            addHexagon(list, x, y - 1);
        } else {
            addHexagon(list, x, y - 2);
            addHexagon(list, x, y + 2);
            addHexagon(list, x, y - 1);
            addHexagon(list, x, y + 1);
            addHexagon(list, x + 1, y - 1);
            addHexagon(list, x + 1, y + 1);
        }
        return list;
    }

    boolean clickHexagon(int x, int y) {
        if (zone[y][x].flag() || zone[y][x].getVisibility()) return true;
        zone[y][x].setVisible();
        closedHexagon--;
        if (zone[y][x].getMine()) {
            return false;
        }
        if (zone[y][x].getMinesCount() != 0) {
            return true;
        }
        for (Hexagon hexagon : getHexagon(x, y)) {
            clickHexagon(hexagon.getX(), hexagon.getY());
        }
        return true;
    }

    Hexagon[][] getHexagon() { return zone; }

    int getClosed() { return closedHexagon; }

    void changeFlag(int x, int y) {
        zone[y][x].changeFlag();
        Renovation.update();
    }

    public int getMines() { return minesCount; }
}