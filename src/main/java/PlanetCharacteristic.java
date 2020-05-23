import javafx.scene.paint.Color;

public class PlanetCharacteristic {
    String name;
    String color;
    double radius;
    double GC;
    double speedX;
    double speedY;
    double positionX;
    double positionY;

    public void setName (String nameU){
        name = nameU;
    }

    public void setColor (Color colorU){
        color = colorU.toString();
    }

    public void setPositionX (String positionXU) {
        positionX = Double.parseDouble(formatter(positionXU));
    }

    public void setPositionY (String positionYU) {
        positionY = Double.parseDouble(formatter(positionYU));
    }

    public void setRadius (String radiusU) {
        radius = Double.parseDouble(formatter(radiusU));
    }

    public void setSpeed (String speedU, String deg) {
        LogicManager logic = new LogicManager();
        speedX = logic.speedX(Double.parseDouble(formatter(speedU)), Double.parseDouble(formatter(deg)));
        speedY = logic.speedY(Double.parseDouble(formatter(speedU)), Double.parseDouble(formatter(deg)));
    }

    public void setGC(String GU) {
        GC = Double.parseDouble(formatter(GU));
    }

    public String formatter(String in) {
        return in.replace(',', '.').trim();
    }

    public String toShortString() {
        return  "Planet " + name +
                "\nColor " + color +
                "\nGravitation constant " + GC +
                "\nRadius " + radius;
    }

    public String toString() {
        return "PlanetCharacteristic{" +
                "name=" + name +
                ", color=" + color +
                ", gravitation constant=" + GC +
                ", radius=" + radius +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }

}
