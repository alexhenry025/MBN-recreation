package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class cam {
    public  static void c(){


        Main.I_animation = false;
        Main.camera.zoom = 0.1f;
        Main.world.step(1 / 60f, 6, 2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Main.camera.update();
        Main.renderer.setView(Main.camera);
        Main.renderer.render();

        Main.batch.setProjectionMatrix(Main.camera.combined);

        Main.b2dr.render(Main.world, Main.camera.combined);

        System.out.println(Main.p.getX() + " , " + Main.p.getY());
        Main.r.play();


    }

    public static void update (){
        Main.p.update(Main.batch);
    }
}
