package UI;

import java.util.ArrayList;

import static helpers.Artist.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class UI {

	private ArrayList<Button> buttonList;

	public UI(){
		buttonList = new ArrayList<Button>();

	}

	public void addButton(String name ,String textureName ,int x, int y){
		buttonList.add(new Button(name , quickLoad(textureName),x,y) );

	}

	public boolean isButtonClicked(String buttonName){
		Button b = getButton(buttonName);
		float mouseY = HEIGHT - Mouse.getY() - 1;
		if(Mouse.getX() > b.getX() && Mouse.getX()  < b.getX() + b.getWidth() 
				&& mouseY > b.getY() && mouseY < b.getX() + b.getWidth()  ){
			return true;
			
			
		}
		
		return false;
		
		
		
	}

	private Button getButton(String buttonName ){
		for(Button b : buttonList ){
			if(b.getName().equals(buttonName)){
				return b;

			}

		}
		return null;

	}



	public void draw(){
		for (Button b : buttonList){
			drawQuadTex(b.getTexture(), (float) b.getX(), (float) b.getY(),b.getWidth(),(float) b.getHeight());

		}

	}





}
