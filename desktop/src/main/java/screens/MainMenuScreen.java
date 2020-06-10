package main.java.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import main.java.resources.Load;
import main.java.stages.StageMenu;
import main.java.start.Aero;


public class MainMenuScreen implements Screen {
    public static Aero game;
    public static Stage stage;
    SpriteBatch batch;



    public MainMenuScreen(Aero gam) {
        game = gam;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        StageMenu.setStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Load.fon, 0, 0);
        batch.end();
        stage.draw();
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
