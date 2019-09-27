package com.omstead.gamebasics;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L8SideScrollPairProgramSolution extends ApplicationAdapter {
	SpriteBatch batch;
	Texture backgrnd;
	Sprite ground;
	Sprite ground2;

	Texture projectile;
	Texture img;
	Sprite sprite;
	int x = 0;
	int y = 0;
	// for bird's movement
	int vx = 3;
	float vy = 0;
	float gravity;
	boolean jumping = false;

	int lolliVX = 5;

	ArrayList<Sprite> lollis = new ArrayList<Sprite>();

	@Override
	public void create() {
		batch = new SpriteBatch();
		// load the image at the start before the game begins
		projectile = new Texture("lollipop_pink.png");

		backgrnd = new Texture("sideScrollBackground.png");
		ground = new Sprite(backgrnd);
		ground2 = new Sprite(backgrnd);
		ground.setPosition(0, 0);
		ground2.setPosition(ground.getWidth(), 0);

		img = new Texture("Pinky.png"); // create a Texture (image)
		sprite = new Sprite(img);

		x = 100;
		y = 50;
		sprite.setPosition(x, y);
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && !jumping) { // jumping
			jumping = true;
			vy = 2;
			gravity = 2;
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			// far enough back from left side that sprite can move left
			if (sprite.getX() > 100) {
				vx = -5;
			} else {
				// moving left, but sprite is at 100, so need to scroll bckgrnd instead
				vx = 0;
				ground.translateX(5);
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			// far enough back from right side that sprite can move right
			if (sprite.getX() < 400) {
				vx = 5;
			} else {
				// moving right, but sprite is at 400, so need to scroll bckgrnd instead
				vx = 0;
				ground.translateX(-5);
				ground2.translateX(-5);
				// wrap background - NOT WORKING!
				/*if(ground.getX()<=-(ground.getWidth()-Gdx.graphics.getWidth())) {
					ground2.setX(Gdx.graphics.getWidth());
				}
				if(ground2.getX()<=-(ground2.getWidth() - Gdx.graphics.getWidth())) {
					ground.setX(Gdx.graphics.getWidth());
				}*/
				
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) { // shooting
			fire();
		} else {
			vx = 0;
		}
		
		if (jumping) {

			vy = vy + gravity;
			gravity = gravity - .5f;
			if (sprite.getY() < 50) {
				vy = 0;
				jumping = false;
				sprite.setY(50);
			}
			System.out.format("Jumping vy: %f gravity: %f%n", vy, gravity);
		}
		sprite.translate(vx, vy);

		// move lollipops
		for (int i = 0; i < lollis.size(); i++) {
			lollis.get(i).translateX(lolliVX);
		}

		batch.begin();
		ground.draw(batch);
		ground2.draw(batch);
		sprite.draw(batch);
		// draw lollipops to the screen
		for (int i = 0; i < lollis.size(); i++) {
			lollis.get(i).draw(batch);
		}
		batch.end();
	}

	private void fire() {
		Sprite lollipop = new Sprite(projectile);
		// projectile starts at sprite
		lollipop.setPosition(sprite.getX() + 45, sprite.getY() + 25);
		lollis.add(lollipop);

	}
}
