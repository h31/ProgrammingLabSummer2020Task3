package main.java.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import main.java.network.Client;
import main.java.network.Server;
import main.java.resources.Load;
import main.java.stages.OnlineStage;
import main.java.start.Aero;

public class OnlineScreen implements Screen {
    public static Aero game;
    public static Stage stage;
    static BitmapFont font;
    SpriteBatch batch;

    public OnlineScreen(Aero gam) {
        game = gam;
    }

    @Override
    public void show() {
        OnlineStage.setStage();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GlyphLayout glyphLayout = new GlyphLayout();
        if (Server.p != null)
            glyphLayout.setText(font, Server.p.toString());
        if (Client.socket != null && Client.socket.isConnected() || Server.socket != null && Server.socket.isConnected())
            game.setScreen(new GameScreen(game));
        batch.begin();
        batch.draw(Load.fon, 0, 0);
        font.draw(batch, glyphLayout, 450 - 90f, 900 * 0.95f);
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
