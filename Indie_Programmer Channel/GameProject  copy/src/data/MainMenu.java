package data;

import static helpers.Artist.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import data.StateManager.GameState;
import UI.Button;
import UI.UI;


public class MainMenu {


	int [][] map = {
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

	private Texture background;
	private UI menuUi;
	// private Game game = new Game(map);
	private boolean first = false;

	private boolean second = false;
	private Game game = new Game(map);
	private Editor editor = new Editor();

	public MainMenu(){
		background = quickLoad("background_td");
		menuUi = new UI();
		menuUi.addButton("Play","Play_Button", WIDTH / 2 - 128,
				(int) (HEIGHT * 0.45F));
		menuUi.addButton("Editor", "Editor_Button", WIDTH / 2 - 128,
				(int)(HEIGHT * 0.55F));
		menuUi.addButton("Quit", "Quit_Button", WIDTH / 2 - 128, (int)
				(HEIGHT * 0.65F));



	}

	private void updateButtons(){
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_P && Keyboard.getEventKeyState()){
				System.out.println("Game update");
				first = true;
				
			}
			
			if(Keyboard.getEventKey() == Keyboard.KEY_E && Keyboard.getEventKeyState()){
				
				second = true;
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_Q && Keyboard.getEventKeyState()){
				System.exit(0);
				
			}

		}


		/*if(Mouse.isButtonDown(0)){
			if(menuUi.isButtonClicked("Play")){
				System.out.println("Game staa");
				//game.update();
				first = true;
				// StateManager.setState(GameSt 2);

			}
			if(menuUi.isButtonClicked("Editor")){
				second  = true;
			}
			if(menuUi.isButtonClicked("Quit")){
				System.exit(0);

			}

		} */


	}



	public void update(){
		drawQuadTex(background, 0, 0, 2048, 1024);
		menuUi.draw();
		updateButtons();



	}

	public boolean getFirst(){
		return first;
	}
	public boolean getSecond(){
		return second;

	}





}
