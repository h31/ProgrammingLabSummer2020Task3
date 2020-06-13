package Model;

import javafx.scene.image.ImageView;

abstract class Animated {
    private ImageView[] imgArray;
    private ImageView imgView;

    public ImageView[] getImgArray() {
        return imgArray;
    }

    public void setImgArray(ImageView[] imgArray) {
        this.imgArray = imgArray;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }
}