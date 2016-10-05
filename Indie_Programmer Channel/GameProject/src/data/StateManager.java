package data;

import static org.lwjgl.opengl.GL11.*;

public class StateManager {
	

	static int [][] map = {
			{0,1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,2,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,1,1,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	
	public static enum GameState{
		MAINMENU,GAME,EDITOR
	}
	
	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu ;
	public static Game game;
	public static Editor editor;
	public static int curr = 1;
	
	
	public static void update(){
		switch(gameState){
		case MAINMENU:
			if(mainMenu == null ){
				//glColor3f(1.0f,0.0f,0.0f);
				//glRectf(0, 0, 640, 480);
				mainMenu = new MainMenu();
				mainMenu.update();
				break;
				
			}
		case GAME:
			if(game == null ){
				//glColor3f(0.0f,1.0f,0.0f);
				//glRectf(0, 0, 640, 480);
				game = new Game(map);
				game.update();
				break;
			}
		case EDITOR:
			if(editor == null){
				//glColor3f(0.0f,0.0f,1.0f);
				//glRectf(0, 0, 640, 480);
				editor = new Editor();
				editor.update();
				break;
				
			}
			
			
			break;
		
		
		}
		
	}
	public static void setState (GameState newState){
		gameState = newState;
		
		
	}
	
	

}
