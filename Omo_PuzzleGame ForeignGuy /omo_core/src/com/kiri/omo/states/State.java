package com.kiri.omo.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.kiri.omo.Omo;


public abstract class State {
	
	protected GameStatesManger gsm;
	protected OrthographicCamera cam;
	protected Vector3 mouse;
	
	protected State(GameStatesManger gsm){
		this.gsm = gsm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Omo.WIDTH,Omo.HEIGHT);
		mouse = new Vector3();
		
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render (SpriteBatch sb);
	
	
}
