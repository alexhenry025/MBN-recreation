/* http://www.wesnoth.org/jetrel/Unrelated/ck/ */
package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L12CJumpingOnGroundWTimer extends Game {
	SpriteBatch batch;
	Texture img;	//temporarily holds an image	
	Sprite sprite;
	int vx = 0;
	float vy = 0;
	
	Texture back;
	Sprite background;
	int backX = 0;
	int backY = 0;
	
	Texture highRock;
	Texture lowRock;
	Sprite[] rocks;
	int numRocks = 20;
	long timeLapse, previousTime;
	
	int scrnWidth;
	int scrnHeight;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		scrnWidth = Gdx.graphics.getWidth();
		scrnHeight = Gdx.graphics.getHeight();
		
		img = new Texture("flappy.png"); // create a Texture (image)
		sprite = new Sprite(img);
		//resize the image - it's too large
		sprite.setScale((float).2, (float).2);
		
		// set up the background
		back = new Texture("background.png");
		background = new Sprite(back);
					
		vx = 0;
		vy = 0;
		sprite.setPosition(vx, vy);
		previousTime = System.currentTimeMillis();
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.printf("(%f, %f, %f)\n", sprite.getX(), sprite.getY(), vy);
		
		// movement code
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && sprite.getY() <= 50) {
			vy=10;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			// far enough back from left side that sprite can move left
			if (sprite.getX() > 0) {
				vx = -5;
			} 
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (sprite.getX() < Gdx.graphics.getWidth()) {
				vx = 5;
			} 
		} else {
			vx = 0;
		}
		
		// moves sprite
		sprite.translate(vx, vy); 
		
		
		timeLapse += System.currentTimeMillis() - previousTime;
		
		// only modify the change in vy every half second
		if(timeLapse > 500) {
				vy-=1;	// eventually vy becomes -ive and bird drops
				timeLapse -= 500;
		}
		
		// Solution to exercise #2
		// stop bird on artificial "ground"
		if(sprite.getY() < 50) {
			sprite.setY(50);
			vy = 0;
		}
		
		batch.begin();
		background.draw(batch);
		sprite.draw(batch);
		batch.end();
	}
}
