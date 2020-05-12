import java.util.ArrayList;

public class SystemCharacteristic {
    ArrayList<PlanetCharacteristic> planet = new ArrayList<>();
    double weightOfStar;
    double radiusOfStar;
    short numberOfPlanets;

    MessageManager message = new MessageManager();

    public void setWeightOfStar (String weight) {
        double weightDouble = 0;
        try {
            weightDouble = Double.parseDouble(weight.trim());
        }
        catch (NumberFormatException e) {
            message.error();
        }
        weightOfStar = weightDouble * Math.pow(10, 11);
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

    public String toString() {
        return "SystemCharacteristic{" +
                "planet=" + planet.toString() +
                ", weightOfStar=" + weightOfStar +
                ", radiusOfStar=" + radiusOfStar +
                ", numberOfPlanets=" + numberOfPlanets +
                '}';
    }
}
