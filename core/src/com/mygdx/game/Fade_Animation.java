package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fade_Animation {
     public static Texture texture = new Texture("Assets/Fade.png");
     public static Sprite sprite = new Sprite(texture);
     public static float alpha = 0;


    public static void Fade(SpriteBatch batch, String level){
        if(alpha<1) {
            for (int i = 0; i < 100; i++) {
                alpha += 0.0005;
            }
        }
        if(alpha>1) {
            Main.Game = level;
            Levels.test = false;
        }
        sprite.setPosition(0,0);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sprite.draw(batch,alpha);
    }
}
