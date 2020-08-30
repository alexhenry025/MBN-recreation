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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import java.util.*;

public class Main extends ApplicationAdapter {
    static SpriteBatch batch;

    public static final float PPM = 0.3f;

    public static final int UP = 0, Down = 1, Left = 2, Right = 3, NW = 4, SW = 5, NE = 6 , SE = 7;// variable for player direction
    public static OrthographicCamera camera; // creating camera
    public static int moves1, Map_Counter = 0, SpawnCount;// counter for maps , this would be = to the level of the map

    static String Game = "Intro_1";
    public  static Player p; // Player object


    private static Box2DDebugRenderer b2dr; // debugger used for checking where all world hit boxes are

   // private WorldCreator wc;

    private Levels levels;

    public static ArrayList<String> Maps;// object Array list for maps

    private Menu menu;

    public static Display display;

    private Keyboard_Input keys;
    public  static OrthogonalTiledMapRenderer renderer;// render with respect to the Orthogonal plane

    public static ArrayList<Fixture> objs = new ArrayList<Fixture>();// Array list of

    public static ArrayList<Body> bodiesToDestroy = new ArrayList<Body>(); // bodies to be destroyed after loading in a new map
    public static TiledMapTileLayer Building;// Layer made in tile
    public static World world;
    public static Spawns spawns;
    static boolean animation, destroyed = true, Change_Map = false;// booleans used to indicate , chang of map , removal of objects and animation
    public static float time;

    @Override
    public void create() {// create method
        world = new World(new Vector2(0,0),true); // creating new world , setting in the position

        world.setContactListener(new WorldContactListener());

        batch = new SpriteBatch();

        camera = new OrthographicCamera(720f, 480f);// camera created with respect to screen resolution

        p = new Player();

        levels = new Levels();

        Maps = new ArrayList<String>();

        File_Reading();

        b2dr = new Box2DDebugRenderer();// variable used to render the collision boxes. used for testing purposes.

        menu = new Menu();

        display = new Display();

        keys = new Keyboard_Input();

        spawns = new Spawns();

        levels.CreateMap(Maps.get(Map_Counter), 90, 60);//create a map depending on what level we are on
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
        time += Gdx.graphics.getDeltaTime();
        world.step(1 / 60f, 6, 2);// calculates the physics using box2D
        menu.render(batch);
        if (Game.equals("level1")){
          //  System.out.println("\n" + p.getX() + ", " +  p.getY() + "\n");
            //Destroying bodies when needed this is put in the beginning so that changing the map in the method move can be possible
            if (!destroyed) { // if not destroyed
                for (Body i : bodiesToDestroy) {
                    world.destroyBody(i); // destroys the current body
                }
                destroyed = true; // sets destroyed
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen
            Gdx.gl.glClearColor(51 / 255f, 245 / 255f, 219 / 255f, 1); // make background blue

            levels.Level1(camera,renderer,batch);// render the first level
            b2dr.render(world,camera.combined);
            batch.begin();
            update();
            batch.end();

            if(Map_Counter > 0) { // since Lan's room doesn't have layers being added after make it for counter > 0
                renderer.getBatch().begin();
                renderer.renderTileLayer(Main.Building);
                renderer.getBatch().end();
            }
            move();
            levels.ChangeMap();
        }
    }
    public void update(){
        p.update(batch); // updates the players x y coordinates etc
    }

    public void move() {
//      p.body.setLinearVelocity(0, 0);
        keys.Player_Keys(); // calls the player keys method in the keyboard input class
	    p.setX(p.body.getPosition().x); // set the pos of player sprite to player body
	    p.setY(p.body.getPosition().y); // ser the pos of player sprite to player body
	    camera.position.x = p.getX(); // camera follows players x
	    camera.position.y = p.getY(); // camera follows players y
    }

    @Override
    public void dispose () {
        batch.dispose();
        renderer.dispose();
    }
}
