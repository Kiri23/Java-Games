package data;

import static helpers.Artist.quickLoad;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerIce extends Tower {
	
	
	public TowerIce(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
			
	}
	@Override
	public void shoot(){
		super.shoot();
		super.getProjectiles().add(new ProjectileIceBall(quickLoad("IceGun"),getTarget(), getX() + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4  ,getY() + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4 , 32 ,32 , 400, 10));
		
	}
	
}
