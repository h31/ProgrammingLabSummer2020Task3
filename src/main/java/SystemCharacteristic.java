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
        numberOfPlanets = (int) num;
    }

    public void setRadiusOfStar (String rad) {
        radiusOfStar = Double.parseDouble(formatter(rad));
    }

    public void setGC(String GU) {
        GC = Double.parseDouble(formatter(GU));
    }

    private String formatter(String in) {
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

    public String toStringFile() {
        var result = new StringBuilder(massOfStar + " " + radiusOfStar + " " + GC + " " + numberOfPlanets + " \n");
        for (var i: planet)
            result.append("'").append(i.name).append("'").append(" ")
                    .append(i.color).append(" ").append(i.radius).append(" ")
                    .append(i.positionX).append(" ").append(i.positionY).append(" ")
                    .append(i.speedX).append(" ").append(i.speedY).append(" \n");
        return result.toString();
    }
}
