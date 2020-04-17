package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Character {
    private Status status = Status.IDLE;
    private ImageView imgView;
    private Image[] imgArray;
//    private final float velocity;
//    private final int damage;
//    private final int health;

    Character(Image[] imgArray, int x, int y) {
        this.imgArray = imgArray;
        this.imgView = new ImageView(imgArray[0]);
        this.imgView.setX(x);
        this.imgView.setY(y);
    }
    Character(Image[] imgArray) {
        this.imgArray = imgArray;
        this.imgView = new ImageView(imgArray[0]);
    }

    public void move() {
    }

    public void setPosition(int x, int y) {
        imgView.setX(x);
        imgView.setY(y);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public Image[] getImgArray() {
        return imgArray;
    }
}
