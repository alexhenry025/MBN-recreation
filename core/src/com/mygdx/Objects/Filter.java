package com.mygdx.Objects;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Main;

public class Filter {
    public Body body;
    Polygon polygon;
    private float x, y;
    private int layer;


    public Filter(Polygon polygon, float x, float y, int layer){
        this.polygon = polygon;
        this.x = x;
        this.y = y;
        this.layer = layer;
        CreateBox2d();// set the category and mask filter for the NPC's
    }

    private static PolygonShape createPolygon(Polygon polygon) {
        PolygonShape polygonShape = new PolygonShape();
        float[] vertices = polygon.getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i] * Main.PPM;
        }

        polygonShape.set(worldVertices);
        return polygonShape;
    }
    public void CreateBox2d() { // create the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(polygon.getOriginX() * Main.PPM, polygon.getOriginY() * Main.PPM));
        body = Main.world.createBody(bodyDef);

        //Create a shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = createPolygon(polygon);
        fixtureDef.filter.categoryBits = Main.Filter;
        body.createFixture(fixtureDef).setUserData(this);
    }
    public int getLayer(){
        return layer;
    }
    public Body getBody(){
        return this.body;
    }

}
