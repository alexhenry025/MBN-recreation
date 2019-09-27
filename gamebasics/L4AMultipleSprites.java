package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L4AMultipleSprites extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite[] sprites; 
	
	@Override
	public void create() {
		// create SpriteBatch
		batch = new SpriteBatch();
		
		sprites = new Sprite[10]; 	// 10 sprites
		TextureArray img = new TextureArray("lollipop_pink.png");	// create new Texture (image)
		for(int i = 0; i < 10; i++) {
			sprites[i] = new Sprite(img[i]);
			// start sprites off-screen to the right
			int x = (int)(Math.random()*Gdx.graphics.getWidth() + Gdx.graphics.getWidth());
			int y = (int)(Math.random()*Gdx.graphics.getHeight());
			sprites[i].setPosition(x, y);
			System.out.format("%d, %d", x, y);
		}
	}

	@Override
	public void render() {
		// set background colour
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// move sprites on-screen
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].translateX(-5);	
			// or use a variable for speed: sprites[i].translateX(vx);
		}
		
		// start drawing sprites to screen
		batch.begin();
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(batch);
		}
		batch.end();
	}
}
