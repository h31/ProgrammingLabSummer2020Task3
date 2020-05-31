import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Piece {
    private final Image image;
     int positionX;
     int positionY;

    Piece(Image image, int positionX, int positionY) {
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void update()
    {
        positionY += 26;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public void delete(GraphicsContext gc){
        gc.setFill(Color.rgb(20, 20, 30));
        gc.fillRect(positionX, positionY, 25, 25);
    }

    public boolean intersectsDown(Integer[][] playingField){
        return playingField[(positionX-16)/26+1][(positionY -74)/26+1] != -1;
    }

    public boolean willIntersectDown(Integer[][] playingField){
        return playingField[(positionX-16)/26+1][(positionY -74)/26+2] != -1;
    }

    public boolean intersectsRight(Integer[][] playingField){
        return playingField[(positionX-16)/26+2][(positionY -74)/26+1] != -1;
    }

    public boolean intersectsLeft(Integer[][] playingField){
        return playingField[(positionX-16)/26][(positionY -74)/26+1] != -1;
    }
}
