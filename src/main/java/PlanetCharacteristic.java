import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class PlanetCharacteristic {
    String name;
    String color;
    double weight;
    double radius;
    double perihelion;
    short position;

    public void setName (String nameU){
        name = nameU;
    }

    public void setColor (Color colorU){
        color = colorU.toString();
    }

    public void setWeight (String weightU) {
        double weightDouble;
        try {
            weightDouble = Double.parseDouble(weightU.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        weight = weightDouble;
    }

    public void setPerihelion (String perihelionU) {
        try {
            perihelion = Double.parseDouble(perihelionU.trim());
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

    public void setPosition (String num) {
        try {
            position = Short.parseShort(num.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
    }

    public String toString() {
        return "{" +
                "color=" + color +
                ", weight=" + weight +
                ", radius=" + radius +
                ", perihelion=" + perihelion +
                ", position=" + position +
                '}';
    }


}
