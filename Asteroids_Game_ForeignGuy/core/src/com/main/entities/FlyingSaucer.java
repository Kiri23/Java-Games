package com.main.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.main.game.Game;
import com.main.managers.JukeBox;

public class FlyingSaucer extends SpaceObject {
	
	private ArrayList<Bullet> bullets;
	
	private int type;
	public static final int LARGE = 0;
	public static final int SMALL = 1;
	
	private int score;
	
	private float fireTimer;
	private float fireTime;
	
	private Player player;
	
	private float pathTimer;
	private float pathTime1;
	private float pathTime2;
	
	private int direction;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	private boolean remove;
	
	public FlyingSaucer(
			int type,
			int direction,
			Player player,
			ArrayList<Bullet> bullets
			) {
		
		this.type = type;
		this.direction = direction;
		this.player = player;
		this.bullets = bullets;
		
		speed = 70;
		if(direction == LEFT) {
			directionx = -speed;
			x = Game.WIDTH;
		}
		else if(direction == RIGHT) {
			directionx = speed;
			x = 0;
		}
		y = MathUtils.random(Game.HEIGHT);
		
		shapePointx = new float[6];
		shapePointy = new float[6];
		setShapePoint();
		
		if(type == LARGE) {
			score = 200;
			JukeBox.loop("largesaucer");
		}
		else if(type == SMALL) {
			score = 1000;
			JukeBox.loop("smallsaucer");
		}
		
		fireTimer = 0;
		fireTime = 1;
		
		pathTimer = 0;
		pathTime1 = 1;
		pathTime2 = pathTime1 + 1;
		
	}
	
	private void setShapePoint() {
		if(type == LARGE) {
			shapePointx[0] = x - 10;
			shapePointy[0] = y;
			
			shapePointx[1] = x - 3;
			shapePointy[1] = y - 5;
			
			shapePointx[2] = x + 3;
			shapePointy[2] = y - 5;
			
			shapePointx[3] = x + 10;
			shapePointy[3] = y;
			
			shapePointx[4] = x + 3;
			shapePointy[4] = y + 5;
			
			shapePointx[5] = x - 3;
			shapePointy[5] = y + 5;
		}
		else if(type == SMALL) {
			shapePointx[0] = x - 6;
			shapePointy[0] = y;
			
			shapePointx[1] = x - 2;
			shapePointy[1] = y - 3;
			
			shapePointx[2] = x + 2;
			shapePointy[2] = y - 3;
			
			shapePointx[3] = x + 6;
			shapePointy[3] = y;
			
			shapePointx[4] = x + 2;
			shapePointy[4] = y + 3;
			
			shapePointx[5] = x - 2;
			shapePointy[5] = y + 3;
		}
	}
	
	public int getScore() { return score; }
	public boolean shouldRemove() { return remove; }
	
	public void update(float dt) {
		
		// fire
		if(!player.isHit()) {
			fireTimer += dt;
			if(fireTimer > fireTime) {
				fireTimer = 0;
				if(type == LARGE) {
					radians = MathUtils.random(2 * 3.1415f);
				}
				else if(type == SMALL) {
					radians = MathUtils.atan2(
							player.gety() - y,
							player.getx() - x
							);
				}
				bullets.add(new Bullet(x, y, radians));
				JukeBox.play("saucershoot");
			}
		}
		
		// move along path
		pathTimer += dt;
		// move forward
		if(pathTimer < pathTime1) {
			directiony = 0;
		}
		
		// move downward
		if(pathTimer > pathTime1 && pathTimer < pathTime2) {
			directiony = -speed;
		}
		
		// move to end of screen
		if(pathTimer > pathTime1 + pathTime2) {
			directiony = 0;
			
		}
		
		x += directionx * dt;
		y += directiony * dt;
		
		// screen wrap
		if(y < 0) y = Game.HEIGHT;
		
		// set shape
		setShapePoint();
		
		// check if remove
		if((direction == RIGHT && x > Game.WIDTH) ||
			(direction == LEFT && x < 0)) {
			remove = true;
		}
		
	}
	
	public void draw(ShapeRenderer sr) {
		
		sr.setColor(1, 1, 1, 1);
		sr.begin(ShapeType.Line);
		
		for(int i = 0, j = shapePointx.length - 1;
			i < shapePointx.length;
			j = i++) {
			
			sr.line(shapePointx[i], shapePointy[i], shapePointx[j], shapePointy[j]);
			
		}
		
		sr.line(shapePointx[0], shapePointy[0], shapePointx[3], shapePointy[3]);
			
		sr.end();
		
	}
	
}

















