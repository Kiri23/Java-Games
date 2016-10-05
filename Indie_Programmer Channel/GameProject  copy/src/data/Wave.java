package data;

import static helpers.Clock.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private boolean first = true;
	private CopyOnWriteArrayList<Enemy> enemyList;
	private int enemiesPerWave;
	private boolean waveCompleted;


	public Wave (Enemy enemyType,float spawnTime,int enemiesPerWave){
		this.enemyType =enemyType;
		this.spawnTime = spawnTime;
		this.enemiesPerWave = enemiesPerWave;
		this.timeSinceLastSpawn = 0;
		this.waveCompleted =false;
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		spawn();

	}

	public void Update(){
		boolean allEnemiesDead = true;
		if(enemyList.size() < enemiesPerWave ){
			if(first){
				first = false;
			}else {
				timeSinceLastSpawn += Delta();		
			}

			if(timeSinceLastSpawn > spawnTime ){
				spawn();
				timeSinceLastSpawn = 0;
			}
		}

		for (Enemy e : enemyList){
			if(e.isAlive()){
				allEnemiesDead = false;
				e.update();
				e.draw();
				
			}
			
		}

		/*		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).update();
			enemyList.get(i).draw();
			System.out.println(enemyList.size());
			if(!enemyList.get(i).isAlive()){
				enemyList.remove(i);
				i--;
			}//else{ 

				//waveCompleted = false;
			//}
		 */		

		if(allEnemiesDead){
			waveCompleted = true;
		}
	}

	private void spawn() {
		enemyList.add(new Enemy(enemyType.getTexture(),enemyType.getStartTile(),
				enemyType.getTileGrid(),64, 64, enemyType.getSpeed(),enemyType.getHealth()));


	}
	public boolean isCompleted(){
		return waveCompleted;
	}

	public CopyOnWriteArrayList<Enemy> getEnemyList() {
		return enemyList;
	}


}
