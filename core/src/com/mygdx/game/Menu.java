package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Menu {
    private Texture background;
    Music m;
    Sound s;
    static Sprite C_menu, I_menu,L_menu;
    static ArrayList<Texture> C_t, I_t;
    private static ArrayList<ArrayList<Texture>> capcom_Sprites = new ArrayList<ArrayList<Texture>>();
    private static ArrayList<ArrayList<Texture>> Intro_Sprites = new ArrayList<ArrayList<Texture>>();
    static int C_frame = 0, timer = 0, C_timer = 0, I_frame = 0 , L_frame = 0;
    private static ArrayList<ArrayList<Texture>> Loading_Sprites = new ArrayList<ArrayList<Texture>>();
    static boolean change = false;

    public Menu(){
        C_menu = new Sprite();
        I_menu = new Sprite();
        L_menu = new Sprite();
        Capcom_load();
        Intro_load();
        Intro_loading();
        m = Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/Title.mp3"));
        s = Gdx.audio.newSound(Gdx.files.internal("Assets/Sound/Start_SoundEffect.mp3"));

    }

    public void Capcom_load(){
        C_t = new ArrayList<Texture>();
        for(int k = 0; k < 31; k ++){
            C_t.add(new Texture("Assets/Menu Intro/Capcom/Capcom" + k + ".png"));
        }
        capcom_Sprites.add(C_t);
    }

    public void Intro_load(){
        C_t = new ArrayList<Texture>();
        for(int k = 0; k < 4; k ++){
            C_t.add(new Texture("Assets/Menu Intro/Intro/Intro" + k + ".png"));
        }
        Intro_Sprites.add(C_t);
    }


    public void Intro_loading(){
        C_t = new ArrayList<Texture>();
        for(int k = 0; k < 32; k ++){
            C_t.add(new Texture("Assets/Menu Intro/loading/loading" + k + ".png"));
        }
        Loading_Sprites.add(C_t);
    }


    public int C_frame(){
        if(C_timer < 2){
            C_timer ++;
            if(C_timer == 2){
                if(C_frame < 31){
                    C_frame ++;
                    if(C_frame == 31){
                        C_frame = 0;
                        change = true;

                    }
                    C_timer = 0;

                }
            }
        }
        return C_frame;
    }

    public int I_frame(){
        if(timer < 15){
            timer++;
            if(timer == 15){
                I_frame += 1;
                if(I_frame == 4){
                    I_frame = 0;
                }
                timer = 0;
            }
        }
        return I_frame;
    }

    public int L_frame(){
        if(C_timer < 2){
            C_timer ++;
            if(C_timer == 2){
                if(L_frame < 31){
                    L_frame ++;
                    if(L_frame == 31){
                        L_frame = 0;
                        change = true;

                    }
                    C_timer = 0;

                }
            }
        }
        return L_frame;
    }

    public void render(SpriteBatch batch){

        if(Main.C_animation){
            C_menu.setPosition(0,0);
            C_menu.draw(batch);
        }

        if (Main.I_animation){
            I_menu.setPosition(0,0);
            I_menu.draw(batch);
        }

        if (Main.L_animation){
            L_menu.setPosition(0,0);
            L_menu.draw(batch);

        }
    }
    public void update(SpriteBatch batch, int x, int y){

        if(Main.C_animation) {
            C_frame();
            C_menu.set(new Sprite(capcom_Sprites.get(0).get(C_frame)));
            render(batch);
        }
        if (Main.I_animation){
            I_frame();
            I_menu.set(new Sprite(Intro_Sprites.get(0).get(I_frame)));
            render(batch);
        }
        if (Main.L_animation){
            L_frame();
            L_menu.set(new Sprite(Loading_Sprites.get(0).get(L_frame)));
            render(batch);
        }
    }
}
