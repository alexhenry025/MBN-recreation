package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClassTemplateWEventHandling extends ApplicationAdapter implements InputProcessor {

	Sprite lolli;
	Sprite player;
	int vx = 0;
	int vy = 0;
	
	SpriteBatch batch;

	public void create() {
		
		batch = new SpriteBatch();
		lolli = new Sprite(new Texture(Gdx.files.internal("lollipop_pink.png")));
		lolli.setPosition(50, 50);
	
		player = new Sprite(new Texture(Gdx.files.internal("player.png")));
		player.setPosition(50, 50);
		
		Gdx.input.setInputProcessor(this);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// mouse-following code for lolli
		int x = Gdx.input.getX();
		int y = Gdx.graphics.getHeight()-Gdx.input.getY();
		// subtract half width/height from x,y to center the image over the mouse
		lolli.setPosition(x-lolli.getWidth()/2, y-lolli.getHeight()/2);

		// move the player, only if the player is still within screen bounds
		if (player.getX() + vx < Gdx.graphics.getWidth() && player.getX() + vx > 0) {
			player.translateX(vx);
		}
		if (player.getY() + vy < Gdx.graphics.getHeight() && player.getY() + vy > 0) {
			player.translateY(vy);
		}

		batch.begin();
		player.draw(batch);
		lolli.draw(batch);
		batch.end();

	}

	@Override
	public boolean keyDown(int keycode) {
		/*
		 * if you do the player.translateX(vx); in here, but motion is choppy
		 * and you have to keep hitting the arrow key to move right
		 */
		if (keycode == Keys.UP) {
			vy = 5;
		} else if (keycode == Keys.DOWN) {
			vy = -5;
		} else if (keycode == Keys.LEFT) {
			vx = -5;
		} else if (keycode == Keys.RIGHT) {
			vx = 5;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.UP || keycode == Keys.DOWN) {
			vy = 0;
		} else if (keycode == Keys.LEFT || keycode == Keys.RIGHT) {
			vx = 0;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	// Mouse down/touched
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	// Mouse up/touch released
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
