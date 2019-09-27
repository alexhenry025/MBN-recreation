package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L7CMapMovement extends ApplicationAdapter {
	SpriteBatch batch;
	
	Sprite red;
	Sprite blue;
	Sprite green;
	Sprite white;
	Sprite yellow;
	
	Texture img;
	Sprite sprite;
	Sprite map;
	int vx = 1;
	int vy = 1;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		sprite = new Sprite(new Texture("player.png"));
		map = new Sprite(new Texture("map.png"));
		map.setPosition(0,0);
		
		red = new Sprite(new Texture("red.png"));
		white = new Sprite(new Texture("white.png"));
		blue = new Sprite(new Texture("blue.png"));
		yellow = new Sprite(new Texture("yellow.png"));
		green = new Sprite(new Texture("green.png"));
		
		sprite.setPosition(200,200);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			map.translateY(-vy);
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)){
			map.translateY(vy);
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)){
			map.translateX(-vx);
		} else if (Gdx.input.isKeyPressed(Input.Keys.A)){
			map.translateX(vx);
		}
		
		// set all objects' positions relative to the map posn
		red.setX(map.getX()+50);
		red.setY(map.getY()+60);
		yellow.setX(map.getX()+351);
		yellow.setY(map.getY()+2);
		green.setX(map.getX()+54);
		green.setY(map.getY()+600);
		white.setX(map.getX()+99);
		white.setY(map.getY()+345);
		blue.setX(map.getX()+671);
		blue.setY(map.getY()+68);
		
		batch.begin();
		map.draw(batch);
		red.draw(batch);
		blue.draw(batch);
		yellow.draw(batch);
		green.draw(batch);
		white.draw(batch);
		sprite.draw(batch);
		batch.end();
	}
}
