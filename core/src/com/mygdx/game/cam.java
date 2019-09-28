package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class cam {
    public  static void Level1(){


            Main.camera.zoom = 0.1f;
            Main.camera.update();
            Main.renderer.setView(Main.camera);
            Main.renderer.render();
            Main.batch.setProjectionMatrix(Main.camera.combined);
            Main.b2dr.render(Main.world, Main.camera.combined);
            System.out.println(Main.p.getX() + " , " + Main.p.getY());
            Main.r.play();

    }

    public static  void create (){
        WorldCreator.Boundaries(Main.world,Main.CurrentMap.getLayers().get("Boundary").getObjects());

    }

    public static void update (){

        Main.p.update(Main.batch);
    }
}
