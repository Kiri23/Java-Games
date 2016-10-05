package data;

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
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;
	public static int curr = 1;
	
	
	public static void update(){
		switch(gameState){
		case MAINMENU:
			if(mainMenu == null && curr == 1 ){
				mainMenu = new MainMenu();
				mainMenu.update();
				break;
				
			}
		case GAME:
			if(game == null && curr == 2){
				game = new Game(map);
				game.update();
				break;
			}
			
			break;
		case EDITOR:
			if(editor == null){
				editor = new Editor();
				editor.update();
				break;
				
			}
			
			
			break;
		
		
		}
		
	}
	public static void setState (GameState newState,int curr){
		gameState = newState;
		StateManager.curr = curr;
		
		
	}
	
	

}
