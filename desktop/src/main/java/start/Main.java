package main.java.start;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Air hockey";
        cfg.addIcon("img/icon.png", Files.FileType.Internal);
        cfg.width = 450;
        cfg.height = 900;
        new LwjglApplication(new Aero(), cfg);
    }
}