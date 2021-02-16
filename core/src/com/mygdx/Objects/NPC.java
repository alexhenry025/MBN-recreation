package com.mygdx.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Main;


public class NPC {
    public Body body;
    Rectangle rect;
    float X,Y, Height, Width;
    int npc;

    public NPC(Rectangle rect, float X, float Y, int npc, float Height, float Width){ // this gets the values for door from the world creator
        this.rect = rect;
        this.X = X;
        this.Y = Y;
        this.npc = npc;
        this.Height = Height;
        this.Width = Width;
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
        def.filter.categoryBits = Main.Player;
        body.createFixture(def);
        body.getFixtureList().get(0).setUserData(this);

    }

    public Body getBody() {
        return body;
    }

    public float getX(){return X;}

    public float getY(){return Y;}

    public float getHeight(){return Height;}

    public Rectangle getRect(){
        return rect;
    }

}


