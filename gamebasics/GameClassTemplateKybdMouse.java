package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClassTemplateKybdMouse extends ApplicationAdapter {
	SpriteBatch batch;
	Texture map;
	Sprite sprite;
	int vx = 0;
	int vy = 3;

	@Override
	public void create() {
		batch = new SpriteBatch();
		map = new Texture("map.png"); // create a Texture (image)
		
		sprite = new Sprite(new Texture("Pinky.png"));
		
		sprite.setPosition(0, 0);
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// KEYBOARD CONTROL
		/*if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			// use translate(vx,vy), translateX(vx) or translateY(vy)
			sprite.translateY(vy);
		} */
		
		
		// MOUSE CONTROL
		/*int x = Gdx.input.getX();
		// (0,0) of screen is lower left of screen
		// mouse co-ords come from upper left
		int y = Gdx.graphics.getHeight()-Gdx.input.getY();
		sprite.setPosition((float)(x - 0.5*sprite.getWidth()), (float)(y - 0.5*sprite.getHeight()));
		*/
		
		// ALL MOVEMENT CODE GOES HERE
		
		// draw sprite
		batch.begin();
		// draw map at x, y = 0,0 - drawing a Texture
		batch.draw(map, 0, 0);
		// drawing a Sprite
		sprite.draw(batch);
		batch.end();
	}
}
