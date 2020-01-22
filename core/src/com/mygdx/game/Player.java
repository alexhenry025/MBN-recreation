/*
 * This is the player class that controls everything about the player
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int x,y;
    static Sprite Lan;
    static ArrayList<Texture> tmp;
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    com.badlogic.gdx.math.Rectangle rect;
    Body body;
    public static int frames = 0;
    int t = 0;

    Box2DDebugRenderer bdr;

    int [] open_list = new int [] {8,8,8,8,8,8,8,8};

    public Player(){
        Lan = new Sprite();
        Load();
        createbody();
        bdr = new Box2DDebugRenderer();
    }

    public void render(SpriteBatch batch){

        Lan.setPosition(100,100);
        batch.draw(Lan,body.getPosition().x - 25 * (float) Math.pow(Main.PPM,2),body.getPosition().y - 25 * (float) Math.pow(Main.PPM, 2), Lan.getWidth() * (float) Math.pow(Main.PPM, 2) * 3, Lan.getHeight() * (float) Math.pow(Main.PPM, 2) * 3);
//        bdr.render(Main.world,Main.camera.combined);
    }

    public int moveFrames(){ // this is the animation for the movement frames the character
        if(frames < open_list[Main.moves1]){
            if(t < 3) {
                t ++;
                if(t == 3) {
                    frames += 1;
                    if (frames == open_list[Main.moves1]) {
                        frames = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return frames;
    }
    public void Load(){// load method , loads all the players sprites.
        for(int i = 0; i < open_list.length; i ++ ){
            for(String w : new String[]{"Lanup", "Landown", "Lanleft", "Lanright" , "LanNW", "LanSW","LanNE", "LanSE"}){
                tmp = new ArrayList<Texture>();
                for(int k = 0; k < open_list[i]; k ++){
                    tmp.add(new Texture("Assets/Lan walk/" + w + "/" + w + k + ".png"));
                }
                sprites.add(tmp);
            }
        }
    }

    public void MoveBody(int x, int y){
        body.setTransform(x,y,0);
    }


    public void createbody(){// create the players body
        Lan.setPosition(100,100);

        rect = new Rectangle((int) Lan.getX(), (int) Lan.getY(), (int) Lan.getWidth(), (int) Lan.getHeight());//create a recto take players x,y,width and height

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = Main.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.shape = shape;

        shape.setAsBox(30 * (float) Math.pow(Main.PPM, 2), 20 * (float) Math.pow(Main.PPM, 2));

        this.body.createFixture(fdef).setUserData("Player");

        this.body.getFixtureList().get(0).setUserData("Player");

        this.body.setTransform(rect.getX(), rect.getY(), 0);

    }

    public void update(SpriteBatch batch){// update method which updates the players frame relative to its movement
        Lan.setPosition(body.getPosition().x,body.getPosition().y);

        if(Main.animation && Main.moves1 == Main.UP){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.Down){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.Left){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.Right){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.NE){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.SE){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.SW){
            moveFrames();
        }
        else if(Main.animation && Main.moves1 == Main.NW){
            moveFrames();
        }
        else{
            frames = 0;
        }

        Lan.set(new Sprite(sprites.get(Main.moves1).get(frames)));
        this.render(batch);
    }

    public void setX(float x) {
        Lan.setX(x);
    }

    public void setY(float y) {
        Lan.setY(y);
    }

    public float getX() {
        return Lan.getX();
    }

    public float getY() {
        return Lan.getY();
    }

    public Body getBody() {
        return body;
    }

    public Rectangle getRect(){
        return rect;
    }





}


