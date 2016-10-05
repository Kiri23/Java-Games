package com.main.game;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.main.managers.GameInputProcessor;
import com.main.managers.GameStateManager;
import com.main.managers.JukeBox;
public class Game implements ApplicationListener {
	
	public static int WIDTH ;
	public static int HEIGHT ;
	
	public static OrthographicCamera cam;
	
	GameStateManager gsm;
	
	
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight() ;
		
		cam = new OrthographicCamera(WIDTH, WIDTH);
		cam.translate(WIDTH/2, HEIGHT/2);
		cam.update();
		Gdx.input.setInputProcessor(new GameInputProcessor());
		
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/explode.ogg", "explode");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/extralife.ogg", "extralife");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/largesaucer.ogg", "largesaucer");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/pulsehigh.ogg", "pulsehigh");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/pulselow.ogg", "pulselow");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/saucershoot.ogg", "saucershoot");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/shoot.ogg", "shoot");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/smallsaucer.ogg", "smallsaucer");
		JukeBox.load("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/"
				+ "core/res/sounds/thruster.ogg", "thruster");
		
		
		gsm = new GameStateManager();
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
			
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
}
