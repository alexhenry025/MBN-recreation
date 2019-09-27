package com.omstead.gamebasics;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L4DMultipleSpritesWArrayList extends ApplicationAdapter {
	SpriteBatch batch;
	ArrayList<Sprite> sprites; 
	
	@Override
	public void create() {
		// create SpriteBatch
		batch = new SpriteBatch();
		
		sprites = new ArrayList<Sprite>(); 	// 10 sprites
		Texture img = new Texture("lollipop_pink.png");	// create new Texture (image)
		
		for(int i = 0; i < 10; i++) {
			Sprite sprite = new Sprite(img);
			// start sprites off-screen to the right
			int x = (int)(Math.random()*Gdx.graphics.getWidth() + Gdx.graphics.getWidth());
			int y = (int)(Math.random()*Gdx.graphics.getHeight());
			sprite.setPosition(x, y);
			
			sprites.add(sprite);
		}
	}

	@Override
	public void render() {
		// set background colour
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// move sprites on-screen
		for(int i = 0; i < sprites.size(); i++) {
			sprites.get(i).translateX(-5);	
		}
		
		// start drawing sprites to screen
		batch.begin();
		for(int i = 0; i < sprites.size(); i++) {
			sprites.get(i).draw(batch);
		}
		batch.end();
	}
}
