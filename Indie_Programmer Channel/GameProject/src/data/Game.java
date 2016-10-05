package data;

import static helpers.Artist.quickLoad;
import static helpers.Clock.*;

// todas las X y Todas las Y de diferentes clases son basadas 
// en el top Left corner 

public class Game {
	
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	
	public static final int TILE_SIZE = 64;
	
	
	private float test;
	
	// Temp Variables
	// TowerCannon tower;
	
	
	public Game(int[][] map){
		grid = new TileGrid(map);
		waveManager = new WaveManager(new Enemy(quickLoad("EnemyTile"), grid.getTile(5,9),grid, 64, 64, 25,25),
				4,2,3.5f);
		player = new Player(grid,waveManager);
		player.setUp();
		
		// tower = new TowerCannon(QuickLoad("cannonBase"), 
			//	grid.GetTile(11, 8), 10);
		
	}
	
	public void update(){
		
		// chequiar el tiempo. el delta Time 4 segundos - 4.00 delta
		test += Delta();
		//System.out.println(test);
		if(waveManager.getWaveNumber() + 1 == 2){
			waveManager.setEnemyType(new Enemy(quickLoad("DirtTile"), grid.getTile(5,9),grid, 64, 64, 25,45));
		}else {	
			waveManager.setEnemyType(new Enemy(quickLoad("EnemyTile"), grid.getTile(5,9),grid, 64,64, 25,40));
		}
		
		grid.draw();
		waveManager.update();
		player.update();
		// tower.update();
		
		
		
		
		
		
	}
	
	
	
}
