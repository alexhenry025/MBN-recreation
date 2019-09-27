// Collision Detection - bullets fired at target
package com.omstead.gamebasics;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class L11CollisionDetectionWProjectiles extends ApplicationAdapter {
	SpriteBatch batch;

	Texture blackBox;
	Texture lollipop;
	Sprite black;
	Sprite lolli;
	Texture img;
	Sprite sprite;
		 
	int vyBlk = 3;	// black box speed

	int lolliVX = 5;

	ArrayList<Sprite> lollis = new ArrayList<Sprite>();

	@Override
	public void create() {
		batch = new SpriteBatch();

		// load the image at the start before the game begins
		blackBox = new Texture("BoxBlack.png");
		lollipop = new Texture("lollipop_pink.png");

		black = new Sprite(blackBox);
		black.setPosition(200, 300);

		img = new Texture("Pinky.png"); // create a Texture (image)
		sprite = new Sprite(img);
		// start sprite in center of screen
		int y = Gdx.graphics.getHeight() / 2;
		sprite.setPosition(0, y);

		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// sprite moves vertically with mouse
		int y = Gdx.graphics.getHeight()-Gdx.input.getY();
		if(y < Gdx.graphics.getHeight()) {
			sprite.setPosition(0, y);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			fire();
		}

		System.out.println("lollis size:  " + lollis.size());
		// move lollipops
		for (int i = lollis.size() - 1; i >=0; i--) {
			lollis.get(i).translateX(lolliVX);
			if (lollis.get(i).getX() > Gdx.graphics.getWidth()) {
				lollis.remove(i);
			} 
	
		}

		// move boxes
		black.translateY(vyBlk); // black moves horizontally
		
		Rectangle blackRect = black.getBoundingRectangle();
		for (int i = lollis.size()-1; i >=0; i--) {
			Rectangle lolliRect = lollis.get(i).getBoundingRectangle();

			if (blackRect.overlaps(lolliRect)) {
				System.out.println("hit!");
				lollis.remove(i);
			}
		}

		// bouncing code
		// if the blackBox reaches the right edge, make it bounce
		if (black.getY() > Gdx.graphics.getHeight() || black.getY() < 0) {
			vyBlk = vyBlk * -1;
		}
	
		// draw sprites to screen
		batch.begin();
		black.draw(batch);
		sprite.draw(batch);
		// draw lollipops to the screen
		for (int i = 0; i < lollis.size(); i++) {
			lollis.get(i).draw(batch);
		}
		batch.end();

	}

	private void fire() {
		System.out.println("firing...");
		Sprite lolli = new Sprite(lollipop);
		// projectile starts at sprite
		lolli.setPosition(sprite.getX() + 45, sprite.getY() + 25);
		lollis.add(lolli);

	}

}
