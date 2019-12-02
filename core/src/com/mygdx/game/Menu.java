package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu {
    public static boolean change = false, create = true;

    private Intro_Animations introAnimations;

    private String game;

    public Menu(){
        game = Main.Game;
    }

    public void render(SpriteBatch batch){
        if(game.equals("Intro_1")){ //starting the game off with the capcom introAnimations
            if(create){
                introAnimations = new Intro_Animations("Assets/Menu Intro/Capcom/Capcom",31);
                create = false;
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            introAnimations.update(batch,2,31);
            batch.end();
            if(change){
                game = "Intro_2";
                create = true;
            }
        }

        if(game.equals("Intro_2")){ // once the capcom introAnimations is finished loop title screen
            if(create){
                introAnimations = new Intro_Animations("Assets/Menu Intro/Intro/Intro",4);
                create = false;
            }
            //menu.m.play();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            introAnimations.update(batch,15,4);
            batch.end();
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){ // if user presses the enter button
//                menu.s.play(); // add this feature in later and make it so the animation is slower

                Main.Game = "level1";
                Main.p.MoveBody(90, 60); // move lan
            }
        }
    }

}
