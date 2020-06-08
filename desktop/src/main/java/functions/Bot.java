package main.java.functions;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import static main.java.screens.GameScreen.puck;

public class Bot {
    public static float xp = 0.5f;
    public static float yp = 1f;
    public static float xo, yo;
    private static boolean dif = false;

    public static Vector2 botwork(float conX, float conY) {
        Vector2 vec = new Vector2();
        xo = xp;
        Random r = new Random();
        yo = yp;
        xp = puck.body.getWorldCenter().x;
        yp = puck.body.getWorldCenter().y;
        vec.x = conX;
        vec.y = conY;
        if (Math.abs(yo - yp) < 0.0001f && Math.abs(xo - xp) < 0.0001f) puck.body.setLinearVelocity(0, 0);
        if (yp > 2 || yp < 0 || xp < 0 || xp > 1) {
            if (yp > 2) Score.c++;
            if (yp < 0) Score.b++;
            Velocity.xo2 = 0.5f;
            Velocity.yo2 = 1.8f;
            puck.body.setTransform(0.5f, 1, 0);
            puck.body.setLinearVelocity(0, 0);
            xp = 0.5f;
            yp = 1f;
            vec.x = 0.5f;
            vec.y = 1.8f;
            return vec;
        }
        if (Math.abs(yo - yp) < 0.003f && Math.abs(xo - xp) < 0.003f) vec.x += (xp - conX) / 65;
        else vec.x += (xp - xo) * (r.nextFloat() * (0.5) + 0.25);
        if (yp > vec.y || dif) {
            dif = true;
            if (vec.y < 1.8) vec.y += 0.02;
            else dif = false;
        } else {
            if (yp - yo > 0f && yp > 1 && yp < 2) {
                if (yp > 1.35) vec.y = vec.y - (r.nextFloat() * (0.02f) + 0.008f);
                if (Math.abs(yo - yp) < 0.003f || Math.abs(xo - xp) > 0.05f) {
                    vec.y = vec.y - (r.nextFloat() * (0.02f) + 0.008f);
                }
            }
            if (yp - yo < 0f) {
                if ((Math.abs(yo - yp) < 0.003f || Math.abs(xo - xp) > 0.05f) && yp > 1 && yp < 2) {
                    vec.y = vec.y - (r.nextFloat() * (0.02f) + 0.008f);
                } else if (vec.y < 1.8f) vec.y += 0.02;
            }
        }
        if (vec.x > 1) vec.x = 1;
        if (vec.x < 0) vec.x = 0;
        if (vec.y > 1.9) vec.y = 1.9f;


        return vec;

    }
}
