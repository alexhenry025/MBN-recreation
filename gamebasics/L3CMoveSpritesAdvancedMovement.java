package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L3CMoveSpritesAdvancedMovement extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	int x, y = 0;
	float a = 0.0f;
	float r;

	@Override
	public void create() {
		batch = new SpriteBatch();
		x = 300;
		y = 300;
		img = new Texture("lollipop_pink.png"); // create a Texture (image)
		sprite = new Sprite(img); // create a Sprite from your Texture
		sprite.setPosition(x, y); // set initial position
		r = sprite.getWidth();
		System.out.println(Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		double offsetX = (Math.cos(a) * r);
		double offsetY = (Math.sin(a) * r);
		a = a + 0.1f;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.setPosition(x + (float) offsetX, y + (float) offsetY);
		sprite.draw(batch);
		batch.end();
	}
}
