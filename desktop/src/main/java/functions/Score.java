package main.java.functions;

import main.java.screens.EndGame;
import main.java.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static main.java.screens.GameScreen.game;



public class Score {
    public static int c, b;
    static BitmapFont font;
    public static float timeSeconds = 0f;
    static float period = 180f;

    public static void initialize() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    public static void displayMessage(SpriteBatch batch) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "You " + c + " : " + b + " Bot");
        font.draw(batch, glyphLayout, 450 - 90f, 900 * 0.95f);
        glyphLayout.setText(font, (int) (180 - timeSeconds) / 60 + ":" + (59 - (int) timeSeconds % 60));
        font.draw(batch, glyphLayout, 450 - 35f, 900 * 0.9f);
        if (GameScreen.k) timeSeconds += Gdx.graphics.getRawDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            handleEvent();
        }

    }

    static void handleEvent() {
        game.setScreen(new EndGame(game));
    }

}
