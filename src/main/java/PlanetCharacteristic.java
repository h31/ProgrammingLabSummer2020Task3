import javafx.scene.paint.Color;

public class PlanetCharacteristic {
    String name;
    String color;
    double radius;
    double G;
    double speedX;
    double speedY;
    int degrees;
    double positionX;
    double positionY;

    public void setName (String nameU){
        name = nameU;
    }

    public void setColor (Color colorU){
        color = colorU.toString();
    }

    public void setPositionX (String positionXU) {
        try {
            positionX = Double.parseDouble(positionXU.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
    }

    public void setPositionY (String positionYU) {
        try {
            positionX = Double.parseDouble(positionYU.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
    }

    public void setRadius (String radiusU) {
        try {
            radius = Double.parseDouble(radiusU.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        if (radius > 100000) {
            //здесь будет вызов MessageManager
        }
    }

    public void setSpeed (String speedU, String deg) {
        try {
            speedX = Double.parseDouble(speedU.trim()) * Math.cos(Double.parseDouble(deg.trim()));
            speedY = Double.parseDouble(speedU.trim()) * Math.sin(Double.parseDouble(deg.trim()));
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        if (radius > 100000) {
            //здесь будет вызов MessageManager
        }
    }

    public void setG (String GU) {
        try {
            G = Double.parseDouble(GU.trim());
            G = G * Math.pow(10, -11);
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
    }

    public String toString() {
        return "{" +
                "name=" + name +
                "color=" + color +
                ", Gravitation constant=" + G +
                ", radius=" + radius +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                ", degrees=" + degrees +
                '}';
    }


}
