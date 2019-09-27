package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClassTemplateWKeyboard extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	int vx = 0;
	int vy = 3;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("Pinky.png"); // create a Texture (image)
		sprite = new Sprite(img);
		// start sprite in center of screen
		float x = Gdx.graphics.getWidth() / 2;
		float y = Gdx.graphics.getHeight() / 2;
		sprite.setPosition(x, y);
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			sprite.translateY(vy);
		} else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			sprite.translateX(-vx);
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			sprite.translateY(-vy);
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			sprite.translateX(vx);
		} 
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
}
