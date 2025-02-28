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
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import java.util.*;

public class Main extends ApplicationAdapter {
    //Constants
    public static final float PPM = 0.3f;
    public static final short Player = 0x0001, Filter = 0x0002, maskNPC = 0x0003;
    static final int UP = 0, Down = 1, Left = 2, Right = 3, NW = 4, SW = 5, NE = 6 , SE = 7;// variable for player direction
    //Static variables
    static SpriteBatch batch;
    static OrthographicCamera camera; // creating camera
    static int moves1, Map_Counter = 0, map_layer = 3;// counter for maps , this would be = to the level of the map
    static String Game = "Intro_1";
    static Player player; // Player object
    static ArrayList<String> Maps;// object Array list for maps
    static OrthogonalTiledMapRenderer renderer;// render with respect to the Orthogonal plane
    static ArrayList<Fixture> objs = new ArrayList<Fixture>();// Array list of
    static ArrayList<Body> bodiesToDestroy = new ArrayList<Body>(); // bodies to be destroyed after loading in a new map
    static boolean animation, destroyed = true, filter = false;// booleans used to indicate animation change and object distruction
    //Private static variables
    private static Box2DDebugRenderer b2dr; // debugger used for checking where all world hit boxes are
    private com.mygdx.game.Maps maps;
    private Menu menu;
    private Keyboard_Input keys;
    public static World world;
    private WorldCreator wc;

    @Override
    public void create() {// create method
        world = new World(new Vector2(0,0),true); // creating new world , setting in the position
        world.setContactListener(new WorldContactListener());
        batch = new SpriteBatch();
        camera = new OrthographicCamera(720f, 480f);// camera created with respect to screen resolution
        player = new Player();
        maps = new Maps();
        Maps = new ArrayList<String>();
        File_Reading();
        b2dr = new Box2DDebugRenderer();// variable used to render the collision boxes. used for testing purposes.
        menu = new Menu();
        //display = new Display();
        keys = new Keyboard_Input();
        maps.CreateMap(Maps.get(Map_Counter), 90, 60);//create a map depending on what level we are on
        wc = new WorldCreator();
    }

    private void File_Reading(){ // used for loading in map locations from a file
        FileHandle file = Gdx.files.internal("Assets/Maps.txt");
        String[] text = file.readString().split(", ");
        for (int i = 0; i < text.length; i++) {
            Collections.addAll(Maps, text[i]);
        }
    }

    @Override
    public void render(){
        world.step(1 / 60f, 6, 2);// calculates the physics using box2D
        menu.render(batch);
        if (Game.equals("level1")){
            //System.out.println("\n" + player.getX() + ", " +  player.getY() + "\n");
            //Destroying bodies when needed this is put in the beginning so that changing the map in the method move can be possible
            if (!destroyed) { // if not destroyed
                for (Body i : bodiesToDestroy) {
                    world.destroyBody(i); // destroys the current body
                }
                destroyed = true; // sets destroyed
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen
            Gdx.gl.glClearColor(51 / 255f, 245 / 255f, 219 / 255f, 1); // make background blue
            maps.Camera(camera,renderer,batch);// render the first level
//            b2dr.render(world,camera.combined);
            batch.begin();
            update();
            //display.update();
            batch.end();
            renderer.getBatch().begin();
            renderer.renderTileLayer(maps.layers.get(2));
            if(filter){
                renderer.renderTileLayer(maps.layers.get(map_layer));
            }
            renderer.getBatch().end();
            move();
            maps.ChangeMap();
        }
    }
    private void update(){
        player.update(batch); // updates the players x y coordinates etc
    }


    private void move() {
//      p.body.setLinearVelocity(0, 0);
        keys.Player_Keys(); // calls the player keys method in the keyboard input class
	    player.setX(player.body.getPosition().x); // set the pos of player sprite to player body
	    player.setY(player.body.getPosition().y); // ser the pos of player sprite to player body
	    camera.position.x = player.getX(); // camera follows players x
	    camera.position.y = player.getY(); // camera follows players y
    }
    @Override
    public void dispose () {
        batch.dispose();
        renderer.dispose();
        player.dispose();
        Fade_Animation.dispose();
        objs.clear();
        maps.layers.clear();
        wc.dispose();
        System.gc();
        System.exit(0);
        menu.dispose();
    }
}
