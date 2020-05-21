public class LogicManager {
    public double acceleration (double G, double weight, double starPosition, double planetPosition, double distance){
        return (G * weight * (starPosition - planetPosition) / Math.pow(distance, 3)) + Math.random() * 0.0000001;
    }
    public double distance (double starX, double starY, double x, double y) {
        return Math.sqrt(Math.pow(starX - x, 2) + Math.pow(starY - y, 2));
    }
}
