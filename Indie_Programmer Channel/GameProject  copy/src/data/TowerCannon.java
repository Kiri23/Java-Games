package data;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawQuadTexRot;
import static helpers.Artist.quickLoad;
import static helpers.Clock.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {
	private float x,y, timeSinceLastShoot , firingSpeed, angle;
	private int width,height,damage,range;
	private Texture baseTexture ,cannonTextrue;
	private Tile StartTile;
	private CopyOnWriteArrayList<Projectile> projectiles;
	private CopyOnWriteArrayList<Enemy> enemies;
	private Enemy target;// persica un enemigo en especifico
	private boolean targeted;
	
	
	public TowerCannon (Texture baseTexture, Tile startTile , int damage, 
			int range, CopyOnWriteArrayList<Enemy> enemies){
		this.baseTexture = baseTexture;
		this.cannonTextrue = quickLoad("cannonGun");
		this.StartTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.damage = damage;
		this.range = range;
		this.firingSpeed = 3;
		this.timeSinceLastShoot = 0;
		this.projectiles = new CopyOnWriteArrayList<Projectile>();
		this.enemies = enemies;
		this.targeted = false;
		
		//this.target = acquireTarget();
		//this.angle = calculateAngle();
		
		
	}
	
	private Enemy acquireTarget(){
		Enemy closet = null;
		float closestDistance = 10000;
		for(Enemy e : enemies){
			if(isInRange(e) && finDistance(e) < closestDistance){
				closestDistance = finDistance(e);
				closet = e;	
			}
			
		}
		if(closet !=null){
			targeted = true;
		}
		
		return closet;
	}
	
	private boolean isInRange(Enemy e){
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if(xDistance < range && yDistance < range){
			return true;
		}
		return false;
	}
	
	private float finDistance(Enemy e){
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);	
		return xDistance + yDistance;
		
	}
	
	
	private float calculateAngle(){
		// lo que debe devolver es el angulo entre dos puntos 
		// devuelve el angulo en raidanes de la posiciond del enemigo
		double angleTemp = Math.atan2(target.getY() - y, target.getX() -x);
		// convierte el radianes a degree 		
		//le resta 90 p
		// el 360 grado qeda abajo y el 90 queda a la izquierda , 
		// el 180 queda arriba y el 270 queda en la derecha.
		// si le resto 90 me da el angulo que esta posiconado el target
		// y los grados estan rodados hacia la izquierda por algo de la funcion
		// atan2 pq esta en el range de -pi a pi
		return (float)Math.toDegrees(angleTemp) - 90 ;
		
	}
	
	
	private void shoot(){
		timeSinceLastShoot = 0;
		// direcion y le sumo baja
		// aparece al principio del tile si le resta la mitad del tile
		// menos el bullet size.
		projectiles.add(new ProjectileIceBall(quickLoad("bullet"),target, x + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4  ,y + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4 , 32 ,32 , 400, 10));
		
	}
	
	public void updateEnemyList (CopyOnWriteArrayList<Enemy> newList){
		enemies = newList;
		
	}
	
	public void update(){
		if(!targeted){
			target = acquireTarget();
			
		}
		if(target == null || target.isAlive() == false )
			targeted = false;
		
		
		
		timeSinceLastShoot += Delta();
		
		if(timeSinceLastShoot > firingSpeed){
			shoot();
		}
		for(Projectile p : projectiles ){
			p.update();	
		}
		
		angle = calculateAngle();
		draw();
		
	}
	
	public void draw(){
		drawQuadTex(baseTexture,x,y,width,height);
		// rota el texture en el centro del spriteSheet
		drawQuadTexRot(cannonTextrue, x, y, width, height, angle);
		
	}
	
	public float getX() {
		return x;
	}
	

}
