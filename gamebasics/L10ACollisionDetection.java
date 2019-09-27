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

public class L10ACollisionDetection extends ApplicationAdapter {
	SpriteBatch batch;

	Texture blackBox;
	Texture blueBox;
	Texture greyBox;
	Sprite black;
	Sprite blue;

	String flag = "black";

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
		greyBox = new Texture(Gdx.files.internal("BoxGrey.png"));

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

		// move boxes
		black.translateX(vxBlk); // black moves horizontally
		blue.translateY(vyBl); // blue moves vertically

		// bouncing code
		// if the blackBox reaches the right edge, make it bounce
		if (black.getX() > Gdx.graphics.getWidth() || black.getX() < 0) {
			vxBlk = vxBlk * -1;
		}
		if (blue.getY() > Gdx.graphics.getHeight() || blue.getY() < 0) {
			vyBl = vyBl * -1;
		}
		
		// Create bounding rectangles to check for overlap
		Rectangle blackRect = black.getBoundingRectangle();
		Rectangle blueRect = blue.getBoundingRectangle();
		// check for overlap b/w bounding rectangles
		if (blackRect.overlaps(blueRect)) {
			System.out.println("hit!");
			if (flag == "black") {
				black.setTexture(greyBox);
				flag = "grey";
			}
		} else { // not hitting
			if (flag == "grey") { // and box is grey, reset to black
				black.setTexture(blackBox);
				flag = "black";
			}

		}

		// draw sprites to screen
		batch.begin();
		black.draw(batch);
		blue.draw(batch);
		batch.end();
	}

}
