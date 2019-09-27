// Bouncing object around the screen
package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L7ABouncingSprites extends ApplicationAdapter {
	SpriteBatch batch;

	Sprite pinky;
	int vxP = 5;	// pinky speed
	
	Sprite babyPinky;
	int vxBP = 4;	// baby pinky speed
	int vyBP = 4;
	
	Sprite flappy;
	int vyF = 3;	// flappy speed
	
	Sprite[] sprites; 

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		pinky = new Sprite(new Texture("Pinky.png"));
		pinky.setPosition(0, 300);
		
		flappy = new Sprite(new Texture("flappy.png"));
		flappy.setPosition(300, 300);
		flappy.setSize(128,128);
				
		babyPinky = new Sprite(new Texture("Pinky.png"));
		babyPinky.setSize(64,64);
		babyPinky.setPosition(20, 200);
		
		// display a set of lollipops in a grid ("bricks" for use in a future brick breaker game)
		int offset = 25;
		Texture img = new Texture("lollipop_pink.png");	// create new Texture (image)
		int y = Gdx.graphics.getHeight()-img.getHeight();	// start the "bricks" near the top of the screen
		sprites = new Sprite[30]; 	// 30 sprites
		
		for(int i = 0; i < 30; i++) {
			// if there are ten in the row, move down a row
			if(i%10==0 ) {
				y-=65;
				offset = 25; // reset to start
			}
			
			sprites[i] = new Sprite(img);
			sprites[i].setPosition(offset, y);
			offset+=75;	
		}
		
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// move sprites
		pinky.translateX(vxP);  // pinky moves horizontally
		flappy.translateY(vyF); // flappy moves vertically
		babyPinky.translate(vxBP, vyBP);

		// bouncing code
		// pinky bounces horizontally
		if (pinky.getX() > Gdx.graphics.getWidth() || pinky.getX() < 0) {
			vxP = vxP * -1;
		}

		// flappy bounces vertically
		if (flappy.getY() > Gdx.graphics.getHeight() || flappy.getY() < 0) {
			vyF = vyF * -1;
		}
		
		// bounce baby pinky around the screen
		if (babyPinky.getX() > Gdx.graphics.getWidth() || babyPinky.getX() < 0) {
			vxBP = vxBP * -1;
		}
		if (babyPinky.getY() > Gdx.graphics.getHeight() || babyPinky.getY() < 0) {
			vyBP = vyBP * -1;
		}

		// draw sprites to screen
		batch.begin();
		// draw "bricks" (lollipops) for future use in a brick breaker game
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].draw(batch);
		}
		pinky.draw(batch);
		flappy.draw(batch);
		babyPinky.draw(batch);
		batch.end();
	}
}
