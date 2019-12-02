/*
 * This is the main class for the game
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import java.io.IOException;
import java.util.*;

public class Main extends ApplicationAdapter {
    static SpriteBatch batch;

    public static final float PPM = 0.3f;

    public static OrthographicCamera camera;

    public static int moves1, Map_Counter = 0, SpawnCount;

    public static final int UP = 0, Down = 1, Left = 2, Right = 3, NW = 4, SW = 5, NE = 6 , SE = 7;

    static String Game = "Intro_1";

    public  static Player p;

    public static World world;

    private static OrthogonalTiledMapRenderer renderer;

    private static Box2DDebugRenderer b2dr; // debugger used for checking where all world hit boxes are

    private WorldCreator wc;

    private Levels levels;

    private Spawns spawns;

    public static ArrayList<String> Maps;

    private Menu menu;

    private Keyboard_Input keys;

    public static ArrayList<Fixture> objs = new ArrayList<Fixture>();

    private ArrayList<Body> bodiesToDestroy = new ArrayList<Body>(); // bodies to be destroyed after loading in a new map

    static boolean animation, destroyed = true, Change_Map = false;

    public static TiledMapTileLayer Building;


    @Override
    public void create() {
        world = new World(new Vector2(0,0),true);

        batch = new SpriteBatch();

        camera = new OrthographicCamera(720f, 480f);

        p = new Player();

        levels = new Levels();

        world.setContactListener(new WorldContactListener());

        Maps = new ArrayList<String>();

        File_Reading();

        b2dr = new Box2DDebugRenderer();

        menu = new Menu();

        keys = new Keyboard_Input();

        spawns = new Spawns();

        CreateMap(Maps.get(Map_Counter), 90, 60);
    }

    private void File_Reading(){ // used for loading in map locations from a file
        FileHandle file = Gdx.files.internal("Assets/Maps.txt");
        String[] text = file.readString().split(", ");
        for (int i = 0; i < text.length; i++) {
            Collections.addAll(Maps, text[i]);
        }
    }

    @Override
    public void render() {
        int mx = Gdx.input.getX();
        int my = Gdx.input.getY();

        menu.render(batch);

        if (Game.equals("level1")){
            System.out.println("\n" + p.getX() + ", " +  p.getY() + "\n");

            //Destorying bodies when needed this is put in the begining so that changeing the map in the move can be possible
            if (!destroyed) { // if not destroyed
                for (Body i : bodiesToDestroy) {
                    world.destroyBody(i); // destroys the current body
                }
                destroyed = true; // sets destroyed
            }
            else {
                world.step(1 / 60f, 6, 2);// calculates the physics using box2D
            }

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen
            Gdx.gl.glClearColor(51 / 255f, 245 / 255f, 219 / 255f, 1); // make background blue

            levels.Level1(camera,renderer,batch);
            b2dr.render(world,camera.combined);
            batch.begin();
            update();
            batch.end();

            if(Map_Counter > 0) { // since Lan's room doesnt have layers being added after make it for counter > 0
                renderer.getBatch().begin();
                renderer.renderTileLayer(Main.Building);
                renderer.getBatch().end();
            }
            move();
        }

    }

    public void update(){
        p.update(batch); // updates the players x y coordinates etc
    }

    public void move() {
//        p.body.setLinearVelocity(0, 0);
        keys.Player_Keys(); // calls the player keys method in the keyboard input class

        if(Change_Map) { // if change map
            for (Fixture i : objs) { // iterates all the objects player is colliding with, also y destroy body is put in the begining
                if (i.getUserData().getClass() == Door.class) { // if the object is a door
                    Door door = (Door) i.getUserData(); //gets the user data of each door
                    Map_Counter = Integer.parseInt(door.getType()); // update the map counter
                    spawns.setSpawnCounter(door.getCounter()); // get the spawn counter
                    SpawnCount = spawns.getSpawnCounter(); // set a local variable in main to spawn counter
                    CreateMap(Maps.get(Map_Counter), spawns.getSpawns_x().get(SpawnCount), spawns.getSpawns_y().get(SpawnCount)); // change the map and the players x,y
                    // get the correct index            gets the correct spawn x                  gets the correct spawn y
                }
            }
            Change_Map = false; // set it to false
        }

	    p.setX(p.body.getPosition().x); // set the pos of player sprite to player body
	    p.setY(p.body.getPosition().y); // ser the pos of player sprite to player body
	    camera.position.x = p.getX(); // camera follows players x
	    camera.position.y = p.getY(); // camera follows players y

    }

    private void CreateMap(String type, int x, int y){ // A function / Method that is used to create a new map

        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load(type);
        renderer = new OrthogonalTiledMapRenderer(map,PPM);

        if (wc != null) {
            bodiesToDestroy = wc.getToBeDestroyed();
            destroyed = false;
        }
        p.MoveBody(x,y);

        wc = new WorldCreator(world,map);
        Building = (TiledMapTileLayer) map.getLayers().get("Building");
    }

    @Override
    public void dispose () {
        batch.dispose();
        renderer.dispose();
    }
}
