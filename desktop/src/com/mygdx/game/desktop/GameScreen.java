package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import static com.mygdx.game.desktop.MainMenuScreen.click;

public class GameScreen implements Screen {
    public static Puck puck;
    public static Controll control2;
    public static boolean k = true;
    public static float xp = 0.5f;
    public static float yp = 1f;
    public static float xo, yo;
    public static double xo2 = 0.5;
    public static double yo2 = 1.8;
    public static double xo1, yo1;
    static Sound hit, hip;
    private static boolean dif = false;
    final Aero game;
    BitmapFont font;
    World world;
    Stage stage;
    //  Box2DDebugRenderer rend;
    double x2 = 0.5;
    double y2 = 0.2;
    double x1, y1;
    boolean mouse = false;
    SpriteBatch batch;
    Texture pause, resume, restart, mainmenu;
    Button resumeb, restartb, menub, pauseb;
    private float timeSeconds = 0f;


    public GameScreen(final Aero gam) {
        this.game = gam;
    }

    public static Vector2 vel(Float x, Float y) {
        xo1 = xo2;
        yo1 = yo2;
        xo2 = x.doubleValue();
        yo2 = y.doubleValue();
        float oX = Float.parseFloat(String.valueOf(xo2 - xo1));
        float oY = Float.parseFloat(String.valueOf(yo2 - yo1));
        return new Vector2(oX * 10, oY * 10);
    }

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
            xo2 = 0.5f;
            yo2 = 1.8f;
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

    public Vector2 impulse(Float x, Float y) {
        x1 = x2;
        y1 = y2;
        x2 = x.doubleValue();
        y2 = y.doubleValue();
        float oX = Float.parseFloat(String.valueOf(x2 - x1));
        float oY = Float.parseFloat(String.valueOf(y2 - y1));
        return new Vector2(oX * 10, oY * 10);
    }

    @Override
    public void show() {
        hit = new Gdx().audio.newSound(Gdx.files.internal("hit.mp3"));
        hip = new Gdx().audio.newSound(Gdx.files.internal("hip.mp3"));
        world = new World(new Vector2(0, 0), false);
        world.setContactListener(new MyContact());
        world.setVelocityThreshold(0f);
        stage = new Stage(new FitViewport(1, 2));
        final Controll control1 = new Controll(world, 0.5f, 0.2f, false);
        control2 = new Controll(world, 0.5f, 1.8f, true);
        control1.body.setUserData("player");
        puck = new Puck(world);
        puck.body.setUserData("puck");
        control2.body.setUserData("bot");
        //rend = new Box2DDebugRenderer();
        Walls walls = new Walls(world);
        walls.body.setUserData("wall");
        Score.initialize();
        batch = new SpriteBatch();
        stage.addActor(walls);
        stage.addActor(control1);
        stage.addActor(control2);
        stage.addActor(puck);
        pause = new Texture(Gdx.files.internal("pause.png"));
        resume = new Texture(Gdx.files.internal("resume.png"));
        restart = new Texture(Gdx.files.internal("restart.png"));
        mainmenu = new Texture(Gdx.files.internal("mainmenu.png"));
        game.skin.add("resume", resume);
        game.skin.add("restart", restart);
        game.skin.add("mainmenu", mainmenu);
        game.skin.add("pause", pause);
        Button.ButtonStyle pausestyle = new Button.ButtonStyle();
        Button.ButtonStyle resumestyle = new Button.ButtonStyle();
        Button.ButtonStyle restartstyle = new Button.ButtonStyle();
        Button.ButtonStyle menustyle = new Button.ButtonStyle();
        pausestyle.up = game.skin.getDrawable("pause");
        resumestyle.up = game.skin.getDrawable("resume");
        restartstyle.up = game.skin.getDrawable("restart");
        menustyle.up = game.skin.getDrawable("mainmenu");
        pauseb = new Button(pausestyle);
        resumeb = new Button(resumestyle);
        restartb = new Button(restartstyle);
        menub = new Button(menustyle);
        menub.setSize(0.56f, 0.223f);
        restartb.setSize(0.56f, 0.223f);
        resumeb.setSize(0.56f, 0.223f);
        pauseb.setSize(0.78f, 1.02f);
        menub.setPosition(0.22f, 0.69f);
        restartb.setPosition(0.22f, 0.96f);
        resumeb.setPosition(0.22f, 1.23f);
        pauseb.setPosition(0.111f, 0.56f);

        resumeb.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MainMenuScreen.s) click.play();
                GameScreen.k = true;
                pauseb.remove();
                menub.remove();
                resumeb.remove();
                restartb.remove();
                return true;
            }
        });
        restartb.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MainMenuScreen.s) click.play();
                GameScreen.k = true;
                Score.b = 0;
                Score.c = 0;
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
        menub.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MainMenuScreen.s) click.play();
                game.setScreen(new MainMenuScreen(game));
                k = true;
                Score.b = 0;
                Score.c = 0;
                return true;
            }
        });
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (k) {
                    control1.setPosition(x, y);
                    mouse = !mouse;
                }
                return true;

            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if (k) {
                    if (mouse) control1.setPosition(x, y);
                    Vector2 imp = new Vector2(impulse(x, y));
                    control1.body.setLinearVelocity(imp);
                }
                return super.mouseMoved(event, x, y);

            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (k) {
                    control1.setPosition(x, y);
                    Vector2 imp = new Vector2(impulse(x, y));
                    control1.body.setLinearVelocity(imp);
                    super.touchDragged(event, x, y, pointer);
                }
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == 131) {
                    k = !k;
                    if (!k) {
                        stage.addActor(pauseb);
                        stage.addActor(menub);
                        stage.addActor(resumeb);
                        stage.addActor(restartb);
                    } else {
                        pauseb.remove();
                        menub.remove();
                        resumeb.remove();
                        restartb.remove();
                    }
                }
                return super.keyDown(event, keycode);
            }
        });
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 232F / 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //   rend.render(world, stage.getCamera().combined);
        world.step(1 / 25f, 4, 4);
        stage.draw();
        if (k) timeSeconds += Gdx.graphics.getRawDeltaTime();
        float period = 180f;
        if (timeSeconds > period) {
            timeSeconds -= period;
            handleEvent();
        }
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, (int) (180 - timeSeconds) / 60 + ":" + (59 - (int) timeSeconds % 60));
        batch.begin();
        font.draw(batch, glyphLayout, 450 - 35f, 900 * 0.9f);

        Score.displayMessage(batch);
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


    public void handleEvent() {

        game.setScreen(new EndGame(game));

    }
}