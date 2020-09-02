/*
 * This is a class for levels
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import java.util.TimerTask;

public class Levels {
    private WorldCreator wc;
    static boolean test = false;
    private audio sound;

    public Levels(){
        sound =  new audio();
    }

    void Level1(OrthographicCamera camera, OrthogonalTiledMapRenderer renderer, Batch batch){//method used to update camera and change maps relative to the camera
        camera.zoom = 0.08f;
        camera.update();
        renderer.setView(Main.camera);
        renderer.render();
        batch.setProjectionMatrix(Main.camera.combined);
    }

    void CreateMap(String type, int x, int y) { // A function / Method that is used to create a new map
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load(type);
        Main.renderer = new OrthogonalTiledMapRenderer(map, Main.PPM);

        if (wc != null) {// if there are still bodies currently in the world , destroy them
            Main.bodiesToDestroy = wc.getToBeDestroyed();
            Main.destroyed = false;
        }
        Main.player.MoveBody(x, y);

        wc = new WorldCreator(Main.world, map);
        Main.Building = (TiledMapTileLayer) map.getLayers().get("Building");
    }

    void ChangeMap(){
        if(Main.Map_Counter == 0){
            sound.sounds.get(1).play();
        }
        Main.batch.begin();
        //Main.display.update();
        for (Fixture i : Main.objs) { // iterates all the objects player is colliding with, also y destroy body is put in the beginning
            if(i.getUserData().getClass() == NPC.class){
                NPC npc = (NPC) i.getUserData(); //gets the user data of each door
            }

            if (i.getUserData().getClass() == Door.class) { // if the object is a door
                Fade_Animation.alpha = 0f;
                test = true;
                Door door = (Door) i.getUserData(); //gets the user data of each door
                if(door.change){
                    sound.stop();
                    sound.play(door.sound);
                }
                Main.Map_Counter = Integer.parseInt(door.getType()); // update the map counter
                Main.spawns.setSpawnCounter(door.getCounter()); // get the spawn counter
                Main.SpawnCount = Main.spawns.getSpawnCounter(); // set a local variable in main to spawn counter
                CreateMap(Main.Maps.get(Main.Map_Counter), Main.spawns.getSpawns_x().get(Main.SpawnCount), Main.spawns.getSpawns_y().get(Main.SpawnCount)); // change the map and the players x,y
                // get the correct index            gets the correct spawn x                  gets the correct spawn y
            }
        }
        if(test){
            Fade_Animation.Fade(Main.batch, Main.Game);
        }
        Main.batch.end();
    }
}
