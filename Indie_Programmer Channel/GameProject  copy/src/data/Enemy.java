package data;

import java.util.ArrayList;


import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*; 
import static data.Game.*;

public class Enemy implements Entity{

	private int width,height,currentCheckPoints;
	private float speed,x,y,health,startHealth;
	private Texture texture,healthBackground,healthForeGround,healthBorder;
	private Tile startTile;
	private boolean first = true;
	private boolean alive = true;
	private TileGrid grid;

	private ArrayList<CheckPoint> checkPoints;
	private int [] directions;

	public Enemy(Texture texture,Tile startTile , TileGrid grid,int width ,int height , float speed,float health){

		this.texture = texture;
		this.healthBackground = quickLoad("health_background");
		this.healthForeGround = quickLoad("health");
		this.healthBorder = quickLoad("health_border");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.startHealth = health;

		this.checkPoints = new ArrayList<CheckPoint>();
		this.directions = new int [2];

		this.directions [0] = 0;
		this.directions [1] = 1;
		directions = findNextD(startTile);
		this.currentCheckPoints = 0;
		PopulateCheckPointList();

	}


	public void update(){
		if(first)
			first = false;
		else{
			if(CheckPointReached()){
				if(currentCheckPoints + 1 == checkPoints.size()){
					endOfMazeReached();
				}
				else
					currentCheckPoints ++;
			}
			else {
				x += Delta() * checkPoints.get(currentCheckPoints).getxDirection() * speed;
				y += Delta() * checkPoints.get(currentCheckPoints).getyDirection() * speed;


			}
		}

	}
	
	private void endOfMazeReached(){
		Player.modiFyLives(-1);
		die();
	}

	private CheckPoint FindNextC(Tile s,int [] dir){
		Tile next = null;
		CheckPoint c = null;
		boolean found = false;
		int counter = 1;

		while(!found){
			if(s.getXPlace() + dir[0] * counter == grid.getTilesWide() ||
					s.getYPlace() + dir[1] * counter == grid.getTilesHigh() ||	 
					s.getType() != grid.getTile(s.getXPlace() + dir[0] * counter,
							s.getYPlace() + dir[1] * counter ).getType()){
				found = true;
				counter -= 1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter,
						s.getYPlace() + dir[1] * counter );

			}

			counter ++;


		}

		c = new CheckPoint(next, dir[0], dir[1]);
		return c;

	}

	private boolean CheckPointReached(){
		boolean reached = false;
		Tile t = checkPoints.get(currentCheckPoints).getTile();
		if(x > t.getX() - 3 && 
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3 ){
			reached = true;
			x = t.getX();
			y = t.getY();

		}

		return reached;

	}

	private void PopulateCheckPointList(){
		checkPoints.add(FindNextC(startTile, directions = 
				findNextD(startTile )));

		int counter = 0;
		boolean count = true;

		while(count){
			int[] currentD = findNextD(checkPoints.get(counter).getTile());
			if(currentD[0] == 2 || counter == 20){
				count = false;

			}else {
				checkPoints.add(FindNextC(checkPoints.
						get(counter).getTile(),
						directions = findNextD(checkPoints.
								get(counter).getTile() ) ));
			}
			counter ++;


		}


	}



	private int[] findNextD(Tile s){
		int [] dir = new int[2];
		Tile u = grid.getTile(s.getXPlace(),s.getYPlace() - 1 );
		Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
		Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());


		if(s.getType() == u.getType() && directions [1] !=1 ){
			dir[0] = 0;
			dir[1] = -1;
		}
		else if(s.getType() == r.getType() && directions [0] != -1){
			// 0 1
			dir[0] = 1;
			dir[1] = 0;
		}
		else if(s.getType() == d.getType()&& directions [1] != -1){
			// 1 0
			dir[0] = 0;
			dir[1] = 1;
		}
		else if(s.getType() == l.getType()&& directions [0] != 1){
			// 0 -1
			dir[0] = -1;
			dir[1] = 0;
		}
		else{
			dir[0] = 2;
			dir[1] = 2;
			
		}


		return dir;


	}
	
	public void damage(int ammount){
		health -=ammount;
		if(health <= 0){
			die();
			Player.modifyCash(5);
			
		}
	}
	
	
	private void die(){
		alive = false;
		
	}
	
	public void draw(){
		float healthPercentage = health / startHealth;
		drawQuadTex(texture, x, y, texture.getImageWidth()  , texture.getImageHeight()  );
		drawQuadTex(healthBackground, x, y - 16, width, 8);
		drawQuadTex(healthForeGround, x, y - 16,TILE_SIZE * healthPercentage , 8);
		drawQuadTex(healthBorder, x, y - 16, width, 8);
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileGrid getTileGrid(){
		return grid;

	}
	public boolean isAlive(){
		return alive;
	}



}
