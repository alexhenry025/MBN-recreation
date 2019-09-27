// Collision Detection - bullets fired at target
package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class L17AEventDrivenMouseCollisionDetn extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;

	Texture blackBox;
	Texture blueBox;
	Sprite black;
	Sprite blue;
	
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
		blackBox = new Texture("BoxBlack.png");
		blueBox = new Texture("BoxBlue.png");

		black = new Sprite(blackBox);
		blue = new Sprite(blueBox);

		black.setPosition(0, 300);
		blue.setPosition(300, 300);
		
		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// add mouse following - still in render
		// int x = Gdx.input.getX();
		// int y = Gdx.graphics.getHeight()-Gdx.input.getY();

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

		// draw sprites to screen
		batch.begin();
		black.draw(batch);
		blue.draw(batch);

		batch.end();
	}
	

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		Rectangle rect = black.getBoundingRectangle();
		if(rect.contains(screenX,  Gdx.graphics.getHeight()-screenY)){
			Texture img = new Texture("BoxGrey.png");
			black.setTexture(img);
			System.out.println("Mouse hit black box!");
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
