package main.java.start;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import main.java.screens.MainMenuScreen;

import static main.java.resources.Load.load;

public class Aero extends Game {

    public Skin skin;
    public SpriteBatch batch;
    BitmapFont font;


    public void create() {
        load();
        skin=new Skin();
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
    }

}