package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class L12BCoordinatingMvmtWithTimer extends Game {
	SpriteBatch batch;
	Texture img;	//temporarily holds an image	
	Sprite sprite;
	int vx = 5;
	int vy = 0;  //only horizontal movement allowed
	
	Texture lollipop;
	Sprite[] lollis;
	int numLollis = 20;
	
	long timeLapse, previousTime;
	
	
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		img = new Texture("Pinky.png"); // create a Texture (image)
		sprite = new Sprite(img);
		sprite.setPosition(vx, vy);
		
		// add the enemies
		lollipop = new Texture("lollipop_pink.png");
		lollis = new Sprite[numLollis];
		int offset = 25;
		for(int i = 0; i < numLollis; i++) {
				lollis[i] = new Sprite(lollipop);
				//resize the image - it's too large
				// Do NOT use this when you must do collision detection with an object
				// Instead, resize the image in a graphics editor to ensure it's the proper size ahead of time 
				lollis[i].setPosition(offset, Gdx.graphics.getHeight()-lollipop.getHeight());
				offset+=50;
		}
		
		previousTime = System.currentTimeMillis();
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// movement code
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			vy=10;
		}
		
		timeLapse += System.currentTimeMillis() - previousTime;
		previousTime = System.currentTimeMillis();
		// only modify the change in vy every second
		if(timeLapse > 1000) {
	
			/* if the previous render frame was under 1000 and the next one is a bit over 1000
			 * you don't want to lose time (e.g. 1st frame - 970ms; 2nd 1030ms - subtract
			 * 1000 to ensure that the next frame is not getting eating into the next frame's
			 * time, instead of just resetting to 0 */			
			timeLapse -= 1000;
			// move rocks left
			for(int i = 0; i < lollis.length; i++){
				lollis[i].translate(-64,0);	// -64 is based on the width of the image
			}
		}
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			// use translate(vx,vy), translateX(vx) or translateY(vy)
			sprite.translateX(vx*-1);
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			sprite.translateX(vx);
		}
		
		batch.begin();
		sprite.draw(batch);
		for(int i = 0; i < lollis.length; i++){
			lollis[i].draw(batch);		
		}
		batch.end();
	}
}
