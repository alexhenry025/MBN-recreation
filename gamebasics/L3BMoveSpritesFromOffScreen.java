package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L3BMoveSpritesFromOffScreen extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	float x;
	float y;
	int vx = 5;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("lollipop_pink.png"); // create a Texture (image)
		sprite = new Sprite(img);
		// start sprite off-screen
		x = (float)(Math.random()*Gdx.graphics.getWidth()) + Gdx.graphics.getWidth();
		// keep this within the height of the screen
		y = (float)Math.random()*Gdx.graphics.getHeight();
		sprite.setPosition(x, y);
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
		System.out.println("Sprite start (x,y)=(" + x + "," + y + ")");

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// move the sprite left
		sprite.translateX(-vx);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
}
