/*
 * This class is used for the intro animations
 * 2019 - Ghanem & Usman
 * Megaman battle network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Menu {
    static boolean change = false;
    private static boolean create = true;
    private Intro_Animations introAnimations;
    private static Boolean test = false;

    void render(SpriteBatch batch){

        if(Main.Game.equals("Intro_1")){ //starting the game off with the capcom introAnimations
            if(create){
                introAnimations = new Intro_Animations("Assets/Menu Intro/Capcom/Capcom",31);
                create = false;
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            introAnimations.update(batch,2,31);
            batch.end();
            if(change){
                Main.Game = "Intro_2";
                create = true;
            }
        }

        if(Main.Game.equals("Intro_2")){ // once the capcom introAnimations is finished loop title screen
            if(create){
                introAnimations = new Intro_Animations("Assets/Menu Intro/Intro/Intro",4);
                create = false;
            }
            //menu.m.play();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            introAnimations.update(batch,15,4);
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){ // if user presses the enter button
                //menu.s.play(); // add this feature in later and make it so the animation is slower
                test = true;
            }
            if (test) {
                Fade_Animation.Fade(batch, "level1");
            }
            batch.end();
        }
    }
}
