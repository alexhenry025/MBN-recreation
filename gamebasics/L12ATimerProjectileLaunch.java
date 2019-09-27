/* fix mouse movement
 * multiple sprites shot each time
 * background colour
 */
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

public class L12ATimerProjectileLaunch extends ApplicationAdapter {
	SpriteBatch batch;

	Texture blackBox;
	Texture lollipop;
	Sprite black;
	Sprite lolli;
	Texture img;
	Sprite sprite;

	long timeLapse, previousTime;

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
		
		previousTime = System.currentTimeMillis();

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(255, 25, 20, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// timer stuff
		timeLapse += System.currentTimeMillis() - previousTime;
		previousTime = System.currentTimeMillis();

		// sprite moves vertically with mouse
		int y = (int) (Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight() / 2);
		sprite.setPosition(0, y);
		// sprite is bound by upper/lower screen limits
		if (y + sprite.getHeight() > Gdx.graphics.getHeight()) {
			sprite.setPosition(0, Gdx.graphics.getHeight() - sprite.getHeight());
		} else if (y < 0)
			sprite.setPosition(0, 0);

		// fire bullet with space bar press
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			fire();
		}

		// move lollipops
		for (int i = 0; i < lollis.size(); i++) {
			lollis.get(i).translateX(lolliVX);
			if (lollis.get(i).getX() > Gdx.graphics.getWidth()) {
				lollis.remove(i);
			}

		}

		// check for collisions
		for (int i = 0; i < lollis.size(); i++) {
			Rectangle blackRectl = black.getBoundingRectangle();
			Rectangle lolliRect = lollis.get(i).getBoundingRectangle();

			if (blackRectl.overlaps(lolliRect)) {
				System.out.println("hit!");
				lollis.remove(i);
			}
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

	// launch a projectile
	private void fire() {
		// make sure reload is possible - don't fire if not enough time has
		// elapsed
		if (timeLapse > 1000) {
			// force a wait of 1 second between bullet fires by resetting to 0
			timeLapse = 0;

			// create a new projectile and add it to the ArrayList
			Sprite lolli = new Sprite(lollipop);
			lolli.setScale(.5f, .5f);
			// projectile starts at sprite
			lolli.setPosition(sprite.getX() + 45, sprite.getY() + 25);
			lollis.add(lolli);
		}

	}

}
