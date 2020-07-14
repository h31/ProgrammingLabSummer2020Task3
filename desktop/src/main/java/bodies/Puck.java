package main.java.bodies;


import main.java.resources.Load;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import main.java.screens.GameScreen;


public class Puck extends Actor {
    World world;
    public Body body;
    Sprite sprite;
    float max = 1f;
    float min = -1f;
    Vector2 vec;
    Vector2 veccopy;


    public Puck(World world) {
        vec= new Vector2(10,10);
        this.world = world;
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(0.5f, 1);
        bDef.fixedRotation = true;
        bDef.linearDamping = 0.1f;
        body = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.07f);
        fDef.shape = shape;
        fDef.density = 0.2f;
        fDef.restitution = 1f;
        fDef.friction = 0.4f;
        body.createFixture(fDef);
        Load.puckg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(Load.puckg);
        sprite.setBounds(-0.07f, -0.07f, 0.14f, 0.14f);
        veccopy = new Vector2(10,10);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        if (GameScreen.k) {
            if (Math.abs(veccopy.x) > 10e-10f && Math.abs(veccopy.y) > 10e-10f &&
                    Math.abs(veccopy.x - 10) > 10e-10f && Math.abs(veccopy.y - 10) > 10e-10f) {
                body.setLinearVelocity(veccopy);
            }

            if (body.getLinearVelocity().x > max) body.setLinearVelocity(max, body.getLinearVelocity().y);
            if (body.getLinearVelocity().x < min) body.setLinearVelocity(min, body.getLinearVelocity().y);
            if (body.getLinearVelocity().y > max) body.setLinearVelocity(body.getLinearVelocity().x, max);
            if (body.getLinearVelocity().y < min) body.setLinearVelocity(body.getLinearVelocity().x, min);
            veccopy.x = 10;
            veccopy.y = 10;
        } else {
            if (Math.abs(body.getLinearVelocity().x) > 10e-5 && Math.abs(body.getLinearVelocity().y) > 10e-5) {
                vec =body.getLinearVelocity();
                veccopy = new Vector2(vec);
                body.setLinearVelocity(0, 0);
            }
        }
        sprite.setPosition(body.getPosition().x - 0.07f, body.getPosition().y - 0.07f);
        sprite.draw(batch);
        super.draw(batch, parentAlpha);
    }
}
