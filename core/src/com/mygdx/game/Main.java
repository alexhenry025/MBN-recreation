package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricStaggeredTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Main extends ApplicationAdapter {
    static SpriteBatch batch;

    public static final float PPM = 0.3f;

    public static OrthographicCamera camera;

    public static int moves1;

    public static final int UP = 0, Down = 1, Left = 2, Right = 3, NW = 4, SW = 5, NE = 6 , SE = 7;

    static boolean C_animation = false, I_animation = false, animation;

    static String Game = "Intro_1";

    public  static Player p;

    public static World world;
    private TiledMap map;

    private TiledMap citymap;

    private TmxMapLoader mapLoader;

    static OrthogonalTiledMapRenderer renderer;
    private OrthogonalTiledMapRenderer cityrender;

    static Box2DDebugRenderer b2dr;

    private Menu menu;
    static Music r;

    private TiledMapTileLayer Building;

    @Override
    public void create() {
        world = new World(new Vector2(0,0),true);

        batch = new SpriteBatch();
        camera = new OrthographicCamera(720f, 480f);
        p = new Player();
        r = Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/room_soundtrack.mp3"));

        mapLoader = new TmxMapLoader();

        map = mapLoader.load("Assets/Maps/cyber.tmx");
//        map = mapLoader.load("Assets/Maps/Lan's Room.tmx");

       //citymap = mapLoader.load ("Assets/Maps/City.tmx");

		renderer = new OrthogonalTiledMapRenderer(map,PPM);

		cityrender = new OrthogonalTiledMapRenderer(citymap,PPM);

        world.setContactListener(new WorldContactListener());

        WorldCreator.Boundaries(world,map.getLayers().get("Boundary").getObjects());

      //  WorldCreator.Boundaries(world,map.getLayers().get("exit").getObjects());


        b2dr = new Box2DDebugRenderer();

        menu = new Menu();

        Building = (TiledMapTileLayer) map.getLayers().get("Building");
    }

    @Override
    public void render() {

        if(Game.equals("Intro_1")){ //starting the game off with the capcom intro

            C_animation = true;
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            menu.update(batch,0,0);
            batch.end();
            if(Menu.change){
                Game = "Intro_2";
            }
        }

        if(Game.equals("Intro_2")){ // once the capcom intro is finished loop title screen
            C_animation = false;
            I_animation = true;
            menu.m.play();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            menu.update(batch,0,0);
            batch.end();
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){ // if user presses the enter button
//                menu.s.play(); // add this feature in later and make it so the animation is slower
                Game = "level1";
                  menu.m.stop();
            }
        }
        
        if (Game.equals("level1")) {
            I_animation = false;
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glClearColor(51/255f,245/255f,219/255f,1);
            world.step(1 / 60f, 6, 2);
            cam.Level1();
            r.play();
            batch.begin();
            update();

            batch.end();
            renderer.getBatch().begin();
            renderer.renderTileLayer(Building);
            renderer.getBatch().end();
            move();

        }

    }

    public void update(){
        p.update(batch);
    }


    public static void move() {
        p.body.setLinearVelocity(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.UP) )){
            moves1 = NE;
            System.out.println("NE");
            p.getBody().applyLinearImpulse(new Vector2(80, 80), p.getBody().getWorldCenter(), true);
            animation = true;

        }

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.DOWN) )){
            moves1 = SE;
            p.getBody().applyLinearImpulse(new Vector2(80, -80), p.getBody().getWorldCenter(), true);
            animation = true;

        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.DOWN) )){
            moves1 = SW;
            p.getBody().applyLinearImpulse(new Vector2(-80, -80), p.getBody().getWorldCenter(), true);
            animation = true;

        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.UP) )){
            moves1 = NW;
            p.getBody().applyLinearImpulse(new Vector2(-80, 80), p.getBody().getWorldCenter(), true);
            animation = true;

        }

        else  if (Gdx.input.isKeyPressed(Input.Keys.UP)) {// moves the player up
            moves1 = UP;
            System.out.println("up");
            p.getBody().applyLinearImpulse(new Vector2(0, 80), p.getBody().getWorldCenter(), true);
            animation = true;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moves1 = Down;
            System.out.println("down");
            p.getBody().applyLinearImpulse(new Vector2(0, -80), p.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moves1 = Left;
            System.out.println("left");
			p.getBody().applyLinearImpulse(new Vector2(-80, 0), p.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moves1 = Right;
            System.out.println("right");
            p.getBody().applyLinearImpulse(new Vector2(80, 0), p.getBody().getWorldCenter(), true);
            animation = true;

        }

        else {
            p.getBody().applyLinearImpulse(new Vector2(p.getBody().getLinearVelocity().x * -1, p.getBody().getLinearVelocity().y * -1), p.getBody().getWorldCenter(), true);
            animation = false;
            p.frames = 0;
        }

	    p.setX(p.body.getPosition().x); // set the pos of player sprite to player body
	    p.setY(p.body.getPosition().y);

	    camera.position.x = p.getX(); // camera follows players x
	    camera.position.y = p.getY(); // camera follows players y

    }



    @Override
    public void dispose () {
        batch.dispose();
        renderer.dispose();
    }
}
