package data;
import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawQuadTexRot;
import static helpers.Artist.quickLoad;
import static helpers.Clock.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;


public abstract class Tower implements Entity {
	private float x,y,timeSinceLastShoot,firingSpeed , angle;
	private int width,height,damage, range;
	private Enemy target;
	private Texture [] textures;
	private CopyOnWriteArrayList<Enemy>enemies;
	private boolean targeted;
	private ArrayList <Projectile> projectiles;

	public Tower(TowerType type , Tile startTile , CopyOnWriteArrayList<Enemy> enemies ){
		this.textures = type.textures;
		this.damage = type.damage;
		this.range = type.range;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width =  startTile.getWidth();
		this.height =  startTile.getHeight();
		this.enemies = enemies;
		this.targeted = false;
		this.timeSinceLastShoot = 0F;
		this.projectiles = new ArrayList<Projectile>();
		this.firingSpeed = type.firingSpeed;
		this.angle = 0;

	}

	private Enemy acquireTarget(){
		Enemy closet = null;
		float closestDistance = 10000;
		for(Enemy e : enemies){
			if(isInRange(e) && finDistance(e) < closestDistance && e.isAlive()){
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

	public void shoot(){
		timeSinceLastShoot = 0;
		// direcion y le sumo baja
		// aparece al principio del tile si le resta la mitad del tile
		// menos el bullet size.
		projectiles.add(new ProjectileBall(quickLoad("bullet"),target, x + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4  ,y + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4 , 32 ,32 , 400, 10));

	}
	public void updateEnemyList (CopyOnWriteArrayList<Enemy> newList){
		enemies = newList;

	}


	@Override
	public void update() {
		if(!targeted){
			target = acquireTarget();
		}else {
			if(timeSinceLastShoot > firingSpeed){
				shoot();
			}
		}
		if(target == null || target.isAlive() == false )
			targeted = false;
		
		
		timeSinceLastShoot += Delta();

		for(Projectile p : projectiles ){
			p.update();	
		}

		angle = calculateAngle();
		draw();

	}


	@Override
	public void draw() {
		drawQuadTex(textures[0], x, y, width, height);
		if(textures.length > 1){
			for (int i = 0; i < textures.length; i++) {
				drawQuadTexRot(textures[i], x, y, width, height,angle);
			}
		}

	}


	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;

	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;

	}

	@Override
	public void setWidth(int width) {
		this.width = width;

	}

	@Override
	public void setHeight(int height) {
		this.height = height;

	}
	public Enemy getTarget(){
		return target;
	}

	public ArrayList<Projectile> getProjectiles(){
		return projectiles;

	}


}
