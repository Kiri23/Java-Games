package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.Enemy;
import Entity.Explosion_Enemy;
import Entity.HUD;
import Entity.Player;
import Entity.Enemies.Slugger;
import Main.GamePanel;
import TilesMap.Background;
import TilesMap.TileMap;

import java.awt.Point; 

import Audio.AudioPlayer;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg ;

	private Player player;
	
	private ArrayList <Enemy> enemies;
	private ArrayList <Explosion_Enemy> explosion ;
	private Slugger slugger;
	private HUD hud;
	
	private AudioPlayer bgMusic;
	
	
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
		
	}
	
	
	@Override
	// lee todo de la primera 
	// aqui se iniculaiza todo si una cosa es null es pq no ha pasado por aqui y no se ha inicilaizado
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/grassbg1.gif",0.1 );
		
		
		// load el player nada mas esta en memoria pero no dibujado.
		player = new Player(tileMap);
		player.setPosition (100,100);
		
		populatesEnemies();
		
		explosion = new ArrayList <Explosion_Enemy> ();
		
		
		hud = new HUD(player);
		bgMusic = new AudioPlayer ("/Music/level1-12 (mp3cut.net)-2.mp3");
		bgMusic.play();
		bgMusic.loop();
		
	}
	private void populatesEnemies(){
		enemies = new ArrayList<Enemy>();
		Point [] points = new Point[] {
				new Point(200,100),
				new Point (860,200),
				new Point (1525,200),
				new Point (1680,200),
				new Point (1800,200)
	};
		for (int i = 0; i < points.length; i++) {
			slugger = new Slugger (tileMap);
			slugger.setPosition(points[i].x, points[i].y);
			enemies.add(slugger);
		}
		
		
	}

	// este metod es el que hace que se mueva todo que haiga animacion
	@Override
	public void update() {
		
		// update player
		player.update();
		
		// esto es lo que hace que el mapa se mueva.
		tileMap.setPosition(
				GamePanel.WIDTH /  2- player.getx(),
				GamePanel.HEIGHT / 2 - player.gety()
			);
		
		// set Backgrounds Scroolings 
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies  and check if a enemy attack you
		player.checkAttack(enemies);
		
		// update all enemies 
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()){
				enemies.remove(i);
				i--;
				explosion.add(new Explosion_Enemy(e.getx(), e.gety()));
			}
			
		}
		//update explosion
		for (int i = 0; i < explosion.size(); i++) {
			explosion.get(i).update();
			if(explosion.get(i).shouldRemove()){
				explosion.remove(i);
				i--;
			}
			
		}
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		// draw background 
		bg.draw(g);
		
		
		/*  draw tilemap - aqui es que en realidad se dibuja el mapa...porque esto se llama en el 
		 * gamestatesManger y el gamesStatesmanager llamas los metodos abstract del currenStates.
		 */
		tileMap.draw(g);
		// draw player
		player.draw(g);
		
		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
			
		}
		
		// draw explosions 
		for (int i = 0; i < explosion.size(); i++) {
			explosion.get(i).setMapPosition((int)tileMap.getx(),(int)tileMap.gety());
			explosion.get(i).draw(g);
		}
		
		
		// draw hud- tiene que ser lo ultimo pq van encima de todo
		hud.draw(g);
		
		
	} 
	
	
	@Override
	// este metodo se llama obligado en el 	GamestatesManger.
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if (k == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if (k == KeyEvent.VK_UP){
			player.setUp(true);
		}
		if (k == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		if (k == KeyEvent.VK_SPACE){
			player.setJumping(true);
		}
		if (k == KeyEvent.VK_E){
			player.setGliding(true); 
		}
		if (k == KeyEvent.VK_R){
			player.setScratching();
		}
		if (k == KeyEvent.VK_D){
			player.setFiring();
		}
		  
		
		
		
		
	}
	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if (k == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if (k == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if (k == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		if (k == KeyEvent.VK_SPACE){
			player.setJumping(false);
		}
		if (k == KeyEvent.VK_E){
			player.setGliding(false); 
		}
		
		
	
	}
	

}
