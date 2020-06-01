package com.mygdx.game.desktop;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.desktop.GameScreen.hip;
import static com.mygdx.game.desktop.GameScreen.hit;
import static com.mygdx.game.desktop.MainMenuScreen.click;

public class MyContact implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body fixA = contact.getFixtureA().getBody();
        Body fixB = contact.getFixtureB().getBody();
        if (fixA.getUserData() != null && fixB.getUserData() != null) {
            if (fixA.getUserData().equals("puck") && fixB.getUserData().equals("player")
                    || fixB.getUserData().equals("puck") && fixA.getUserData().equals("player")
                    || fixA.getUserData().equals("puck") && fixB.getUserData().equals("bot")
                    || fixB.getUserData().equals("puck") && fixA.getUserData().equals("bot")) {
                if (MainMenuScreen.s) hit.play();
            }
            if (fixA.getUserData().equals("puck") && fixB.getUserData().equals("wall")
                    || fixB.getUserData().equals("puck") && fixA.getUserData().equals("wall")) {
                if (MainMenuScreen.s) hip.play();
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
