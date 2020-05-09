import java.util.ArrayList;

public class SystemCharacteristic {
    ArrayList<PlanetCharacteristic> planet = new ArrayList<PlanetCharacteristic>();
    double weightOfStar;
    double radiusOfStar;
    short numberOfPlanets;
    double focusDistance;

    public void setWeightOfStar (String weight) {
        double weightDouble;
        try {
            weightDouble = Double.parseDouble(weight.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        weightOfStar = weightDouble;
    }

    public void setNumberOfPlanets (double num) {
        numberOfPlanets = (short) num;
    }

    public void setRadiusOfStar (String rad) {
        double radius;
        try {
            radius = Double.parseDouble(rad.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        if (radius > 100000) {
            //здесь будет вызов MessageManager
        }
        radiusOfStar = radius;
    }

    public void setFocusDistance (String foc) {
        double focus;
        try {
            focus = Double.parseDouble(foc.trim());
        }
        catch (NumberFormatException e) {
            //здесь будет вызов MessageManager
            throw e;
        }
        focusDistance = focus;
    }

    public String toString() {
        return "SystemCharacteristic{" +
                "planet=" + planet.toString() +
                ", weightOfStar=" + weightOfStar +
                ", radiusOfStar=" + radiusOfStar +
                ", numberOfPlanets=" + numberOfPlanets +
                ", focusDistance=" + focusDistance +
                '}';
    }
}
