package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MainMenuScreen implements Screen {
    public static float volume;
    final Aero game;
    OrthographicCamera camera;
    Stage stage;
    Texture fon;
    SpriteBatch batch;
    boolean s = true;



    public MainMenuScreen(final Aero gam) {
        game = gam;
        fon = new Texture(Gdx.files.internal("fonmenu.png"));
        batch = new SpriteBatch();
        Texture startTexture = new Texture(Gdx.files.internal("start.png"));
        Texture duel = new Texture(Gdx.files.internal("duel.png"));
        Texture exit = new Texture(Gdx.files.internal("exit.png"));
        final Texture sound = new Texture(Gdx.files.internal("soundon.png"));
        final Texture soundoff = new Texture(Gdx.files.internal("soundoff.png"));
        game.skin.add("sound", sound);
        game.skin.add("soundoff", soundoff);
        game.skin.add("exit", exit);
        game.skin.add("duel", duel);
        game.skin.add("startImage", startTexture);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Button.ButtonStyle startstyle = new Button.ButtonStyle();
        Button.ButtonStyle duelstyle = new Button.ButtonStyle();
        final Button.ButtonStyle soundstyle = new Button.ButtonStyle();
        Button.ButtonStyle exitstyle = new Button.ButtonStyle();
        final Button.ButtonStyle soundoffstyle = new Button.ButtonStyle();
        exitstyle.up = game.skin.getDrawable("exit");
        soundstyle.up = game.skin.getDrawable("sound");
        soundoffstyle.up = game.skin.getDrawable("soundoff");
        duelstyle.up = game.skin.getDrawable("duel");
        startstyle.up = game.skin.getDrawable("startImage");
        Button exitb = new Button(exitstyle);
        final Button soundb = new Button(soundstyle);
        Button duelb = new Button(duelstyle);
        Button start = new Button(startstyle);
        exitb.setPosition(225 - 125, 310);
        duelb.setPosition(225 - 125, 430);
        start.setPosition(225 - 125, 550);
        start.addListener(
                new InputListener() {
                    public boolean touchDown(InputEvent event, float x,
                                             float y, int pointer, int button) {
                        game.setScreen(new GameScreen(game));
                        return true;
                    }
                });
        exitb.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x,
                                     float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        soundb.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (s) {
                    soundb.setStyle(soundoffstyle);
                    volume = 0f;
                } else {
                    soundb.setStyle(soundstyle);
                    volume = 1f;
                }
                s=!s;
                return true;
            }
        });
        soundb.setPosition(380, 80);
        stage.addActor(soundb);
        stage.addActor(exitb);
        stage.addActor(start);
        stage.addActor(duelb);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 450, 900);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        batch.begin();
        batch.draw(fon, 0, 0);
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
