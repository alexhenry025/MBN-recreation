package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class WorldCreator{
    Body body;
    BodyDef bdef = new BodyDef();
    FixtureDef fdef = new FixtureDef();
    PolygonShape shape = new PolygonShape();

    static ArrayList<Body> boundries = new ArrayList<Body>();
    static ArrayList<Body> Exit = new ArrayList<Body>();
    static ArrayList<Body> Enter = new ArrayList<Body>();
    static ArrayList<ArrayList<Body>> Bodies = new ArrayList<ArrayList<Body>>();
    private ArrayList<Body> toBeDestroyed = new ArrayList<Body>();

    static float x_enter, y_enter, x_exit, y_exit , x_spawn , y_spawn;

    public WorldCreator(World world, TiledMap map){

        for(int i = 0; i < map.getLayers().getCount(); i ++){
            for(MapObject obj : map.getLayers().get(2).getObjects().getByType(PolylineMapObject.class)){
                Shape shape;
                if(obj instanceof PolylineMapObject){
                    shape = createPolyline((PolylineMapObject)obj);
                }
                else if (obj instanceof PolygonMapObject){
                    shape = createPolygon((PolygonMapObject)obj);
                }
                else {
                    continue;
                }
                bdef.type =  BodyDef.BodyType.StaticBody;
                body = world.createBody(bdef);
                body.createFixture(shape,0.1f);
                boundries.add(body);
                shape.dispose();
            }

            for (MapObject obj : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {// this will be the spawn box for the player
                Rectangle rect = ((RectangleMapObject) obj).getRectangle();

                x_spawn = ((RectangleMapObject) obj).getRectangle().getX() * Main.PPM;
                y_spawn = ((RectangleMapObject) obj).getRectangle().getY() * Main.PPM;

                bdef.type = BodyDef.BodyType.StaticBody;

                bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

                // body = world.createBody(bdef);

                // shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);

                // fdef.shape = shape;
                // body.createFixture(fdef).setUserData("Spawn");

            }

            // for buildings
            for (MapObject obj : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {// exit box for the player
                Rectangle rect = ((RectangleMapObject) obj).getRectangle();

                x_exit = ((RectangleMapObject) obj).getRectangle().getX() * Main.PPM;//
                y_exit = ((RectangleMapObject) obj).getRectangle().getY() * Main.PPM;

                bdef.type = BodyDef.BodyType.StaticBody;

                bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM); // set the position of the exit box ,
                // this will indicate where the player will spawn after exiting a door

                body = world.createBody(bdef);

                shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);

                fdef.shape = shape;
                body.createFixture(fdef).setUserData("Exit");
                Exit.add(body);

            }

            for (MapObject obj : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {

                Rectangle rect = ((RectangleMapObject) obj).getRectangle();
                x_enter = ((RectangleMapObject) obj).getRectangle().getX() * Main.PPM;
                y_enter = ((RectangleMapObject) obj).getRectangle().getY() * Main.PPM;

                bdef.type = BodyDef.BodyType.StaticBody;

                bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

                body = world.createBody(bdef);

                shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);

                fdef.shape = shape;
                body.createFixture(fdef).setUserData("Enter");
                Enter.add(body);

            }
        }

        Bodies.add(boundries);
        Bodies.add(Exit);
        Bodies.add(Enter);

    }

    private static ChainShape createPolyline(PolylineMapObject polyline){
        float [] vertices = polyline.getPolyline().getTransformedVertices();//
        Vector2[] worldverticies = new Vector2[vertices.length/2];

        for(int i = 0; i < worldverticies.length; i ++){
            worldverticies[i] = new Vector2(vertices[i * 2] * Main.PPM, vertices[i * 2 + 1] * Main.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldverticies);
        return cs;
    }
    private static ChainShape createPolygon (PolygonMapObject polygon){
        float [] v = polygon.getPolygon().getTransformedVertices();
        Vector2[] wv = new Vector2[v.length/2];

        for ( int i =0 ; i< wv.length ; i++){
            wv[i] = new Vector2(v[i*2] * Main.PPM , v[i*2+1] * Main.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(wv);
        return cs;
    }




    public ArrayList<Body> getToBeDestroyed() {
        toBeDestroyed = new ArrayList<Body>();
        for (Body i : boundries) toBeDestroyed.add(i);
        for (Body i : Enter) toBeDestroyed.add(i);
        for (Body i : Exit) toBeDestroyed.add(i);

        return toBeDestroyed;
    }
}