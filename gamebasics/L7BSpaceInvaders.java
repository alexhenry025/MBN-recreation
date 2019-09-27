// saucer.png is from appinventor.mit.edu
// 
package com.omstead.gamebasics;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L7BSpaceInvaders extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ArrayList<Sprite> invaders = new ArrayList<Sprite>(); 
	int invVX = 5;
	Sprite plyr;
	int vx = 5;
	
	Texture projectile; 
	int lolliVY =5;
	ArrayList<Sprite> lollis = new ArrayList<Sprite>();
	
	@Override
	public void create() {
		// create SpriteBatch
		batch = new SpriteBatch();
		
		projectile = new Texture("lollipop_pink.png");
		
		int offset = 25;
	
		img = new Texture("invader.png");	// create new Texture (image)
		for(int i = 0; i < 3; i++) {
			Sprite invader = new Sprite(img);
			invader.setPosition(offset, Gdx.graphics.getHeight()-img.getHeight());
			invaders.add(invader);
			offset+=75;
		}
		
		plyr = new Sprite(new Texture("saucer.png"));
		plyr.setPosition(Gdx.graphics.getWidth()/2, 20);
		
	}

	@Override
	public void render() {
		// set background colour
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// move sprites on-screen
		for(int i = 0; i < invaders.size(); i++) {
			invaders.get(i).translateX(invVX);
			// bounce invaders off edges of screen
			if(invaders.get(i).getX() > Gdx.graphics.getWidth() || invaders.get(i).getX() < 0) {
				invVX*=-1;
				// shift all invaders down
				for(int j = 0; j < invaders.size(); j++) {
					// move invaders down by the height of an invader
					invaders.get(j).translateY(-img.getHeight());
				}
			}
		}
		
		// move lollipops up the screen
		for(int i = 0; i < lollis.size(); i++) {
			lollis.get(i).translateY(lolliVY);
		}
		
		// keyboard control of plyr
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			plyr.translateX(vx*-1);
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			plyr.translateX(vx);
		} else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			fire();
		}
		
		// start drawing sprites to screen
		batch.begin();
		for(int i = 0; i < invaders.size(); i++) {
			invaders.get(i).draw(batch);
		}
		for(int i = 0; i < lollis.size(); i++) {
			lollis.get(i).draw(batch);
		}
		plyr.draw(batch);
		batch.end();
	}
	
	private void fire() {	
		Sprite lollipop = new Sprite(projectile);
		// projectile starts at sprite
		lollipop.setPosition(plyr.getX() + 45, plyr.getY() + 25);
		lollis.add(lollipop);

	}
}
