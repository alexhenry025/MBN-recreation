package com.mygdx.game;

import java.awt.*;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bricks{
    // 2d list with corrisponding brick location
    private static int x,y, width, height;
    public int [][] brickList= new int[][] {{4,4,4,4,4,4,4,4,4,4,4,4,4,4}, {7,7,7,7,7,7,7,7,7,7,7,7,7,7}, {8,8,8,8,8,8,8,8,8,8,8,8,8,8}, {1,1,1,1,1,1,1,1,1,1,1,1,1,1}, {6,6,6,6,6,6,6,6,6,6,6,6,6,6}, {3,3,3,3,3,3,3,3,3,3,3,3,3,3}};
    public static ArrayList<Point> getloc = new ArrayList<Point>(); // this list will get the sprite location
    public static ArrayList<Sprite> bricks = new ArrayList<Sprite>(); // an object list that consists of all the sprites
    public static ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
    public static int [] bricktype = new int[] {1,2,3,4,5,6,7,8}; // this will determine the type of brick
    Ball ball;
    private static int dx =-1, dy = -2;
    static Sprite brick;
    public ArrayList<Texture> load = new ArrayList<Texture>(); // an object list (texture) that will store all the textures for the sprites

    public Bricks(){
        //loading all textures to the object array list
        load.add(new Texture("bluebrick.png"));
        load.add(new Texture("darkbluebrick.png"));
        load.add(new Texture("greenbrick.png"));
        load.add(new Texture("greybrick.png"));
        load.add(new Texture("orangebrick.png"));
        load.add(new Texture("pinkbrick.png"));
        load.add(new Texture("redbrick.png"));
        load.add(new Texture("yellowbrick.png"));
        for (int i = 0; i < 8; i++){
            bricks.add(new Sprite(load.get(i)));// creates an object array sprite list
        }
        this.x = 10;
        this.y = 710;
        this.width = 42;
        this.height = 20;
        getloc = get_pos();

    }

    public ArrayList get_pos(){
        //Due to all the sprites being the same width and height this will take the width and height of the first sprite
        int width = (int) bricks.get(0).getWidth();
        int height = (int) bricks.get(0).getHeight();
        ArrayList<Point> Pos = new ArrayList<Point>(); // make a position array list that will use the 2d bricklist
        int x = 10; // starting x pos
        int y = 710; // starting y pos
        // makes all the brick locations according to the 2d list
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 14; j++) {
                Point position = new Point((x + j * (width+2)), (y - i * (height+2 )));
                Pos.add(position);
            }
        }

        return Pos; // returns the list
    }

    // renders the sprties
    private void render(SpriteBatch batch, int type){
        for(int i = 0; i < 8; i ++){
            if(type == bricktype[i]){
                bricks.get(i).draw(batch);
            }
        }

    }

    // updates the sprites to make the multiple bricks
    public void update(SpriteBatch batch){
        int counter = 0;
        // makes the bricks
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 13; j++) {
                int brickType = brickList[i][j]; // brick type is extracted from the 2d list
                Point point = getloc.get(counter); // gets the location
                counter += 1;
                for (int k = 0; k < 8; k++) {
                    if (brickType == bricktype[k]) {
                        bricks.get(k).setPosition(point.x, point.y);
                    }
                }

                for(int l = 0; l < 6; l ++){
                    for (int h = 0; h < 13; h ++){
                        if(Ball.ball.getBoundingRectangle().overlaps(bricks.get(h).getBoundingRectangle())){
                            ball.dy = dy;
                            bricks.get(h).setPosition(200,200);
                            this.render(batch,brickType);
                        }
                    }
                }
                this.render(batch, brickType);
            }
        }
    }

    public static ArrayList<Rectangle> getRect(){
        return rects;
    }
}
