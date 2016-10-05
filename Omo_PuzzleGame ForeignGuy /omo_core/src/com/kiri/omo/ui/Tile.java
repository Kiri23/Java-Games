package com.kiri.omo.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiri.omo.handler.Content;

public class Tile extends Box {

	private TextureRegion light;
	private TextureRegion dark;

	private boolean selected;

	private Content res;

	public Tile(float x,float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width - 8 ;
		this.height = height - 8 ;
		res = new Content();



		res.loadAtlas("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Omo_PuzzleGame ForeignGuy/omo_core/assets/pack.pack", 
				"pack");
		dark = res.getAtlas("pack").findRegion("dark");
		light = res.getAtlas("pack").findRegion("light");

	}
	
	public void setSelected(boolean b){
		selected = b;
	}
	public void update(float dt){

	}
	public void render(SpriteBatch sb){
		if(selected){
			sb.draw(light, x - width / 2 , y - height / 2,width / 2 ,height / 2);
		}
		else {
			sb.draw(dark, x - width / 2 , y - height / 2,width / 2 ,height / 2);
		}
		
	}



}
