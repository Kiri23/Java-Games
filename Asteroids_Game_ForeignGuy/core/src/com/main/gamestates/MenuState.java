package com.main.gamestates;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.main.entities.Asteroid;
import com.main.game.Game;
import com.main.managers.GameStateManager;

public class MenuState extends GameState {

	private SpriteBatch sb;
	private ShapeRenderer sr;

	private BitmapFont titleFont;
	private BitmapFont font;

	private final String tittle = "Asteroids";

	private int currentItem;
	private String [] menuItems;

	private ArrayList<Asteroid>asteroid;



	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		// set font
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/core/res/fonts/Hyperspace Bold.ttf")
				);
		titleFont = gen.generateFont(56);
		titleFont.setColor(Color.WHITE);

		font = gen.generateFont(30);

		menuItems = new String []{
				"Play",
				"Highscores",
				"Quit"
		};

		asteroid = new ArrayList<Asteroid> ();
		for (int i = 0; i < 6; i++) {
			asteroid.add(
					new Asteroid(MathUtils.random(Game.WIDTH), 
							MathUtils.random(Game.HEIGHT),
							Asteroid.LARGE)
					);
		}

	}

	@Override
	public void update(float dt) {
		handleInput();
		for (int i = 0; i < asteroid.size(); i++) {
			asteroid.get(i).update(dt);
		}
	}

	@Override
	public void draw() {
		sb.setProjectionMatrix(Game.cam.combined);
		sr.setProjectionMatrix(Game.cam.combined);
		
		for (int i = 0; i < asteroid.size(); i++) {
			asteroid.get(i).draw(sr);	
		}
		
		sb.begin();
	
		float width = titleFont.getSpaceWidth() + 218;
		titleFont.draw(sb, tittle,(Game.WIDTH - width) / 2 ,300 );
		
		//draw menu
		for (int i = 0; i < menuItems.length; i++) {
			//width = titleFont.getSpaceWidth() + 218;
			if(currentItem == i)
				font.setColor(Color.RED);
			else 
				font.setColor(Color.WHITE);
			font.draw(sb, menuItems[i],(Game.WIDTH - 80 ) / 2 ,210 - 35 * i );

		}
		


		sb.end();		
	}

	@Override
	public void handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			if(currentItem < menuItems.length -1){
				currentItem ++;
			}

		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			if(currentItem > 0)
				currentItem --;

		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			select();

		}
	}
	private void select(){
		if(currentItem ==0){
			gsm.setState(GameStateManager.PLAY);

		}
		if(currentItem == 1){
			gsm.setState(GameStateManager.HighScores);

		}
		if(currentItem == 2){
			Gdx.app.exit();


		}	

	}


	@Override
	public void dispose() {
		sb.dispose();
		sr.dispose();
		titleFont.dispose();
		font.dispose();

	}




}
