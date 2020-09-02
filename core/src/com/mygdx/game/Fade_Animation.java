package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Fade_Animation {
     private static Texture texture = new Texture("Assets/Fade.png");
     private static Sprite sprite = new Sprite(texture);
     static float alpha = 0;

    public static boolean Fade(SpriteBatch batch, String level){
        switch((int)alpha){
            case 0:
                for (int i = 0; i < 100; i++) {
                    alpha += 0.0005;
                }
                break;
            case 1:
                Main.Game = level;
                Levels.test = false;
            default:
                break;
        }
        sprite.setPosition(0,0);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sprite.draw(batch,alpha);
        return true;
    }
}
