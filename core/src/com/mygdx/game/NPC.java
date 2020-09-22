package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;


public class NPC {
    public static Body body;
    public static Rectangle rect;
    float X,Y;
    int npc;

    public NPC(Rectangle rect, float X, float Y, int npc){ // this gets the values for door from the world creator
        this.rect = rect;
        this.X = X;
        this.Y = Y;
        this.npc = npc;
        CreateBox2d(categories.Category_NPC,categories.Category_NPC,(short)0);// set the category and mask filter for the NPC's
    }
    public void CreateBox2d(short c_bits,short m_bits,short g_index){ // create the body
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(rect.getX() * categories.PPM + rect.getWidth() / 2 * categories.PPM, rect.getY() * categories.PPM + rect.getHeight() / 2 * categories.PPM);
        body = Main.world.createBody(bodyDef);
        shape.setAsBox(rect.getWidth() / 2 * categories.PPM, rect.getHeight() / 2 * categories.PPM);
        fixtureDef.shape = shape;
        System.out.println("creating NPC's");
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits= c_bits;//get c_bits from CreateBox2d parameters
        fixtureDef.filter.maskBits = m_bits;//set maskBits to NPC to avoid collision with player
        fixtureDef.filter.groupIndex = g_index;
        this.body.createFixture(fixtureDef);
        this.body.getFixtureList().get(0).setUserData(this);
    }
    public Body getBody() {
        return body;
    }
    public float getX(){return X;}
    public float getY(){return Y;}
    public static Rectangle getRect(){
        System.out.println("NPC RECT Y POS " +body.getPosition().y);

        return new Rectangle(body.getPosition().x,body.getPosition().y,rect.getWidth()/2*categories.PPM,rect.getHeight()/2*categories.PPM);
    }


}


