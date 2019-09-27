/* Collision Detection Between Objects in Two Arrays
  Topics:
	  1. Use nested looping to detect when objects in two arrays collide
	  2. Update the locations of objects in two arrays on the screen 
  Exercises:
  1. Update your rainfall program to: 
	  a) replace the rectangle with a sun (it does not have to move)
	  b) allow the sun to shoot fireballs 
	  c) remove fireballs and rain drops when they collide
*/
package com.omstead.gamebasics;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class L15CollisionBetweenTwoSets extends ApplicationAdapter {
	SpriteBatch batch;
		
	ArrayList<Sprite> balls = new ArrayList<Sprite>();// our two objects list
	ArrayList<Sprite> boxes = new ArrayList<Sprite>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		
		Sprite ball;
		for (int i = 0; i < 10; i++) {
			ball = new Sprite(new Texture("Ball.png"));
			// x - what you multiply by gives spacing to your chars
			// y - to make them start off-screen, add 500
			ball.setPosition((int)(Math.random()*Gdx.graphics.getWidth()), (int)(Math.random()*1000 + 500)); 
			balls.add(ball);
		}

		Sprite box;
		for (int i = 0; i < 10; i++) {
			box = new Sprite(new Texture("Brick.png"));
			// x - what you multiply by gives spacing to your chars
			// y - to make them start off-screen, add 500
			box.setPosition((int)(Math.random()*Gdx.graphics.getWidth()), (int)(Math.random()*1000 - 500)); 
			boxes.add(box);
		}
		
		System.out.print("Sizes:" + balls.size() + " " + boxes.size());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		for(int i = balls.size()-1; i >= 0; i--) {
			// first move the balls
			balls.get(i).translateY(-5);
			
			// check for collisions
			for(int j = boxes.size()-1; j >= 0; j--) {
				Rectangle r1 = balls.get(i).getBoundingRectangle();
				Rectangle r2 = boxes.get(j).getBoundingRectangle();
				if (r1.overlaps(r2)) {
					balls.remove(i);
					boxes.remove(j);
					// need to break out of the loop because if you remove the last box in the boxes
					// array,there will be nothing left to compare a ball to and you'll get an exception 
					break;
				}
			}
		}
		
		// what would happen if this line was put in the inner loop above?
		for(int i = boxes.size()-1; i >= 0; i--) {
			boxes.get(i).translateY(5);
		}
		
		// draw items to screen
		batch.begin();
		// nothing is being removed in these loops, so you can loop start to end
		for(int i = 0; i < balls.size(); i++) {
			balls.get(i).draw(batch);
		}
		
		for(int i = 0; i < boxes.size(); i++) {
			boxes.get(i).draw(batch);
		}
		batch.end();
	}
}
