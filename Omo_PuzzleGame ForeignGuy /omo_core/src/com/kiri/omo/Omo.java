package com.kiri.omo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kiri.omo.handler.Content;
import com.kiri.omo.states.GameStatesManger;

public class Omo extends ApplicationAdapter {
	
	public static final String tittle= "Omo";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	private GameStatesManger gsm;
	private SpriteBatch sb;
	
	public static Content res;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		gsm = new GameStatesManger();
		sb = new SpriteBatch();
		gsm.push(new PlayStates(gsm));
		res = new Content();
		String pathPack = "/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Omo_PuzzleGame ForeignGuy/"
				+ "omo_core/assets/pack.pack";
		
		//res.loadAtlas("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Omo_PuzzleGame ForeignGuy/omo_core/assets/pack.pack", 
				//"pack");
		
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
		
	}
}
