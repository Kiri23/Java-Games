package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TilesMap.TileMap;
import Entity.Animation;
import Entity.Enemy;

public class Slugger extends Enemy {

	private BufferedImage[] sprites;



	public Slugger (TileMap tm){
		super(tm);

		moveSpeed = 0.3;
		maxSpeed = 0.3 ;
		fallSpeed = 0.2 ;
		maxFallSpeed = 10.0;

		width = 30;
		height =30;
		cwidth = 20;
		cheight = 20;

		health = maxHealth = 2 ;
		damage = 1;

		// load Sprites 
		try {
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Enemies/slugger.gif"
							)
					);

			sprites = new BufferedImage [3];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
						i * width,
						0,
						width,
						height
						);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo leer el enemigo Slugger - Desde Slugger class");
		}
		animation = new Animation();
		animation.setFrame(sprites);
		animation.setDelay(300);

		right = true;	
		facingRight = true;
		
	}
	private void getNextPosition(){
		// esto simplmente lo que hace es que verifica si hay chocado con una pared y vuelve para atras
        //movement
		if(left) {
			// dx cambiar la distancia del mapa restandole el moveSpeed.
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		// falling 
		if (falling){
			dy += fallSpeed;	
		}
		
	}

	public void update(){
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000 ;
			if(elapsed > 400){
				flinching = false ;
			}
			
		}
		// if it hits a wall, go to the other directions
		//whem something hits a wall the dx automatic get to 0
		if (right && dx ==0 ){
			right = false;
			left = true ;
			facingRight = false;
			
		}
		else if (left && dx == 0){
			right = true;
			left = false;
			facingRight = true;
		}
		
		animation.update();
		
		
	}

	public void draw(Graphics2D g){
		// if (notOnScreen())	return;
		setMapPosition(); 

		super.draw(g);




	}




}
