package data;

import org.newdawn.slick.opengl.Texture;

public class ProjectileIceBall extends Projectile {

	public ProjectileIceBall(Texture texture, Enemy target, float x, float y,
			int width, int height, float speed, int damage) {
		super(texture, target, x, y, width, height, speed, damage);
		
		
	
	}
	@Override
	public void isHit(){
		super.getTarget().setSpeed(4F);
		super.setAlive(false);
		System.out.println("Iis shd");
		
	}
	
	
	
	
	

}
