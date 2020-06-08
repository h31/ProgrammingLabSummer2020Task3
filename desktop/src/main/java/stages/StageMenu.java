package main.java.stages;

import main.java.resources.Load;
import main.java.screens.GameScreen;
import main.java.screens.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import main.java.start.Aero;

import static main.java.screens.MainMenuScreen.game;

public class StageMenu {
   public static boolean s = true;
    static String mus;

    public static void setStage(){
        game.skin.add("sound", Load.sound);
        game.skin.add("soundoff", Load.soundoff);
        game.skin.add("exit", Load.exit);
        game.skin.add("duel", Load.duel);
        game.skin.add("startImage", Load.startTexture);
        MainMenuScreen.stage = new Stage();
        Gdx.input.setInputProcessor(MainMenuScreen.stage);
        Button.ButtonStyle startstyle = new Button.ButtonStyle();
        Button.ButtonStyle duelstyle = new Button.ButtonStyle();
        Button.ButtonStyle  soundfstyle = new Button.ButtonStyle();
        final Button.ButtonStyle soundstyle = new Button.ButtonStyle();
        Button.ButtonStyle exitstyle = new Button.ButtonStyle();
        final Button.ButtonStyle soundoffstyle = new Button.ButtonStyle();
        if (s) mus = "sound";
        else mus="soundoff";
        soundfstyle.up = game.skin.getDrawable(mus);
        exitstyle.up = game.skin.getDrawable("exit");
        soundstyle.up = game.skin.getDrawable("sound");
        soundoffstyle.up = game.skin.getDrawable("soundoff");
        duelstyle.up = game.skin.getDrawable("duel");
        startstyle.up = game.skin.getDrawable("startImage");
        Button exitb = new Button(exitstyle);
        final Button soundb = new Button(soundfstyle);
        Button duelb = new Button(duelstyle);
        Button start = new Button(startstyle);
        exitb.setPosition(225 - 125, 310);
        duelb.setPosition(225 - 125, 430);
        start.setPosition(225 - 125, 550);
        start.addListener(
                new InputListener() {
                    public boolean touchDown(InputEvent event, float x,
                                             float y, int pointer, int button) {
                        if (s) Load.click.play();
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
                Load.click.play();
                if (s) {
                    soundb.setStyle(soundoffstyle);
                } else {
                    soundb.setStyle(soundstyle);
                }
                s = !s;
                return true;
            }
        });
        soundb.setPosition(380, 80);
        MainMenuScreen.stage.addActor(soundb);
        MainMenuScreen.stage.addActor(exitb);
        MainMenuScreen.stage.addActor(start);
        MainMenuScreen.stage.addActor(duelb);


    }
}
