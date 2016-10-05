package com.main.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {
	
	private float lifeTime;
	private float lifeTimer;
	
	private boolean remove;
	
	public Bullet(float x, float y,float radians){
		this.x = x;
		this.y = y;
		this.radians = radians;
		
		float speed = 350;
		// la direcion es radianes y que se le suma la distancia a speed
		directionx = MathUtils.cos(radians) * speed ;
		directiony =  MathUtils.sin(radians) * speed ;
		
		width = 2;
		height = 2;
		
		lifeTimer = 0;
		// stay on screen - 1 second
		lifeTime = 1;
	}
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update(float dt){
		x += directionx * dt;
		y += directiony * dt;
	
		wrap();
		
		lifeTimer +=dt;
		if(lifeTimer > lifeTime){
			remove = true;
		}
		
	}
	
	public void draw (ShapeRenderer sr){
		
		sr.setColor(1,1,1,1);
		sr.begin(ShapeType.Filled);
		sr.circle(x-width /2 ,y - height / 2  ,width / 2);
	
		sr.end();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
