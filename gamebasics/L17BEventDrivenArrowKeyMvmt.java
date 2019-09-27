package com.omstead.gamebasics;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L17BEventDrivenArrowKeyMvmt extends ApplicationAdapter implements InputProcessor {

	Texture playerImg;
	Sprite player;
	int vx = 0;
	int vy = 0;

	Texture redTarget;
	Texture yellowTarget;
	Texture explosion;
	Sprite red;
	Sprite yellow;
	Sprite expl;

	Texture winScreen;
	Sprite win;

	SpriteBatch batch;
	ArrayList<Sprite> targets;

	public void create() {
		
		batch = new SpriteBatch();
		yellowTarget = new Texture(Gdx.files.internal("yellowTarget.png"));
		redTarget = new Texture(Gdx.files.internal("redTarget.png"));

		targets = new ArrayList<Sprite>();

		for (int i = 0; i < 5; i++) {

			Sprite target = new Sprite(yellowTarget);
			target.setPosition((int) (Math.random() * Gdx.graphics.getWidth()),
					(int) (Math.random() * (Gdx.graphics.getHeight() - yellowTarget.getHeight())));
			targets.add(target);
		}

		playerImg = new Texture(Gdx.files.internal("player.png"));
		player = new Sprite(playerImg);
		player.setPosition(50, 50);
		
		Gdx.input.setInputProcessor(this);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// move the player, only if the player is still within screen bounds
		if (player.getX() + vx < Gdx.graphics.getWidth() && player.getX() + vx > 0) {
			player.translateX(vx);
		}
		if (player.getY() + vy < Gdx.graphics.getHeight() && player.getY() + vy > 0) {
			player.translateY(vy);
		}

		batch.begin();
		player.draw(batch);
		for (int i = 0; i < targets.size(); i++) {
			targets.get(i).draw(batch);
		}
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
