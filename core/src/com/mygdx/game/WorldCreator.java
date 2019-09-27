package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public class WorldCreator {
    public static void Boundaries (World world, MapObjects objects){
        for(MapObject obj : objects){
            Shape shape;
            if(obj instanceof PolylineMapObject){
                shape = createPolyline((PolylineMapObject)obj);
            }
            else {
                continue;
            }
            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type =  BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape,0.1f);
            shape.dispose();
        }

    }

    private static ChainShape createPolyline(PolylineMapObject polyline){
        float [] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldverticies = new Vector2[vertices.length/2];

        for(int i = 0; i < worldverticies.length; i ++){
            worldverticies[i] = new Vector2(vertices[i * 2] * Main.PPM, vertices[i * 2 + 1] * Main.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldverticies);
        return cs;
    }
}