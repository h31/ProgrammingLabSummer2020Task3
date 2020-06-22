import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Piece {
    private final Image image;
     int positionX;
     int positionY;
    int pieceSize;

    Piece(Image image, int positionX, int positionY, int pieceSize) {
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        this.pieceSize = pieceSize;
    }

    public void update()
    {
        positionY += pieceSize;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public void delete(GraphicsContext gc){
        gc.setFill(Color.rgb(20, 20, 30));
        gc.fillRect(positionX, positionY, pieceSize, pieceSize);
    }

    public boolean intersectsDown(Integer[][] playingField){
        return playingField[(positionX-1)/pieceSize][(positionY-1)/pieceSize-2] != -1;
    }

    public boolean willIntersectDown(Integer[][] playingField){
        return playingField[(positionX-1)/pieceSize][(positionY-1)/pieceSize-1] != -1;
    }

    public boolean intersectsRight(Integer[][] playingField){
        return playingField[(positionX-1)/pieceSize+1][(positionY-1)/pieceSize-2] != -1;
    }

    public boolean intersectsLeft(Integer[][] playingField){
        return playingField[(positionX-1)/pieceSize-1][(positionY-1)/pieceSize-2] != -1;
    }

    public boolean intersectsAny(Integer[][] playingField){
        return playingField[(positionX-1)/pieceSize][(positionY-1)/pieceSize-1] != -1 ||
                playingField[(positionX-1)/pieceSize][(positionY-1)/pieceSize-3] != -1 ||
                playingField[(positionX-1)/pieceSize+1][(positionY-1)/pieceSize-2] != -1 ||
                playingField[(positionX-1)/pieceSize-1][(positionY-1)/pieceSize-2] != -1;

    }
}
