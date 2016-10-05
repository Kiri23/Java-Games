package com.main.entities;

import com.main.game.Game;

public class SpaceObject {
	
	protected float x ;
	protected float y ;
	
	protected float directionx;
	protected float directiony ;
	
	// the angle that is faced 
	protected float radians ;
	
	protected float speed ;
	protected float rotationsSpeed;
	
	protected int width ;
	protected int height ;
	
	
	protected float [] shapePointx;
	protected float [] shapePointy;
	
	
	public float getx(){
		return x;
	}
	public float gety(){
		return y;
	}
	public float [] getShapePointx(){
		return shapePointx;
	}
	public float [] getShapePointy(){
		return shapePointy;
	}	
	public void setPosition(float x, float y){
		this.x= x;
		this.y = y;
		
	}
	
	
	public boolean intersects (SpaceObject other){
		float [] sx = other.getShapePointx();
		float [] sy = other.getShapePointy();
		for (int i = 0; i < sx.length; i++) {
			if (contains(sx[i],sy[i])){
				return true;
			}
		}
		
		
		return false;
	}
	
	public boolean contains (float x,float y){
		boolean b = false;
		for (int i = 0, j = shapePointx.length - 1;i < shapePointx.length;j = i++) {
			if((shapePointy[i]>y) !=(shapePointy[j] > y) && 
					(x < (shapePointx[j] - shapePointx[i]) *
					(y - shapePointy[i]) / (shapePointy[j] - shapePointy[i])
					+ shapePointx[i])){
				b= !b;
			}
			
		}
		return b;
		
	}
	
	
	protected void wrap(){
		if(x<0)
			x = Game.WIDTH;
		if (x>Game.WIDTH)
			x = 0;
		if(y<0)
			y = Game.HEIGHT;
		if (y>Game.HEIGHT)
			y = 0;
		
	}
	
}
