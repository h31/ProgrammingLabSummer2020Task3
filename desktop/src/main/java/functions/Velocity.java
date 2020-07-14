package main.java.functions;

import com.badlogic.gdx.math.Vector2;

public class Velocity {
    volatile public static double xo2 = 0.5;
    volatile public static double yo2 = 1.8;
    public static double xo1, yo1;
    static double x2 = 0.5;
    static double y2 = 0.2;
    static double x1, y1;

    public static Vector2 velbot(Float x, Float y) {
        xo1 = xo2;
        yo1 = yo2;
        xo2 = x.doubleValue();
        yo2 = y.doubleValue();
        float oX = Float.parseFloat(String.valueOf(xo2 - xo1));
        float oY = Float.parseFloat(String.valueOf(yo2 - yo1));
        return new Vector2(oX * 10, oY * 10);
    }

    public static Vector2 velcon(Float x, Float y) {
        x1 = x2;
        y1 = y2;
        x2 = x.doubleValue();
        y2 = y.doubleValue();
        float oX = Float.parseFloat(String.valueOf(x2 - x1));
        float oY = Float.parseFloat(String.valueOf(y2 - y1));
        return new Vector2(oX * 10, oY * 10);
    }
}
