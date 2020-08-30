package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.*;
import java.util.ArrayList;

public class Display {
    static SpriteBatch batch;
    Sprite b ;
    private ArrayList<Texture> Texture;
    private ArrayList<ArrayList<Texture>> Sprites;


    public Display(){
        b= new Sprite();
        batch = new SpriteBatch();
        Texture = new ArrayList<Texture>();
        Sprites = new ArrayList<ArrayList<Texture>>();
        Texture.add(new Texture("Assets/Miscellaneous/box.png"));
        Sprites.add(Texture);


    }
    public void update(){
        b.set(new Sprite(Sprites.get(0).get(0)));
        render(Main.batch);
    }
    public void render(SpriteBatch batch){
        b.setPosition(Main.p.getX()-35,Main.p.getY()-24);
        //System.out.println("ADSSSSSSSSSSSSSSss");
        b.draw(batch);
    }

}
