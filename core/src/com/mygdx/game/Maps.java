/*
 * This is a class for levels
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.Objects.*;

import java.util.ArrayList;

public class Maps {
    private WorldCreator wc;
    ArrayList<TiledMapTileLayer> layers;
    private Spawns spawns;
    public static boolean fade_out = false, fade_in = false; // booleans for fade in and fade out animations
    private Audio sound;
    public static int time = 0;
    private TiledMap map = new TiledMap();
    public Maps(){
        sound =  new Audio();
        spawns = new Spawns();
    }

    void Camera(OrthographicCamera camera, OrthogonalTiledMapRenderer renderer, Batch batch){//method used to update camera and change maps relative to the camera
        camera.zoom = 0.08f;
        camera.update();
        renderer.setView(Main.camera);
        renderer.render();
        batch.setProjectionMatrix(Main.camera.combined);
    }
    void CreateMap(String type, int x, int y) { // A function / Method that is used to create a new map
        map.dispose(); // dispose the current map
        layers = new ArrayList<TiledMapTileLayer>();
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load(type);
        Main.renderer = new OrthogonalTiledMapRenderer(map, Main.PPM);

        if (wc != null) {// if there are still bodies currently in the world , destroy them
            Main.bodiesToDestroy = wc.getToBeDestroyed();
            Main.destroyed = false;
        }
        Main.player.MoveBody(x, y);
        fade_in = true; // once the player has been moved set fade in to true
        new Fadein_timer(1); // use fade in timer to wait a second
        wc = new WorldCreator(Main.world, map);
        //gets all the tile layers in the map
        for(int i = 0; i < map.getLayers().getCount(); i ++){
            if(map.getLayers().get(i).getClass() == TiledMapTileLayer.class){
                layers.add((TiledMapTileLayer) map.getLayers().get(i));
            }
        }
    }

    void ChangeMap(){
        if(Main.Map_Counter == 0){
            sound.sounds.get(1).play();
        }
        Main.batch.begin();
        //Main.display.update();
        for (Fixture i : Main.objs) { // iterates all the objects player is colliding with, also y destroy body is put in the beginning
            if(i.getUserData().getClass() == NPC.class){
                NPC npc = (NPC) i.getUserData();
                //System.out.println("Colliding with NPC #" + npc.npc + ", X:" + npc.rect.x + ", Y:" + npc.rect.y);

            }

            if(i.getUserData().getClass() == Filter.class){
                Filter filter = (Filter) i.getUserData();
                Main.map_layer = filter.getLayer();
            }

            if (i.getUserData().getClass() == Door.class) { // if the object is a door
                if(time == 1){
                    time = 0;
                    Door door = (Door) i.getUserData(); //gets the user data of each door
                    if(door.change){
                        sound.stop();
                        sound.play(door.sound);
                    }
                    Main.Map_Counter = Integer.parseInt(door.getType()); // update the map counter
                    CreateMap(Main.Maps.get(Main.Map_Counter), spawns.getSpawns_x().get(door.counter), spawns.getSpawns_y().get(door.counter)); // change the map and the players x,y
                    // get the correct index            gets the correct spawn x                  gets the correct spawn y
                }
            }
        }
        if(fade_in){
            Fade_Animation.Fadein(Main.batch); // this will call the fade in animation function
        }
        if(fade_out){
            Fade_Animation.Fadeout(Main.batch); // this will call the fade out animation function
        }
        Main.batch.end();
    }


}
