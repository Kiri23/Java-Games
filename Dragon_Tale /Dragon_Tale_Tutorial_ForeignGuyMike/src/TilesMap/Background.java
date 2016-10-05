package TilesMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	
	private double moveScale;

	public Background (String s, double ms){
		try {
			
			image =ImageIO.read(
					getClass().getResourceAsStream(s)
					);
			moveScale = ms;
			
		} catch (Exception e) {
			System.out.println("No se pudo leer los resources");
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x,double y){
		this.x = (x * moveScale) % GamePanel.WIDTH ;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	//backgroudn to scrool
	public void setVector(double dx,double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	// este metodo que se llama update - se va a llamar cada ves que yo quiero que cambie algo
	public void update(){
		// cambiar la posicion 
		x += dx;
		y +=dy;	
	}
	
	public void draw (Graphics2D g){
		
		
		
		g.drawImage(image,(int)x,(int)y,null);
		
		// esto es lo que causa la animacion del background que se mueven las nubes 
		if (x<0){
			/* Aqui la X siempre va a ser negativa, so si la X es -1
			 * eso signifca que se salio de la pantalla y yo quiero que aparesca por el lado 
			 * opuesto de la pantalla y lo que hago es sumarle la X (Que la x es negativa) al ancho de la pantalla
			 * para que siempre me de un resultado menor que el ancho de la pantalla 
			 * en la posicion X sumarle la X y el width de la pantalla.
			 */
			g.drawImage(image,(int)x + GamePanel.WIDTH,(int)y ,null);
			
		}
		if (x>0){
			
			g.drawImage(image,(int)x - GamePanel.WIDTH,(int)y ,null);
			
		}
		
		
		
		
	}
	
	
	
	
	
}
