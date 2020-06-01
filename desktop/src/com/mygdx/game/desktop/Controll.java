package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.desktop.GameScreen.*;

class Controll extends Actor {
    public boolean auto;
    World world;
    Body body;
    Sprite sprite;
    Texture con;


    public Controll(World world, float x, float y, boolean auto) {
        this.world = world;
        this.auto = auto;
        setPosition(x, y);
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        bDef.position.set(getX(), getY());
        bDef.fixedRotation = true;
        //  bDef.angularDamping = 0.01f;
        body = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.07f);
        fDef.shape = shape;
        fDef.density = 0.3f;
        fDef.restitution = 0.6f;
        fDef.friction = 0.4f;
        body.createFixture(fDef);
        con = new Texture(Gdx.files.internal("c1.png"));

        sprite = new Sprite(con);
        sprite.setBounds(x - 0.07f, y - 0.07f, 0.14f, 0.14f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (k) {
            if (!auto) {
                body.setTransform(getX(), getY(), 0);
                if (body.getWorldCenter().y > 1) body.setTransform(body.getWorldCenter().x, 1, 0);
                if (body.getWorldCenter().y < 0) body.setTransform(body.getWorldCenter().x, 0, 0);
                if (body.getWorldCenter().x > 1) body.setTransform(1, body.getWorldCenter().y, 0);
                if (body.getWorldCenter().x < 0) body.setTransform(0, body.getWorldCenter().y, 0);
            } else {
                Vector2 vec = botwork(puck.body, control2.body.getWorldCenter().x, control2.body.getWorldCenter().y);
                control2.body.setTransform(vec.x, vec.y, 0);
                Vector2 imp = new Vector2(vel(control2.body.getWorldCenter().x, vec.y));
                control2.body.setLinearVelocity(imp);
            }
        } else body.setLinearVelocity(0,0);
        sprite.setPosition(body.getPosition().x - 0.07f, body.getPosition().y - 0.07f);
        sprite.draw(batch);
        super.draw(batch, parentAlpha);

    }

}
