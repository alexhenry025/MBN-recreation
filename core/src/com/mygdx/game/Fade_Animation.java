package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


class Fade_Animation {
    private static Texture texture = new Texture("Assets/Fade.png");
    private static Sprite sprite = new Sprite(texture);
    static float alpha = 0;
    static boolean fade_out;

    static void Fadeout(SpriteBatch batch) {
        if (alpha < 1) {
            for (int i = 0; i < 50; i++) {
                alpha += 0.0005;
                if (alpha >= 1) {
                    alpha = 1;
                }
            }
        }
        render(batch);
    }

    static void Fadeout(SpriteBatch batch, String level) { // overloading the function to take in level as a parameter

        switch ((int) alpha) {
            case 0:
                for (int i = 0; i < 50; i++) {
                    alpha += 0.0005;
                }
                break;
            case 1:
                alpha = 1;
                Main.Game = level;
                Main.player.MoveBody(96, 58);

            default:
                break;
        }
        render(batch);
    }

    static void Fadein(SpriteBatch batch) { // fade in function
        for (int i = 0; i < 40; i++) {
            alpha -= 0.0005;
            if (alpha <= 0) {
                alpha = 0;
            }
        }

        render(batch);
    }

    static void render(SpriteBatch batch) {
        sprite.setPosition(0, 0);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.draw(batch, alpha);
    }
}
