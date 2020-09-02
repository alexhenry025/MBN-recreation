/*
 * This is a class for keyboard input
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import static com.mygdx.game.Main.*;

 class Keyboard_Input {
    private Vector2 velocity = new Vector2();
    float speed = 60;
    void Player_Keys(){// simple inputs for the player ,allowing the player to move in 8 different directions
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.UP) ) && !Levels.test){
            moves1 = NE;
            player.body.setLinearVelocity(20,20);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                player.body.setLinearVelocity(40,40);
            }
            animation = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.DOWN) )&& !Levels.test){
            moves1 = SE;
            player.getBody().applyLinearImpulse(new Vector2(0.90f, -0.90f), player.getBody().getWorldCenter(), true);
            player.body.setLinearVelocity(20,-20);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                player.body.setLinearVelocity(40,-40);
            }
            animation = true;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.DOWN) )&& !Levels.test){
            moves1 = SW;
            player.body.setLinearVelocity(-20,-20);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                player.body.setLinearVelocity(-40,-40);
            }
            animation = true;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.UP) )&& !Levels.test){
            moves1 = NW;
            player.body.setLinearVelocity(-20,20);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                player.body.setLinearVelocity(-40,40);
            }
            animation = true;
        }

        else  if (Gdx.input.isKeyPressed(Input.Keys.UP)&& !Levels.test) {// moves the player up
            moves1 = UP;
            player.body.setLinearVelocity(0,20);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                player.body.setLinearVelocity(0,40);
            }
            animation = true;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&& !Levels.test) {
            moves1 = Down;
            player.body.setLinearVelocity(0,-20);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                player.body.setLinearVelocity(0,-40);
            }
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& !Levels.test) {
            moves1 = Left;
            player.body.setLinearVelocity(-20,0);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)&& !Levels.test){
                player.body.setLinearVelocity(-40,0);
            }
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& !Levels.test) {
            moves1 = Right;
            player.body.setLinearVelocity(20,0);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)&& !Levels.test){
                player.body.setLinearVelocity(40,0);
            }
            animation = true;
        }

        else {
            player.body.setLinearVelocity(0,0);
            animation = false;
            player.frames = 0;
        }
    }
}
