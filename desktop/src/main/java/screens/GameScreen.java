package main.java.screens;

import main.java.bodies.Controll;
import main.java.bodies.Puck;
import main.java.bodies.Walls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import main.java.start.Aero;
import main.java.functions.MyContact;
import main.java.functions.Score;
import main.java.stages.StageGame;


public class GameScreen implements Screen {
    public static Puck puck;
    public static Controll control2, control1;
    public static Walls walls;
    public static boolean k = true;
    public static Aero game;
    World world;
    public static Stage stage;
    //  Box2DDebugRenderer rend;
    SpriteBatch batch;


    public GameScreen(final Aero gam) {
        game = gam;
    }


    @Override
    public void show() {
        world = new World(new Vector2(0, 0), false);
        world.setContactListener(new MyContact());
        World.setVelocityThreshold(0f);
        control1 = new Controll(world, 0.5f, 0.2f, false);
        control2 = new Controll(world, 0.5f, 1.8f, true);
        puck = new Puck(world);
        walls = new Walls(world);
        control1.body.setUserData("player");
        puck.body.setUserData("puck");
        control2.body.setUserData("bot");
        //rend = new Box2DDebugRenderer();
        walls.body.setUserData("wall");
        Score.initialize();
        batch = new SpriteBatch();
        StageGame.setStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 232F / 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //   rend.render(world, stage.getCamera().combined);
        world.step(1 / 25f, 4, 4);
        stage.draw();
        batch.begin();
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


}