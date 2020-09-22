/*
 * This is the player class that controls everything about the player
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;

class Player {
    private static Sprite Lan;
    static ArrayList<Texture> tmp;
    private static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    public static com.badlogic.gdx.math.Rectangle rectp;
    public static Body body;
    static int frames = 0;
    private int t = 0;
    private int [] open_list = new int [] {8,8,8,8,8,8,8,8};
    public static ShapeRenderer shapeRenderer;
    public static com.badlogic.gdx.math.Rectangle rect;


    Player(){
        Lan = new Sprite();
        Load();
        createbody(categories.Category_PLAYER,categories.Category_NPC,(short)0);

    }
    private void render(SpriteBatch batch){
        Lan.setPosition(96,58);
        batch.draw(Lan,body.getPosition().x - 27 * (float) Math.pow(categories.PPM,2),body.getPosition().y - 15 * (float) Math.pow(categories.PPM, 2), Lan.getWidth() * (float) Math.pow(categories.PPM, 2) * 3.2f, Lan.getHeight() * (float) Math.pow(categories.PPM, 2) * 3.2f);
    }
    private int moveFrames(){ // this is the animation for the movement frames the character
        if(frames < open_list[Main.moves1]){
            if(t < 8) {
                t ++;
                if(t == 8) {
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
    private void Load(){// load method , loads all the players sprites.
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
    void MoveBody(int x, int y){
        body.setTransform(x,y,0);
    }
    private void createbody(short c_bits,short m_bits,short g_index){// create the players body
        Lan.setPosition(96,58);
        rectp = new Rectangle((int) Lan.getX(), (int) Lan.getY(), (int) Lan.getWidth(), (int) Lan.getHeight());//create a recto take players x,y,width and height
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = Main.world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        fdef.shape = shape;
       // shape.setAsBox(20 * (float) Math.pow(categories.PPM, 2), 10 * (float) Math.pow(categories.PPM, 2));
        shape.setAsBox(20 * (float) Math.pow(categories.PPM, 2),10 * (float) Math.pow(categories.PPM, 2),new Vector2(0,0),0);
        this.body.createFixture(fdef).setUserData("Player");
        this.body.getFixtureList().get(0).setUserData("Player");
        this.body.setTransform(rectp.getX(), rectp.getY(), 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits= c_bits;
        fixtureDef.filter.maskBits = m_bits;
        fixtureDef.filter.groupIndex = g_index;

    }

    void update(SpriteBatch batch){// update method which updates the players frame relative to its movement
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

    void setX(float x) {
        Lan.setX(x);
    }

    void setY(float y) {
        Lan.setY(y);
    }

    float getX() {
        return Lan.getX();
    }

    float getY() {
        return Lan.getY();
    }

    Body getBody() {
        return body;
    }
    Rectangle getRect(){
        System.out.println(body.getPosition().y);
        return new Rectangle(body.getPosition().x - 27 * (float) Math.pow(categories.PPM,2),body.getPosition().y  ,20,5);
    }
}


