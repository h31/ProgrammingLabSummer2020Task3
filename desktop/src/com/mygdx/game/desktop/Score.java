package com.mygdx.game.desktop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {
    public static int c, b;
    static BitmapFont font;

    public static void initialize() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);

    }

    public static void displayMessage(SpriteBatch batch) {


        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "You " + c + " : " + b + " Bot");
        font.draw(batch, glyphLayout, 450 - 90f, 900 * 0.95f);

    }
}
