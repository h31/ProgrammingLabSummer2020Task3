import javafx.scene.paint.Color;

public class PlanetCharacteristic {
    String name;
    String color;
    double weight;
    double radius;
    double G;
    double speed;
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

    public void setSpeed (String speedU) {
        try {
            speed = Double.parseDouble(speedU.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        if (radius > 100000) {
            //здесь будет вызов MessageManager
        }
    }

    public void setDegrees (String deg) {
        try {
            degrees = Integer.parseInt(deg.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
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
                ", weight=" + weight +
                ", Gravitation constant=" + G +
                ", radius=" + radius +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", speed=" + speed +
                ", degrees=" + degrees +
                '}';
    }


}
