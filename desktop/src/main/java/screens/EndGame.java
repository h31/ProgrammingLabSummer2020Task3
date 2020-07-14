package main.java.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import main.java.functions.Score;
import main.java.start.Aero;

public class EndGame implements Screen {
    final Aero game;
    SpriteBatch batch;
    Texture end;


    public EndGame(Aero gam) {
        this.game = gam;
        String a = "img/1.png";
        batch = new SpriteBatch();
        if (Score.b>Score.c) a = "img/2.png";
        if (Score.b==Score.c) a = "img/3.png";
        Score.b=0;
        Score.c=0;
        end = new Texture(Gdx.files.internal(a));
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        batch.begin();
        batch.draw(end, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
