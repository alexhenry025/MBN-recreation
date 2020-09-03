/*
 * This is a class that makes the doors and handles map change also spawn x, y sets
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */
package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Door {
    Body body;
    private Rectangle rect;
    float New_x, New_y, angle;
    String type;
    int counter, sound;
    boolean change;
    boolean test=false;
    //private Stage stage
    //   rect type used for map change x y counter is what will tell the door which spawn to map to from array
    public Door(Rectangle rect, String type, int New_x, int New_y, int counter, float angle, int sound, boolean change){ // this gets the values for door from the world creator
        this.rect = rect;
        this.type = type;
        this.New_x = New_x;
        this.New_y = New_y;
        this.counter = counter;
        this.angle = angle;
        this.sound = sound;
        this.change = change;

        System.out.println("creating doors");
        CreateBox2d();
    }

    public void CreateBox2d(){ // create the body
        BodyDef bdef = new BodyDef();
        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.angle = -angle* MathUtils.degreesToRadians;

        bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

        body = Main.world.createBody(bdef);

        shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);

        def.shape = shape;

        this.body.createFixture(def);

        this.body.getFixtureList().get(0).setUserData(this);

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

}
