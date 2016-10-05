package data;
import static helpers.Clock.*;
public class WaveManager {

	private float timeSinceLastWave, timeBetweenEnemies,timeBetweenWaves;
	private int waveNumber,enemiesperWave;
	private Enemy enemyType;
	private Wave currentWave;

	public WaveManager(Enemy enemyType,float timeBetwenEnemies , int enmiesperWave,float timeBetweenWaves){
		this.enemyType = enemyType;
		this.timeBetweenEnemies = timeBetwenEnemies ;
		this.enemiesperWave = enmiesperWave;
		this.timeSinceLastWave = 0;
		this.timeBetweenWaves = timeBetweenWaves;
		this.waveNumber = 0;

		this.currentWave = null;

		newWave();

	}

	public void update(){

		if(!currentWave.isCompleted()){
			currentWave.Update();

		}/* Aqui debe estar el error 
		 * de que el wave empieza mas rapido
		 */ else{
			 timeSinceLastWave += Delta();
			 
			 if(timeSinceLastWave >= timeBetweenWaves ){
				 newWave();
				 timeSinceLastWave = 0;
			 }
		 }
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
