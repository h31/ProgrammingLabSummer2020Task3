package main.java.screens;

import main.java.resources.Load;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import main.java.start.Aero;
import main.java.stages.StageMenu;


public class MainMenuScreen implements Screen {
    public static Aero game;
    OrthographicCamera camera;
    public static Stage stage;
    SpriteBatch batch;



    public MainMenuScreen(final Aero gam) {
        game = gam;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        StageMenu.setStage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 450, 900);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        batch.begin();
        batch.draw(Load.fon, 0, 0);
        batch.end();
        stage.draw();
        game.batch.end();
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
