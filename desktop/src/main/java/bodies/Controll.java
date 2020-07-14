package main.java.bodies;


import main.java.resources.Load;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import main.java.functions.Bot;
import main.java.functions.Velocity;

import static main.java.screens.GameScreen.control2;
import static main.java.screens.GameScreen.k;

public class Controll extends Actor {
    public int auto;
    World world;
    public Body body;
    Sprite sprite;


    public Controll(World world, float x, float y, int auto) {
        this.world = world;
        this.auto = auto;
        setPosition(x, y);
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        bDef.position.set(getX(), getY());
        bDef.fixedRotation = true;
        body = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.07f);
        fDef.shape = shape;
        fDef.density = 0.3f;
        fDef.restitution = 0.6f;
        fDef.friction = 0.4f;
        body.createFixture(fDef);
        sprite = new Sprite(Load.con);
        sprite.setBounds(x - 0.07f, y - 0.07f, 0.14f, 0.14f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (k) {
            if (auto == 0) {
                body.setTransform(getX(), getY(), 0);
                if (body.getWorldCenter().y > 1) body.setTransform(body.getWorldCenter().x, 1, 0);
                if (body.getWorldCenter().y < 0) body.setTransform(body.getWorldCenter().x, 0, 0);
                if (body.getWorldCenter().x > 1) body.setTransform(1, body.getWorldCenter().y, 0);
                if (body.getWorldCenter().x < 0) body.setTransform(0, body.getWorldCenter().y, 0);
            }
            if (auto==1) {
                Vector2 vec = Bot.botwork(control2.body.getWorldCenter().x, control2.body.getWorldCenter().y);
                control2.body.setTransform(vec.x, vec.y, 0);
                Vector2 speed = new Vector2(Velocity.velbot(vec.x, vec.y));
                control2.body.setLinearVelocity(speed);
            }
            if (auto==2){
                Vector2 vec = Bot.botwork(control2.body.getWorldCenter().x, control2.body.getWorldCenter().y);
                control2.body.setTransform(Float.parseFloat(String.valueOf(Velocity.xo2)),Float.parseFloat(String.valueOf(Velocity.yo2)), 0);
                Vector2 speed = new Vector2(Float.parseFloat(String.valueOf(Velocity.xo2)),Float.parseFloat(String.valueOf(Velocity.yo2)));
                control2.body.setLinearVelocity(speed);
            }
        } else body.setLinearVelocity(0, 0);
        sprite.setPosition(body.getPosition().x - 0.07f, body.getPosition().y - 0.07f);
        sprite.draw(batch);
        super.draw(batch, parentAlpha);

    }

}
