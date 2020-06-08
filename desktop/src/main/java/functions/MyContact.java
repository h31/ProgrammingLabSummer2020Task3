package main.java.functions;

import main.java.resources.Load;
import com.badlogic.gdx.physics.box2d.*;
import main.java.stages.StageMenu;


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
                if (StageMenu.s) Load.hit.play();
            }
            if (fixA.getUserData().equals("puck") && fixB.getUserData().equals("wall")
                    || fixB.getUserData().equals("puck") && fixA.getUserData().equals("wall")) {
                if (StageMenu.s) Load.hip.play();
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
