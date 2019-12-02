/*
 * This class is used for the intro animations
 * 2019 - Ghanem & Usman
 * Megaman battle network 6
 */

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Intro_Animations {
    private Sprite sprite;
    private ArrayList<Texture> Texture;
    private ArrayList<ArrayList<Texture>> Sprites;
    private int timer, frame;

    public Intro_Animations(String type, int size){
        sprite = new Sprite();
        Texture = new ArrayList<Texture>();
        Sprites = new ArrayList<ArrayList<Texture>>();
        load(type,size);

    }

    public void load(String type, int size){
        for(int i = 0; i < size; i ++){
            Texture.add(new Texture(type + i + ".png"));
        }
        Sprites.add(Texture);
    }

    public int Animation(int timer_MAX, int frame_MAX){
        if(timer < timer_MAX){
            timer ++;
            if(timer == timer_MAX){
                if(frame < frame_MAX){
                    frame ++;
                    if(frame == frame_MAX){
                        frame = 0;
                        Menu.change = true;

                    }
                    timer = 0;

                }
            }
        }
        return frame;
    }

    public void update(SpriteBatch batch, int timer_MAX, int frame_MAX){
        Animation(timer_MAX, frame_MAX);
        sprite.set(new Sprite(Sprites.get(0).get(frame)));
        render(batch);
    }

    public void render(SpriteBatch batch){
        sprite.setPosition(0,0);
        sprite.draw(batch);
    }


}
