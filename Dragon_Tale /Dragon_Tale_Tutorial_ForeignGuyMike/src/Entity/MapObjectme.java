package Entity;

import java.awt.Rectangle;

import Main.GamePanel;
import TilesMap.Tile;
import TilesMap.TileMap;



// root class for all object
public abstract class MapObjectme {
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//position and vector 
	protected double x ;
	protected double y;
	protected double dx;
	protected double dy;
	
	
	// dimesnions 
	protected int width ;
	protected int height ;
	
	//collsions box 
	protected int cwidth ;
	protected int cheight ;
	
	//collision 
	protected int currRow; 
	protected int currCol; 
	protected double xdest ; 
	protected double ydest ;
	protected double xtemp ;
	protected double ytemp ;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft ;
	protected boolean bottomRight ;
	
	//animations 
	protected  Animation animation;
	protected int currentAction ;
	protected int previousAction ;
	protected boolean facingRight ;
	
	
	//movement
	protected boolean left; 
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean falling;
	protected boolean jumping;
	
	
	// movements atributes
	
	protected double moveSpeed; 
	protected double maxSpeed ;
	protected double stopSpeed ; 
	protected double fallSpeed ;
	protected double maxFallSpeed ;
	protected double jumpStart ; 
	protected double stopJumpSpeed;
	
	
	// constructor 
	public MapObjectme (TileMap tm ){
		tileMap = tm;
		tileSize = tm.getTileSize();
		
	}
	
	
	public boolean intersects (MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		
		return r1.intersects(r2);
		
	}
	public Rectangle getRectangle(){
		return new Rectangle (
				(int) x - cwidth,
				(int)y - cheight,
				cwidth,
				cheight
				);
		}
	
	public void calculateCorners (double x , double y){
		
		// Buscar en el mapa las posiciones (corners)
		int leftTile = (int) (x -cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2 -1) / tileSize;
		int topTile = (int)	(y - cheight / 2) / tileSize ;
		int bottomTile = (int) ( y+cheight/2-1 ) / tileSize ;
		
		
		// topLeftCorner
		int tl = tileMap.getType(topTile, leftTile);
		//topRightCorner
		int tr = tileMap.getType(topTile, rightTile);
		//bottomleftCorner
		int bl = tileMap.getType(bottomTile, leftTile);
		//bottomRightCorner
		int br = tileMap.getType(bottomTile, rightTile);
		
		
		// wil set to true si las localizaciones dan igual a la
		//localizacion de una pared
		topLeft = tl == Tile.BLOCKED;
		topRight =tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		
		
	}
	
	
	public void chechTileMapCollision (){
		
		currCol = (int) x / tileSize ; 
		currRow = (int) y / tileSize ; 
		
		xdest = x + dx ; 
		ydest = y + dy ;
		
		xtemp = x ;
		ytemp = y;
		
		calculateCorners (x,ydest);
		
		// going upward
		if(dy < 0){
			if (topLeft || topRight){
				// stop the movement
				dy = 0;
				// set the player right below donde este
				ytemp = currRow * tileSize + cheight / 2;
				}
			// otherwise podemos seguir subiendo
			else {
				ytemp += dy;
				
			}
			
			// si va pa abajo chequear cuando toque el piso
			if(dy > 0) {
				if(bottomLeft || bottomRight){
					dy = 0;
					falling = false;
					ytemp = (currRow + 1) * tileSize - cheight / 2;	
				
				}
				else {
					ytemp += dy;
					
				}
				
			}
			
			calculateCorners (xdest , y);
			 if (dx < 0 ){
				 if(topLeft || bottomLeft){
					 dx = 0;
					 xtemp = currCol * tileSize + cwidth / 2;
				 }
				 else {
					 xtemp += dx;
				 }
				 
				 if (dx > 0){
					 if(topRight || bottomRight){
						 dx = 0;
						 xtemp = (currCol +1 ) * tileSize - cwidth / 2;
					 } 
					 else {
						 xtemp += dx;
					 }
					 
				 }
				 if (!falling){
					 calculateCorners(x, ydest + 1);
					if (!bottomLeft && !bottomRight){
						falling = true;
						
					}
					 
				 }
				 
			 }
			   
		}
	
	}
	public int getX(){ 
		return (int) x ;
	}
	public int getY(){ 
		return (int) y ;
	}
	public int getWidht(){
		return width;
	}
	public int getHeihgt (){
		return height;
	}
	public int getCWidth (){
		return cwidth;
	}
	public int getCHeight(){ 
		return cheight;
	}
	
	// regular position
	public void setPosition(double x ,double y){
		this.x = x ;
		this.y = y;
	}
	
	public void setVector (double dx , double dy){
		this.dx = dx ;
		this.dy = dy;
	}
	
	// map position 
	public void setMapPosition(){
		xmap = tileMap.getx();
		ymap = tileMap.gety();
		
	}
	
	public void setLeft (boolean b ){
		left = b;
	}
	public void setRight (boolean b ){
		right = b;
	}
	public void setUp (boolean b ){
		up = b;
	}
	public void setDown (boolean b ){
		down = b;
	}
	public void setJumping (boolean b ){
		jumping = b;
	}
	
	// detrmines if the object is on the screen to draw it
	public boolean notOnScreen(){
		//x + xmap is the final player position
		return x + xmap + width < 0 ||
				x + xmap - width > GamePanel.WIDTH ||
				y + ymap + height < 0 ||
				y + ymap - height > GamePanel.HEIGHT;
		
	}
	
	
	
	
	
	
	
	

}
