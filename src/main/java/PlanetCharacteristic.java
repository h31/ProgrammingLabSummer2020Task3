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

    public void setPositionX (String positionXU) throws Exception {
        try {
            positionX = Double.parseDouble(positionXU.trim());
        }
        catch (NumberFormatException e) {
            message.error(25);
        }
    }

    public void setPositionY (String positionYU) throws Exception {
        try {
            positionX = Double.parseDouble(positionYU.trim());
        }
        catch (NumberFormatException e) {
            message.error(25);
        }
    }

    public void setRadius (String radiusU) throws Exception {
        try {
            radius = Double.parseDouble(radiusU.trim());
        }
        catch (NumberFormatException e) {
            message.error(22);
        }
    }

    public void setSpeed (String speedU, String deg) throws Exception {
        try {
            speedX = Double.parseDouble(speedU.trim()) * Math.cos(Double.parseDouble(deg.trim()));
            speedY = Double.parseDouble(speedU.trim()) * Math.sin(Double.parseDouble(deg.trim()));
        }
        catch (NumberFormatException e) {
            message.error(24);
        }

    }

    public void setG (String GU) throws Exception {
        try {
            G = Double.parseDouble(GU.trim());
            G = G * Math.pow(10, -11);
        }
        catch (NumberFormatException e) {
            message.error(21);
        }
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
