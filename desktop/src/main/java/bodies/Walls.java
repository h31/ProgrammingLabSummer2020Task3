package main.java.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static main.java.resources.Load.fonw;

public class Walls extends Actor {
    World world;
    public Body body;
    Sprite sprite;
    Texture fon;

    public Walls(World world) {
        this.world=world;
        BodyDef wall = new BodyDef();
        wall.type = BodyDef.BodyType.StaticBody;
        wall.position.set(0.5f, 1);
        body = world.createBody(wall);
        FixtureDef fDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{
                new Vector2(-0.5f, -1.2f), new Vector2(-0.5f, 1.2f), new Vector2(0.501f, 1.2f), new Vector2(0.501f, -1.2f), new Vector2(-0.5f, -1.2f)
        });
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 1;
        body.createFixture(fDef);
        sprite = new Sprite(fonw);
        sprite.setBounds(0f,0f,1,2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        super.draw(batch, parentAlpha);
    }
}
