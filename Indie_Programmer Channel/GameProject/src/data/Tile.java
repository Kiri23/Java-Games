package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;


public class Tile {
	private float x, y ;
	int width,height;
	private Texture texture;
	private TileType type;

	public Tile(float x, float y, int width, int height,TileType type ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = quickLoad(type.textureName);

	}

	public void draw(){
		drawQuadTex(texture, x, y, width, height);

	}


	public Texture getTexture() {
		return texture;
	}

	public TileType getType() {
		return type;
	}

	public float getX() {
		return x;
	}

	public int getXPlace(){
		return (int) x / 64;

	}
	public int getYPlace(){
		return (int) y / 64;
	}
	
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public float getY() {
		return y;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}


	public void setType(TileType type) {
		this.type = type;
	}



}
