package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Character {
    private Status action = Status.IDLE; // Анимация
    private Status.View view = Status.View.LEFT;
    private ImageView imgView; // Как выглядит сейчас
    private Image[] imgArray; // Массив кадров
//    private final float velocity;
//    private final int damage;
//    private final int health;

    public Character(Image[] imgArray, int x, int y) {
        this.imgArray = imgArray;
        this.imgView = new ImageView(imgArray[0]);
        this.imgView.setX(x);
        this.imgView.setY(y);
    }
    Character(Image[] imgArray) {
        this.imgArray = imgArray;
        this.imgView = new ImageView(imgArray[0]);
    }

    Character(Image[] imgArray, int x, int y, Status action, Status.View view) {
        this.imgArray = imgArray;
        this.imgView = new ImageView(imgArray[0]);
        this.action = action;
        this.view = view;
    }

    public abstract void move();

    public void setPosition(double x, double y) {
        imgView.setX(x);
        imgView.setY(y);
    }

    public Status getAction() {
        return action;
    }

    public void setAction(Status action, Status.View view) {
        this.action = action;
        this.view = view;
    }

    public Status.View getView() {
        return view;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public Image[] getImgArray() {
        return imgArray;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public void setImgArray(Image[] imgArray) {
        this.imgArray = imgArray;
        getImgView().setImage(imgArray[0]);
    }
}
