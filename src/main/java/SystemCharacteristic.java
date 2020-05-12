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
            weightDouble = Double.parseDouble(formatter(weight));
        }
        catch (NumberFormatException e) {
            message.error(11);
        }
        weightOfStar = weightDouble * Math.pow(10, 11);
    }

    public void setNumberOfPlanets (double num) {
        numberOfPlanets = (short) num;
    }

    public void setRadiusOfStar (String rad) {
        double radius = 0;
        try {
            radius = Double.parseDouble(formatter(rad));
        }
        catch (NumberFormatException e) {
            message.error(12);
        }
        radiusOfStar = radius;
    }

    public String formatter(String in) {
        return in.replace(',', '.').trim();
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
