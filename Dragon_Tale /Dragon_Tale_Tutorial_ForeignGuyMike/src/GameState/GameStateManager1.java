package GameState;

import java.util.ArrayList;

public class GameStateManager1 {
	
	
	// gamesStates se anaden los States del juego.
	private GameState [] gameStates;
	// para saber en que States(etapa) se encuentra el juego
	private int currentState;
	
	//////////////Los States del juego /////////////////
	
	public static final int NUMGAMESTATES = 2;
	
	// el states del menu
	public static final int MENUSTATE =0;
	// el primer level 
	public static final int LEVEL1STATE = 1;
	
	public GameStateManager1(){
		
		gameStates = new GameState [NUMGAMESTATES] ;
		
		
		currentState = MENUSTATE;
		loadStates(currentState);
		
	}
	private void loadStates(int state){
		if (state == MENUSTATE)
			//gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			System.out.println();
			//gameStates[state] = new Level1State(this);
	
	}
	private void unloadState(int state){
		gameStates [state] = null;
	}
	
	
	public void setStates (int state){
		unloadState(currentState);
		currentState = state;
		loadStates(currentState);
		//gameStates[currentState].init();
		
	}
	
	public void update(){
		// currenStates es el estado del juego.	
		try {
			gameStates[currentState].update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(java.awt.Graphics2D g){
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void keyPressed(int k){
		gameStates[currentState].keyPressed(k);
	}
	public void keyReleased(int k){
		gameStates[currentState].keyReleased(k);
	}
	

}
