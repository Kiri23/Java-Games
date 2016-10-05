package data;

public class WaveManager {

	private float timeSinceLastWave, timeBetweenEnemies;
	private int waveNumber,enemiesperWave;
	private Enemy enemyType;
	private Wave currentWave;

	public WaveManager(Enemy enemyType,float timeBetwenEnemies , int enmiesperWave){
		this.enemyType = enemyType;
		this.timeBetweenEnemies = timeBetwenEnemies ;
		this.enemiesperWave = enmiesperWave;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;

		this.currentWave = null;

		newWave();

	}

	public void update(){
		if(!currentWave.isCompleted()){
			currentWave.Update();
		}else 
			newWave();
	}

	private void newWave(){
		currentWave = new Wave(enemyType,timeBetweenEnemies,enemiesperWave);
		waveNumber++;
		System.out.println("Beggining wave "+ waveNumber);
		switch(waveNumber){
		case 2:
			currentWave = new Wave(enemyType,timeBetweenEnemies,enemiesperWave * waveNumber);
			break;
		case 3:
			currentWave = new Wave(enemyType,timeBetweenEnemies,enemiesperWave * waveNumber);
			break;
		}


	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	public int getWaveNumber() {
		return waveNumber;
	}
	
	public void setEnemyType(Enemy enemyType) {
		this.enemyType = enemyType;
	}





}
