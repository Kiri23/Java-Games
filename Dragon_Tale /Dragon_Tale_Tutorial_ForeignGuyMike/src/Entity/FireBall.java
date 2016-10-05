package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TilesMap.TileMap;


public class FireBall extends MapObject {

	private boolean hit;
	private boolean remove ;
	// los fireballs - el ataque como tal
	private BufferedImage [] sprites;
	private BufferedImage [] hitSprites;

	public FireBall (TileMap tm ,boolean right){
		super(tm);
		
		facingRight = right;
		
		moveSpeed = 3.8;
		// para saber en que direcion tirar la bolita de fuego.
		if(right)
			// cambiara la localizacion por su velocidad.
			dx = moveSpeed;
		else 
			dx -=moveSpeed;

		width = 30;
		height = 30;
		cwidth = 14; 
		cheight = 14;

		// Load sprites 
		try {
			// esto es para leer unaimages una sprites
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Player/fireball.gif")
					);

			sprites = new BufferedImage [4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
						i * width,
						0,
						width,
						height
						);
			}
			hitSprites = new BufferedImage [3];
			for (int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(
						i * width,
						height,
						width,
						height
						);
			}

			animation = new Animation();
			animation.setFrame(sprites);
			animation.setDelay(70);

		} catch (Exception e) {
			System.out.println("No se pudo leer los fireBall- desde FireBall");
			e.printStackTrace();
		}

	}
	// primero hay que activar eso para saber que un fireballs toco una pared y poner hit true y poder entrar en el ciclo y poner remove true
	// esto es de hit animations - load Sprites.
	public void setHit(){
		if(hit)
			return;
		
		hit = true;
		animation.setFrame(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	public boolean shouldRemove(){
		return remove;
		
	}
	public void update(){
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hit){
			setHit();
			
		}
		
		
		animation.update();
		
		if (hit && animation.hasPlayedOnce()){
			remove = true;
		}
		
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}
	
	



}