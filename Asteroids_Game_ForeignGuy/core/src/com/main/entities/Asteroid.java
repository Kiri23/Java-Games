package com.main.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends SpaceObject {
	
	private int type;
	public static final int SMALL =0;
	public static final int MEDIUM = 1;
	public static final int LARGE = 2 ;
	
	private int numPoints;
	private float[] dist;
	
	private int score;
	
	private boolean remove;
	
	public Asteroid(float x, float y , int type){
		this.x = x ;
		this.y = y ;
		this.type = type;
		
		if(type == SMALL){
			numPoints = 8;
			width = height = 12 ;
			speed = MathUtils.random(70,100);
			score = 100;
		}
		else if (type == MEDIUM){
			numPoints = 10;
			width = height = 20;
			speed = MathUtils.random(50,60); 
			score = 50;
			
		}
		else if (type == LARGE){
			numPoints = 12;
			width = height = 40;
			speed = MathUtils.random(20,30); 
			score = 20;
		}
		rotationsSpeed = MathUtils.random(-1,1);
		
		radians = MathUtils.random(2 * 3.1415f);
		
		directionx = MathUtils.cos(radians) * speed;
		directiony = MathUtils.sin(radians) * speed;
		
		shapePointx = new float [numPoints];
		shapePointy = new float [numPoints] ;
		dist = new float [numPoints] ;
		
		int radius = width / 2;
		
		for (int i = 0; i < numPoints ; i++) {
			// para crear los puntos desde radius entre 2 hasta radius 
			dist[i] = MathUtils.random(radius /2 , radius);	
		}
		
		setShapePoints();

	}
	private void setShapePoints(){
		float angle = 0;
		for (int i = 0; i < numPoints ; i++) {
			shapePointx[i] = x + MathUtils.cos(angle + radians)* dist[i];
			shapePointy[i] = y + MathUtils.sin(angle + radians)* dist[i];
			angle += 2 * 3.1415f / numPoints ;
		}
	}
	public int getType() {
		return type;
	}
	public boolean shouldRemove(){
		return remove;
	}
	public int getScore(){
		return score;
	}
	public void update (float dt){
		// como esto esta en update se suma la misma cantidad todo el tiempo.
		x += directionx * dt;
		y += directiony * dt;
		radians += rotationsSpeed * dt;
		setShapePoints();
		
		
		wrap();
		
		
	}
	public void draw (ShapeRenderer sr){
		sr.setColor(1,1,1,1);
		sr.begin(ShapeType.Line);
		for(int i = 0, j = shapePointx.length - 1;i< shapePointx.length;j = i++){
			sr.line(shapePointx[i], shapePointy[i], shapePointx[j], shapePointy[j]);;
		}
		
		
		sr.end();
		
		
		
	}
	
	
	
}
