package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.sun.org.apache.xpath.internal.operations.Or;

import static com.mygdx.game.Main.batch;
import static com.mygdx.game.Main.map;

public class LoadMap {

    public OrthographicCamera camera;

    OrthogonalTiledMapRenderer renderer;

    private TiledMapTileLayer Building;

    Box2DDebugRenderer b2dr;


    public  LoadMap(TiledMap map) {

        camera = new OrthographicCamera(720f, 480f);

        renderer = new OrthogonalTiledMapRenderer(map,Main.PPM);

        WorldCreator.Boundaries(Main.world,map.getLayers().get("Boundary").getObjects());

        WorldCreator.World(Main.world,map);

        Building = (TiledMapTileLayer) map.getLayers().get("Building");

        b2dr = new Box2DDebugRenderer();


    }

    public void Load(TiledMap map){
        renderer = new OrthogonalTiledMapRenderer(map,Main.PPM);

        WorldCreator.Boundaries(Main.world,map.getLayers().get("Boundary").getObjects());

        WorldCreator.World(Main.world,map);

        Building = (TiledMapTileLayer) map.getLayers().get("Building");

    }

    public void update(TiledMap map){
        camera.zoom = 0.1f;
        camera.update();
        renderer.setView(camera);
        renderer.render();
        batch.setProjectionMatrix(camera.combined);
        b2dr.render(Main.world, camera.combined);
    }

    public void changeMap(TiledMap Newmap){
        map.dispose();
        map = Newmap;
        renderer.setMap(map);
        Load(map);
        Main.city = true;
    }

}
