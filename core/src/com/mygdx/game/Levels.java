/*
 * This is a class for levels
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Levels {

    void Level1(OrthographicCamera camera, OrthogonalTiledMapRenderer renderer, Batch batch){
        camera.zoom = 0.1f;
        camera.update();
        renderer.setView(Main.camera);
        renderer.render();
        batch.setProjectionMatrix(Main.camera.combined);

    }
}
