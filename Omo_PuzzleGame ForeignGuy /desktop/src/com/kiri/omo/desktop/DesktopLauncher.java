package com.kiri.omo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kiri.omo.Omo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Omo.tittle;
		config.width = Omo.WIDTH / 2;
		config.height = Omo.HEIGHT / 2;
		
		
		new LwjglApplication(new Omo(), config);
	}
}
