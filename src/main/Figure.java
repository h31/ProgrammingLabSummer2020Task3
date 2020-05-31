import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Figure{
    int x;
    int y;
    Piece piece1;
    Piece piece2;
    Piece piece3;
    Piece piece4;
    int position;

    Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void newFigure(int figureNum){
        position = 1;
        switch (figureNum) {
            case 0: {
                piece1 = new Piece(new Image("square1.png"), x, y);
                piece2 = new Piece(new Image("square1.png"), x+26, y);
                piece3 = new Piece(new Image("square1.png"), x+26, y+26);
                piece4 = new Piece(new Image("square1.png"), x, y+26);
                break;
            }
            case 1:{
                piece1 = new Piece(new Image("square2.png"), x, y);
                piece2 = new Piece(new Image("square2.png"), x-26, y);
                piece3 = new Piece(new Image("square2.png"), x, y+26);
                piece4 = new Piece(new Image("square2.png"), x+26, y+26);
                break;
            }
            case 2:{
                piece1 = new Piece(new Image("square3.png"), x, y);
                piece2 = new Piece(new Image("square3.png"), x+26, y);
                piece3 = new Piece(new Image("square3.png"), x-26, y);
                piece4 = new Piece(new Image("square3.png"), x, y+26);
                break;
            }
            case 3:{
                piece1 = new Piece(new Image("square4.png"), x, y);
                piece2 = new Piece(new Image("square4.png"), x, y+26);
                piece3 = new Piece(new Image("square4.png"), x, y+52);
                piece4 = new Piece(new Image("square4.png"), x+26, y+52);
                break;
            }
            case 4:{
                piece1 = new Piece(new Image("square5.png"), x, y);
                piece2 = new Piece(new Image("square5.png"), x+26, y);
                piece3 = new Piece(new Image("square5.png"), x, y+26);
                piece4 = new Piece(new Image("square5.png"), x-26, y+26);
                break;
            }
            case 5:{
                piece1 = new Piece(new Image("square6.png"), x, y);
                piece2 = new Piece(new Image("square6.png"), x, y+26);
                piece3 = new Piece(new Image("square6.png"), x, y+52);
                piece4 = new Piece(new Image("square6.png"), x-26, y+52);
                break;
            }
            case 6: {
                piece1 = new Piece(new Image("square7.png"), x, y);
                piece2 = new Piece(new Image("square7.png"), x+26, y);
                piece3 = new Piece(new Image("square7.png"), x+52, y);
                piece4 = new Piece(new Image("square7.png"), x-26, y);
            }
        }
    }

    public void nextFigure(Figure nextFigure) {
        position = 1;
        piece1 = nextFigure.piece1;
        piece2 = nextFigure.piece2;
        piece3 = nextFigure.piece3;
        piece4 = nextFigure.piece4;
    }

    public void update()
    {
        piece1.update();
        piece2.update();
        piece3.update();
        piece4.update();
    }
    public void render(GraphicsContext gc) {
        piece1.render(gc);
        piece2.render(gc);
        piece3.render(gc);
        piece4.render(gc);
    }

    public void delete(GraphicsContext gc) {
        piece1.delete(gc);
        piece2.delete(gc);
        piece3.delete(gc);
        piece4.delete(gc);
    }

    public boolean intersectsDown(Integer[][] playingField){
        return piece1.intersectsDown(playingField) || piece2.intersectsDown(playingField) ||
                piece3.intersectsDown(playingField) || piece4.intersectsDown(playingField);
    }

    public boolean willIntersectDown(Integer[][] playingField){
        return piece1.willIntersectDown(playingField) || piece2.willIntersectDown(playingField) ||
                piece3.willIntersectDown(playingField) || piece4.willIntersectDown(playingField);
    }

    public boolean intersectsRight(Integer[][] playingField){
        return piece1.intersectsRight(playingField) || piece2.intersectsRight(playingField) ||
                piece3.intersectsRight(playingField) || piece4.intersectsRight(playingField);
    }

    public boolean intersectsLeft(Integer[][] playingField){
        return piece1.intersectsLeft(playingField) || piece2.intersectsLeft(playingField) ||
                piece3.intersectsLeft(playingField) || piece4.intersectsLeft(playingField);
    }

    public void moveTo(int x, int y) {
        int moveY = y - piece1.positionY;
        int moveX = x - piece1.positionX;
        piece1.positionY += moveY;
        piece1.positionX += moveX;
        piece2.positionY += moveY;
        piece2.positionX += moveX;
        piece3.positionY += moveY;
        piece3.positionX += moveX;
        piece4.positionY += moveY;
        piece4.positionX += moveX;
    }

    public void moveRight() {
        piece1.positionX += 26;
        piece2.positionX += 26;
        piece3.positionX += 26;
        piece4.positionX += 26;
    }
    public void moveLeft() {
        piece1.positionX -= 26;
        piece2.positionX -= 26;
        piece3.positionX -= 26;
        piece4.positionX -= 26;
    }

    public void moveDown() {
        piece1.positionY += 26;
        piece2.positionY += 26;
        piece3.positionY += 26;
        piece4.positionY += 26;
    }

    public void rotate(int figureNum) {
        switch (figureNum) {
            case 0: {
                break;
            }
            case 1:{
                switch (position) {
                    case 1:
                    case 3:{
                        piece1 = new Piece(new Image("square2.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square2.png"), piece1.positionX, piece1.positionY-26);
                        piece3 = new Piece(new Image("square2.png"), piece1.positionX-26, piece1.positionY);
                        piece4 = new Piece(new Image("square2.png"), piece1.positionX-26, piece1.positionY+26);
                        position =2;
                        break;
                    }
                    case 2:
                    case 4:{
                        piece1 = new Piece(new Image("square2.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square2.png"), piece1.positionX-26, piece1.positionY);
                        piece3 = new Piece(new Image("square2.png"), piece1.positionX, piece1.positionY+26);
                        piece4 = new Piece(new Image("square2.png"), piece1.positionX+26, piece1.positionY+26);
                        position =1;
                        break;
                    }
                }
                break;
            }
            case 2:{
                switch (position) {
                    case 1:{
                        piece1 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY+26);
                        piece3 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY-26);
                        piece4 = new Piece(new Image("square3.png"), piece1.positionX-26, piece1.positionY);
                        position = 2;
                        break;
                    }
                    case 2:{
                        piece1 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square3.png"), piece1.positionX-26, piece1.positionY);
                        piece3 = new Piece(new Image("square3.png"), piece1.positionX+26, piece1.positionY);
                        piece4 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY-26);
                        position = 3;
                        break;
                    }
                    case 3:{
                        piece1 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY-26);
                        piece3 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY+26);
                        piece4 = new Piece(new Image("square3.png"), piece1.positionX+26, piece1.positionY);
                        position = 4;
                        break;
                    }
                    case 4:{
                        piece1 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square3.png"), piece1.positionX+26, piece1.positionY);
                        piece3 = new Piece(new Image("square3.png"), piece1.positionX-26, piece1.positionY);
                        piece4 = new Piece(new Image("square3.png"), piece1.positionX, piece1.positionY+26);
                        position = 1;
                        break;
                    }
                }
                break;
            }
            case 3:{
                switch (position) {
                    case 1:{
                        piece1 = new Piece(new Image("square4.png"), piece2.positionX+26, piece2.positionY);
                        piece2 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square4.png"), piece2.positionX-26, piece2.positionY);
                        piece4 = new Piece(new Image("square4.png"), piece2.positionX-26, piece2.positionY+26);
                        position = 2;
                        break;
                    }
                    case 2:{
                        piece1 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY+26);
                        piece2 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY-26);
                        piece4 = new Piece(new Image("square4.png"), piece2.positionX-26, piece2.positionY-26);
                        position = 3;
                        break;
                    }
                    case 3:{
                        piece1 = new Piece(new Image("square4.png"), piece2.positionX-26, piece2.positionY);
                        piece2 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square4.png"), piece2.positionX+26, piece2.positionY);
                        piece4 = new Piece(new Image("square4.png"), piece2.positionX+26, piece2.positionY-26);

                        position = 4;
                        break;
                    }
                    case 4:{
                        piece1 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY-26);
                        piece2 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square4.png"), piece2.positionX, piece2.positionY+26);
                        piece4 = new Piece(new Image("square4.png"), piece2.positionX+26, piece2.positionY+26);
                        position = 1;
                        break;
                    }
                }
                break;
            }
            case 4:{
                switch (position) {
                    case 1:
                    case 3:{
                        piece1 = new Piece(new Image("square5.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square5.png"), piece1.positionX, piece1.positionY+26);
                        piece3 = new Piece(new Image("square5.png"), piece1.positionX-26, piece1.positionY);
                        piece4 = new Piece(new Image("square5.png"), piece1.positionX-26, piece1.positionY-26);
                        position = 2;
                        break;
                    }
                    case 2:
                    case 4:{
                        piece1 = new Piece(new Image("square5.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square5.png"), piece1.positionX+26, piece1.positionY);
                        piece3 = new Piece(new Image("square5.png"), piece1.positionX, piece1.positionY+26);
                        piece4 = new Piece(new Image("square5.png"), piece1.positionX-26, piece1.positionY+26);
                        position = 1;
                        break;
                    }
                }
                break;
            }
            case 5:{
                switch (position) {
                    case 1:{
                        piece1 = new Piece(new Image("square6.png"), piece2.positionX+26, piece2.positionY);
                        piece2 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square6.png"), piece2.positionX-26, piece2.positionY);
                        piece4 = new Piece(new Image("square6.png"), piece2.positionX-26, piece2.positionY-26);
                        position = 2;
                        break;
                    }
                    case 2:{
                        piece1 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY+26);
                        piece2 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY-26);
                        piece4 = new Piece(new Image("square6.png"), piece2.positionX+26, piece2.positionY-26);
                        position = 3;
                        break;
                    }
                    case 3:{
                        piece1 = new Piece(new Image("square6.png"), piece2.positionX-26, piece2.positionY);
                        piece2 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square6.png"), piece2.positionX+26, piece2.positionY);
                        piece4 = new Piece(new Image("square6.png"), piece2.positionX+26, piece2.positionY+26);
                        position = 4;
                        break;
                    }
                    case 4:{
                        piece1 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY-26);
                        piece2 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY);
                        piece3 = new Piece(new Image("square6.png"), piece2.positionX, piece2.positionY+26);
                        piece4 = new Piece(new Image("square6.png"), piece2.positionX-26, piece2.positionY+26);
                        position = 1;
                        break;
                    }
                }
                break;
            }
            case 6: {
                switch (position) {
                    case 1:
                    case 3: {
                        piece1 = new Piece(new Image("square7.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square7.png"), piece1.positionX, piece1.positionY+26);
                        piece3 = new Piece(new Image("square7.png"), piece1.positionX, piece1.positionY+52);
                        piece4 = new Piece(new Image("square7.png"), piece1.positionX, piece1.positionY-26);
                        position = 2;
                        break;
                    }
                    case 2:
                    case 4: {
                        piece1 = new Piece(new Image("square7.png"), piece1.positionX, piece1.positionY);
                        piece2 = new Piece(new Image("square7.png"), piece1.positionX+26, piece1.positionY);
                        piece3 = new Piece(new Image("square7.png"), piece1.positionX+52, piece1.positionY);
                        piece4 = new Piece(new Image("square7.png"), piece1.positionX-26, piece1.positionY);
                        position = 1;
                        break;
                    }
                }
            }
        }
    }
}
