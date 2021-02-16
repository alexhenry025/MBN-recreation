/*
 * This is a class that controls all world objects
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.*;
import com.mygdx.Objects.*;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.Objects.Filter;

import java.util.ArrayList;

class WorldCreator{
    private Body body;
    private BodyDef bdef = new BodyDef();
    private FixtureDef fdef = new FixtureDef();
    private PolygonShape shape = new PolygonShape();
    private ArrayList<Body> Wall; // this is an object array list that contains all bodies for wall collision
    private ArrayList<Body> toBeDestroyed = new ArrayList<Body>();

    static ArrayList<NPC>npc;
    static ArrayList<Door> door; // this is an object Array list for doors makes all the doors and also handles map change
    static ArrayList<Filter>filter;

    public WorldCreator(){

    }

    public WorldCreator(World world, TiledMap map){
        door = new ArrayList<Door>(); // make a new one every time a new map loads
        Wall = new ArrayList<Body>(); // make a new one every time a new map loads
        npc = new ArrayList<NPC>();//this is a list of all the NPC is the current map
        filter = new ArrayList<Filter>();

        for(MapLayer mapLayer : map.getLayers()) { // iterate through all the objects in the map
            for (MapObject obj : mapLayer.getObjects()) { // check all the objects in the map
                if(obj.getName() == null){
                    continue;
                }
                if (obj instanceof PolylineMapObject) { // if an object is polyline type
                    Shape shape;
                    shape = createPolyline((PolylineMapObject) obj); // create polyline object
                    bdef.type = BodyDef.BodyType.StaticBody;
                    body = world.createBody(bdef);
                    if(obj.getName().equals("Wall")){
                        body.createFixture(shape, Main.PPM).setUserData("Wall");
                        Wall.add(body);
                        shape.dispose();
                    }
                }

                else if(obj instanceof  PolygonMapObject){
                    shape = getPolygon((PolygonMapObject) obj);
                    bdef.type = BodyDef.BodyType.StaticBody;
                    body = world.createBody(bdef);
                    fdef.shape = shape;
                    if(obj.getName().equals("Filter")){
                        Polygon polygon = ((PolygonMapObject) obj).getPolygon();
                        filter.add(new Filter(polygon, polygon.getX(), polygon.getY(), (Integer) obj.getProperties().get("Layer")));
                        for(Fixture f : body.getFixtureList()){
                            f.setUserData("Filter");
                        }
                        shape.dispose();
                    }
                }
                else if (obj instanceof RectangleMapObject) { // if its a rect type
                    Rectangle rect = ((RectangleMapObject) obj).getRectangle(); // create the rect and body
                    bdef.type = BodyDef.BodyType.StaticBody;
                    bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);
                    body = world.createBody(bdef);
                    shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);
                    fdef.shape = shape;
                    if (obj.getName().equals("Door")) { // if the object name is door
                        // make a new door and add it to the list

                        door.add(new Door(rect, (String) obj.getProperties().get("type"), (Integer) (obj.getProperties().get("x_d")), (Integer) (obj.getProperties().get("y_d")), (Integer) (obj.getProperties().get("SpawnLoc")), (Float)obj.getProperties().get("Angle"), (Integer) obj.getProperties().get("Sound"), (Boolean) obj.getProperties().get("Change_sound")));
                        for (Fixture f : body.getFixtureList()) { // add the door's fixture used for collision to the fixture list
                            f.setUserData("Door");
                        }
                        shape.dispose();
                    }
                    if (obj.getName().equals("NPC")) {
                        //  System.out.println("Hi lan how are you doing today ");
                        npc.add(new NPC(rect,((RectangleMapObject) obj).getRectangle().getX(), ((RectangleMapObject) obj).getRectangle().getY(), (Integer) obj.getProperties().get("NPC"), ((RectangleMapObject) obj).getRectangle().height, ((RectangleMapObject) obj).getRectangle().width));
                        for (Fixture f : body.getFixtureList()) { // add the door's fixture used for collision to the fixture list
                            f.setUserData("NPC");
                        }
                        shape.dispose();
                    }
                }
            }
        }
    }

    private static ChainShape createPolyline(PolylineMapObject polyline){ // method that creates the polyline objects

        float [] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldverticies = new Vector2[vertices.length/2];

        for(int i = 0; i < worldverticies.length; i ++){
            worldverticies[i] = new Vector2(vertices[i * 2] * Main.PPM, vertices[i * 2 + 1] * Main.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldverticies);
        return cs;
    }

    private static CircleShape getCircle(CircleMapObject circleObject) {
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius / Main.PPM);
        circleShape.setPosition(new Vector2(circle.x / Main.PPM, circle.y / Main.PPM));
        return circleShape;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i] / Main.PPM;
        }

        polygon.set(worldVertices);
        return polygon;
    }
    public ArrayList<Body> getToBeDestroyed() { // this Array list stores all physical objects on the map that will need to be destroyed upon map change

        toBeDestroyed = new ArrayList<Body>();
        toBeDestroyed.addAll(Wall);
        for(Door i : door) toBeDestroyed.add(i.body);
        for(NPC i : npc) toBeDestroyed.add(i.body);
        for(Filter i : filter) toBeDestroyed.add(i.getBody());
        return toBeDestroyed;
    }

    public void dispose(){
        shape.dispose();

    }
}