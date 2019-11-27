package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Loading {
    static Sprite L_menu;
    static ArrayList<Texture> L_t;
    static int L_frame = 0, C_timer=0;
    private static ArrayList<ArrayList<Texture>> Loading_Sprites = new ArrayList<ArrayList<Texture>>();


    public  Loading() {
        Intro_loading();
        L_menu = new Sprite();
    }


    public void Intro_loading() {
        L_t = new ArrayList<Texture>();
        for (int k = 0; k < 32; k++) {
            L_t.add(new Texture("Assets/Menu Intro/loading/loading" + k + ".png"));
        }
        Loading_Sprites.add(L_t);
    }


    public  int L_frame() {

        if (C_timer < 2) {
            C_timer++;
            if (C_timer == 2) {
                if (L_frame < 32) {
                    L_frame++;
                    if (L_frame == 32) {
                        L_frame = 0;
                    }
                    C_timer = 0;

                }
            }
        }

        return L_frame;
    }

    public  void render(SpriteBatch batch) {
        if(Main.L_animation = true) {
            L_menu.setPosition(0, 0);
            //System.out.println("HIIIIIIIIIII");
            L_menu.draw(batch);
        }

    }

    public void update(SpriteBatch batch, int x, int y) {
        if (Main.L_animation = true) {
            L_frame();
            System.out.println(L_frame);
            L_menu.set(new Sprite(Loading_Sprites.get(0).get(L_frame)));
            render(batch);
        }



    }
}
