package main.java.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import main.java.network.Client;
import main.java.network.Server;
import main.java.resources.Load;
import main.java.screens.MainMenuScreen;
import main.java.screens.OnlineScreen;

import java.io.IOException;

import static main.java.screens.OnlineScreen.game;

public class OnlineStage {

    public static void setStage() {
        game.skin.add("connect", Load.connect);
        game.skin.add("back", Load.back);
        OnlineScreen.stage = new Stage();
        Gdx.input.setInputProcessor(OnlineScreen.stage);
        Button.ButtonStyle connects = new Button.ButtonStyle();
        Button.ButtonStyle backs = new Button.ButtonStyle();
        backs.up = game.skin.getDrawable("back");
        connects.up = OnlineScreen.game.skin.getDrawable("connect");
        Button backb = new Button(backs);
        Button connectb = new Button(connects);
        backb.setPosition(225 - 125, 430);
        connectb.setPosition(225 - 125, 550);
        connectb.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x,
                                     float y, int pointer, int button) {
                if (StageMenu.s) Load.click.play();
                MyTextInputListener listener = new MyTextInputListener();
                 Gdx.input.getTextInput(listener, "Connect", "IP:PORT", "");
                return true;
            }
        });
        backb.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (StageMenu.s) Load.click.play();
                game.setScreen(new MainMenuScreen(game));
                try {
                    Server.server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        OnlineScreen.stage.addActor(connectb);
        OnlineScreen.stage.addActor(backb);
    }
    static class MyTextInputListener implements Input.TextInputListener {
        @Override
        public void input (String text) {
            Client.ipAddr = text;
            try {
                Client.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void canceled () {
        }
    }
}
