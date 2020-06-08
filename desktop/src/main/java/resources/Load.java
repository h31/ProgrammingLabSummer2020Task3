package main.java.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Load {
    public static Texture pause, resume, restart, mainmenu, fon, duel, exit, sound, soundoff, startTexture, puckg, con, fonw;
    public static Sound hit, hip, click;

    public static void load() {
        pause = new Texture(Gdx.files.internal("img/pause.png"));
        resume = new Texture(Gdx.files.internal("img/resume.png"));
        restart = new Texture(Gdx.files.internal("img/restart.png"));
        mainmenu = new Texture(Gdx.files.internal("img/mainmenu.png"));
        fon = new Texture(Gdx.files.internal("img/fonmenu.png"));
        duel = new Texture(Gdx.files.internal("img/duel.png"));
        exit = new Texture(Gdx.files.internal("img/exit.png"));
        sound = new Texture(Gdx.files.internal("img/soundon.png"));
        soundoff = new Texture(Gdx.files.internal("img/soundoff.png"));
        startTexture = new Texture(Gdx.files.internal("img/start.png"));
        puckg = new Texture(Gdx.files.internal("img/puck.png"));
        con = new Texture(Gdx.files.internal("img/c1.png"));
        fonw = new Texture(Gdx.files.internal("img/fon.png"));
        hit = new Gdx().audio.newSound(Gdx.files.internal("sound/hit.mp3"));
        hip = new Gdx().audio.newSound(Gdx.files.internal("sound/hip.mp3"));
        click = new Gdx().audio.newSound(Gdx.files.internal("sound/click.mp3"));
    }

}

