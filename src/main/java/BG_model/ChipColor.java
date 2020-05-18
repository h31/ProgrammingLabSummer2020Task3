package BG_model;

public enum ChipColor {
    WHITE(1),
    BLACK(-1);


    public final int moveDir;

    ChipColor(int moveDir) {
        this.moveDir = moveDir;
    }
}
