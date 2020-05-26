

public class Controller {

    public void restart() {
        Field field = new Field();
        Field.field = new  int[4][4];
        field.score = 0;
        Main.root.getChildren().add(Painter.draw(Field.field));
    }
}
