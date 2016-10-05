package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel 
	implements Runnable, KeyListener{
	
	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	
	
	public GamePanel() {
		// es el constructor de Jpanel.. crea un nuevo JPanel
		super();
		// Jpanel setPreferedSize.
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this); // -- addMouseListner - eventListener.
			thread.start();
		}
	}
	
	private void init() {
		
		image = new BufferedImage(
					WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB
				);
		
		// esto es lo que hace graficar para todos los draw.
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		// aqui es que se empieza a dibujar todo.
		gsm = new GameStateManager();
		
	   
		
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		int tick = 0;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			tick++;
			
			// tiempo que ha pasado
			elapsed = System.nanoTime() - start;
			long time = System.nanoTime() - start ;
			time += time;
			if(time >=100){
				tick = 0;
				time = 0;
				
			}
			
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			// para que se vea bien el juego buenas animaciones 
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE,
				null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

}
















