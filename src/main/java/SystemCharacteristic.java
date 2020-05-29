import java.util.ArrayList;

public class SystemCharacteristic {
    ArrayList<PlanetCharacteristic> planet = new ArrayList<>();
    double massOfStar;
    double radiusOfStar;
    int numberOfPlanets;
    double GC;

    public void setMassOfStar(String mass) {
        double massDouble;
        massDouble = Double.parseDouble(formatter(mass));
        massOfStar = massDouble;
    }

    public void setNumberOfPlanets (double num) {
        numberOfPlanets = (short) num;
    }

    public void setRadiusOfStar (String rad) {
        double radius;
        radius = Double.parseDouble(formatter(rad));
        radiusOfStar = radius;
    }

    public void setGC(String GU) {
        GC = Double.parseDouble(formatter(GU));
    }

    public String formatter(String in) {
        return in.replace(',', '.').trim();
    }

    public String toString() {
        return "SystemCharacteristic{" +
                "planet=" + planet.toString() +
                ", massOfStar=" + massOfStar +
                ", radiusOfStar=" + radiusOfStar +
                ", gravitation constant=" + GC +
                ", numberOfPlanets=" + numberOfPlanets +
                '}';
    }
}
