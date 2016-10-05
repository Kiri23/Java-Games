package data;

import static helpers.Artist.*;
import helpers.Clock;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Player  {
	private TileGrid grid;
	private TowerType [] towerTypes;
	private int index;
	private WaveManager waveManager;
	private ArrayList<Tower > towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown;
	private static int cash ,lives;

	public Player(TileGrid grid, WaveManager waveManager){
		this.grid = grid;
		this.towerTypes = new TowerType [3];
		this.towerTypes[0] = TowerType.CannonRed;
		this.towerTypes[1] = TowerType.CannonBlue;
		this.towerTypes[2] = TowerType.CannonIce;
		this.index = 0;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		cash = 0;
		lives = 0;


	}

	public void setUp(){
		cash = 50;
		lives = 10;

	}

	public static boolean modifyCash (int amount){
		if(cash + amount >=0){
			cash += amount;
			System.out.println("Modificando el cash " + cash);
			return true;
		}
		System.out.println("Currency " + cash);
		return false;
	}

	public static void modiFyLives(int amount){
		lives += amount;

	}

	public void update(){
		for(Tower t : towerList){
			t.update();
			t.draw();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}
		// handle mouse input 
		if(Mouse.isButtonDown(0) && !leftMouseButtonDown){
			setTower();

			
		}
		if(Mouse.isButtonDown(1) && !rightMouseButtonDown){
			System.out.println("right mouse ");
			towerList.add(new TowerCannonBlue(TowerType.CannonBlue, 
					grid.getTile((Mouse.getX() / 64) + 2  , (HEIGHT - Mouse.getY() - 1) / 64),waveManager.getCurrentWave().getEnemyList()));


		}



		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);

		// handle keyboard input
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
				Clock.changeMultiplier(0.2f);	
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
				Clock.changeMultiplier(-0.2f);	
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState()){
				moveIndex();
			}



		}


	}


	private void setTower(){
		if(index <2){
			if(modifyCash(-20)){
				towerList.add(new TowerCannonBlue(towerTypes[index], 
						grid.getTile((Mouse.getX() / 64) + 2  , (HEIGHT - Mouse.getY() - 1) / 64),waveManager.getCurrentWave().getEnemyList()));
			}
		}else 
			towerList.add(new TowerIce(towerTypes[index], 
					grid.getTile((Mouse.getX() / 64) + 2  , (HEIGHT - Mouse.getY() - 1) / 64),waveManager.getCurrentWave().getEnemyList()));
	}

	private void moveIndex(){
		index++;
		if(index > towerTypes.length - 1 ){
			index = 0;
		}

	}
}