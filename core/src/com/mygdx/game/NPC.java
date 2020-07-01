package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class NPC {
    Body body;
    Rectangle rect;
    float New_x, New_y;
    String type;
    int counter;

    public NPC(Rectangle rect, String type){ // this gets the values for door from the world creator
        this.rect = rect;
        this.type = type;

        CreateBox2d();
    }

    public void CreateBox2d(){ // create the body
        BodyDef bdef = new BodyDef();
        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;

        bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

        body = Main.world.createBody(bdef);

        shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);

        def.shape = shape;

        this.body.createFixture(def);

        this.body.getFixtureList().get(0).setUserData(this);
        System.out.println("creating NPC's");

    }

    public Body getBody() {
        return body;
    }

    public String getType() {
        return type;
    }

    public int getCounter(){
        return counter;
    }


    public Rectangle getRect(){
        return rect;
    }


}


