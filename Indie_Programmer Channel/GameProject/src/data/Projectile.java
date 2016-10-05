package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import static helpers.Artist.*;

public abstract class Projectile implements Entity {

	private Texture texture;
	private float x,y,speed,xVelocity,yVelocity;
	private int damage,width,height;
	private Enemy target;
	private boolean alive;


	public Projectile(Texture texture,Enemy target, float x,float y,int width ,int height,float speed,int damage){
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();


	}
	private void calculateDirection(){	
		// para diagonal movements
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x - Game.TILE_SIZE / 4+ Game.TILE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - Game.TILE_SIZE / 4+ Game.TILE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		// cuanta distancia falta
		float xPercentOfMovemement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovemement;
		yVelocity = totalAllowedMovement - xPercentOfMovemement;




		// esto es pa saber si el enemigo esta antes de la torre o despues 
		// si esta despues target.getX() > x.
		if(target.getX() < x){
			xVelocity *= -1;
		}
		if(target.getY() < y){
			yVelocity *= -1;
		}


	}
	
	public void isHit(){
		target.damage(damage);
		alive = false;
		
	}
	

	public void update() {
		if(alive){
			x+= xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			if(checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight())){
				isHit();
				
			}

			draw();

		}
	}

	public void draw(){
		drawQuadTex(texture, x,y ,width, height);

	}
	
	public Texture getTexture(){
		return texture;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setAlive(boolean alive ){
		this.alive = alive;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Enemy getTarget(){
		return target;
	}






}
