import javafx.scene.paint.Color;

public class PlanetCharacteristic {
    String name;
    String color;
    double radius;
    double G;
    double speedX;
    double speedY;
    double positionX;
    double positionY;

    MessageManager message = new MessageManager();

    public void setName (String nameU){
        name = nameU;
    }

    public void setColor (Color colorU){
        color = colorU.toString();
    }

    public void setPositionX (String positionXU) {
        try {
            positionX = Double.parseDouble(formatter(positionXU));
        }
        catch (NumberFormatException e) {
            message.error(25);
        }
    }

    public void setPositionY (String positionYU) {
        try {
            positionX = Double.parseDouble(formatter(positionYU));
        }
        catch (NumberFormatException e) {
            message.error(25);
        }
    }

    public void setRadius (String radiusU) {
        try {
            radius = Double.parseDouble(formatter(radiusU));
        }
        catch (NumberFormatException e) {
            message.error(22);
        }
    }

    public void setSpeed (String speedU, String deg) {
        try {
            speedX = Double.parseDouble(formatter(speedU)) * Math.cos(Double.parseDouble(formatter(deg)));
            speedY = Double.parseDouble(formatter(speedU)) * Math.sin(Double.parseDouble(formatter(deg)));
        }
        catch (NumberFormatException e) {
            message.error(24);
        }

    }

    public void setG (String GU) {
        try {
            G = Double.parseDouble(formatter(GU));
            G = G * Math.pow(10, -11);
        }
        catch (NumberFormatException e) {
            message.error(21);
        }
    }

    public String formatter(String in) {
        return in.replace(',', '.').trim();
    }

    public String toShortString() {
        return  "Planet " + name +
                "\nColor " + color +
                "\nGravitation constant " + G +
                "\nRadius " + radius;
    }

    public String toString() {
        return "PlanetCharacteristic{" +
                "name=" + name +
                ", color=" + color +
                ", gravitation constant=" + G +
                ", radius=" + radius +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }

}
