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

    void Player_Keys(){// simple inputs for the player ,allowing the player to move in 8 different directions
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.UP) )){
            moves1 = NE;
            player.getBody().applyLinearImpulse(new Vector2(15, 15), player.getBody().getWorldCenter(), true);
            animation = true;

        }

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.DOWN) )){
            moves1 = SE;
            player.getBody().applyLinearImpulse(new Vector2(15, -15), player.getBody().getWorldCenter(), true);
            animation = true;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.DOWN) )){
            moves1 = SW;
            player.getBody().applyLinearImpulse(new Vector2(-15, -15), player.getBody().getWorldCenter(), true);
            animation = true;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.UP) )){
            moves1 = NW;
            player.getBody().applyLinearImpulse(new Vector2(-15, 15), player.getBody().getWorldCenter(), true);
            animation = true;
        }

        else  if (Gdx.input.isKeyPressed(Input.Keys.UP)) {// moves the player up
            moves1 = UP;
            player.getBody().applyLinearImpulse(new Vector2(0, 15), player.getBody().getWorldCenter(), true);
            animation = true;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moves1 = Down;
            player.getBody().applyLinearImpulse(new Vector2(0, -15), player.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moves1 = Left;
            player.getBody().applyLinearImpulse(new Vector2(-15, 0), player.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moves1 = Right;
            player.getBody().applyLinearImpulse(new Vector2(15, 0), player.getBody().getWorldCenter(), true);
            animation = true;
        }

        else {
            player.getBody().applyLinearImpulse(new Vector2(player.getBody().getLinearVelocity().x * -1, player.getBody().getLinearVelocity().y * -1), player.getBody().getWorldCenter(), true);
            animation = false;
            player.frames = 0;
        }
    }
}
