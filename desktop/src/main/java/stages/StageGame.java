package main.java.stages;

import main.java.functions.Score;
import main.java.functions.Velocity;
import main.java.resources.Load;
import main.java.screens.GameScreen;
import main.java.screens.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static main.java.screens.GameScreen.*;

public class StageGame {
    static boolean mouse = false;
    static Button resumeb, restartb, menub, pauseb;

    public static void setStage() {
        stage = new Stage(new FitViewport(1, 2));
        stage.addActor(walls);
        stage.addActor(control1);
        stage.addActor(control2);
        stage.addActor(puck);
        game.skin.add("resume", Load.resume);
        game.skin.add("restart", Load.restart);
        game.skin.add("mainmenu", Load.mainmenu);
        game.skin.add("pause", Load.pause);
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
                if (StageMenu.s) Load.click.play();
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
                if (StageMenu.s) Load.click.play();
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
                if (StageMenu.s) Load.click.play();
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
                    Vector2 imp = new Vector2(Velocity.velcon(x, y));
                    control1.body.setLinearVelocity(imp);
                }
                return super.mouseMoved(event, x, y);

            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (k) {
                    control1.setPosition(x, y);
                    Vector2 imp = new Vector2(Velocity.velcon(x, y));
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
    }
}
