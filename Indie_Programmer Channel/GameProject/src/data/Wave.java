package data;

import static helpers.Clock.Delta;

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
		// si la lista de enemigo es menor que los enemigos 
		// que deben haber en un wave spawn un enemigo mas 
		if(enemyList.size() < enemiesPerWave && !waveCompleted){
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
		for (int i = 0; i < enemyList.size(); i++) {
			if(enemyList.get(i).isAlive())
				System.out.println( " el enemigo #" + i +   " esta vivo ");
			else 
				System.out.println(" el enemigo #"+ i + " esta muerto");
		}
		System.out.println(" enemigos en el mapa "+enemyList.size() );
		//else{
		//enemyList.remove(e);
		//}

		if(allEnemiesDead){
			waveCompleted = true;
			enemyList.clear();
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
