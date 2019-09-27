package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L4CMultipleSpritesWOffset extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite[] sprites; 
	int vx = -3;
	
	@Override
	public void create() {
		// create SpriteBatch
		batch = new SpriteBatch();
		
		int offset = 25;
		sprites = new Sprite[10]; 	// 10 sprites
		Texture img = new Texture("lollipop_pink.png");	// create new Texture (image)
		for(int i = 0; i < 10; i++) {
			sprites[i] = new Sprite(img);
			sprites[i].setPosition(offset, 525);
			offset+=75;
		}
	}

	@Override
	public void render() {
		// set background colour
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// update position on-screen to make them move R->L
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].translateX(vx);
		}
		
		// start drawing sprites to screen
		batch.begin();
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(batch);
		}
		batch.end();
		
	}
}
