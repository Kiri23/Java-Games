package data;

import static helpers.Artist.*;
import helpers.Clock;

import java.util.concurrent.TimeUnit;
import static helpers.Clock.*;

import org.lwjgl.opengl.Display;

public class Boot {

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


	public Boot() {
		beginSession();
		Game game = new Game(map); 
		MainMenu menu = new MainMenu();
		Editor editor = new Editor();

		while(!Display.isCloseRequested()){
			Clock.update();


			if (!menu.getFirst() && !menu.getSecond()){
				menu.update();
			}else if (menu.getFirst() && !menu.getSecond()){
				game.update();
			}else if (!menu.getFirst() && menu.getSecond()){
				editor.update();
			}

			//StateManager.update();

			Display.update();
			Display.sync(60);



		}

	}

	public static void main(String[] args) {
		new Boot();
	}



}
