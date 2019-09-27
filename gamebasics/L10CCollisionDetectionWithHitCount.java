// Collision Detection - bullets fired at target
package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class L10CCollisionDetectionWithHitCount extends ApplicationAdapter {
	SpriteBatch batch;
	
	Texture blackBox;
	Texture blueBox;
	Sprite black;
	Sprite blue;

	String flag = "black";
	boolean hit = false;
	int hits = 0;

	int vxBl = 5; // blue box speed
	int vyBl = 5;

	int vxBlk = 3; // black box speed
	int vyBlk = 3;

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		// load the image at the start before the game begins
		blackBox = new Texture(Gdx.files.internal("BoxBlack.png"));
		blueBox = new Texture(Gdx.files.internal("BoxBlue.png"));

		black = new Sprite(blackBox);
		blue = new Sprite(blueBox);

		black.setPosition(0, 300);
		blue.setPosition(300, 300);
		
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// add mouse control
		// int x = Gdx.input.getX();
		// int y = Gdx.graphics.getHeight()-Gdx.input.getY();

		// move boxes
		black.translateX(vxBlk); // black moves horizontally
		blue.translateY(vyBl); // blue moves vertically

		Rectangle blackRectl = black.getBoundingRectangle();
		Rectangle blueRectl = blue.getBoundingRectangle();
			
		if (blackRectl.overlaps(blueRectl)) {

			// if they weren't hitting before, turn hitting to true
			if (!hit) {
				hit = true;
				hits++;
				System.out.println("hits: " + hits);
			}
		// if they're not hitting...
		} else {
			hit = false;
		}


		// bouncing code
		// if the blackBox reaches the right edge, make it bounce
		if (black.getX() > Gdx.graphics.getWidth() || black.getX() < 0) {
			vxBlk = vxBlk * -1;
		}

		if (blue.getY() > Gdx.graphics.getHeight() || blue.getY() < 0) {
			vyBl = vyBl * -1;
		}

		// draw sprites to screen
		batch.begin();
		black.draw(batch);
		blue.draw(batch);
		batch.end();

	}


}
