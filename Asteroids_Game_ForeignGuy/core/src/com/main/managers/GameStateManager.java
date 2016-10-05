package com.main.managers;

import com.main.gamestates.GameOverState;
import com.main.gamestates.GameState;
import com.main.gamestates.HighScoreState;
import com.main.gamestates.MenuState;
import com.main.gamestates.PlayState;

public class GameStateManager {

	// current game state
	private GameState gameState;
	
	// para que puedas acerdirlas tiene  que ser Static y  Publicas 
	public static final int MENU = 0;
	public static final int PLAY = 2;
	public static final int HighScores = 3;
	public static final int GameOver = 4;

	public GameStateManager() {
		setState(MENU);
	}

	public void setState(int state) {
		if(gameState != null) gameState.dispose();
		if(state == MENU) {
			gameState = new MenuState(this);
		}
		if(state == PLAY) {
			gameState = new PlayState(this);
		}
		if(state == HighScores){
			gameState = new HighScoreState(this);
		}
		if(state == GameOver)
			gameState = new GameOverState(this);
	}


	public void update(float dt) {
		gameState.update(dt);
	}

	public void draw() {
		gameState.draw();
	}

}











