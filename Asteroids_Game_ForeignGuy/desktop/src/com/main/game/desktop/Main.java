package com.main.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.main.game.Game;

public class Main {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Asteroids";
		config.width =  500;
		config.height = 400 ;
		config.useGL30 = false ;
		config.resizable = false;
		
		new LwjglApplication(new Game(), config);
	}
}
