package com.main.gamestates;

import java.util.ArrayList;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.main.entities.Asteroid;
import com.main.entities.Bullet;
import com.main.entities.FlyingSaucer;
import com.main.entities.Particle;
import com.main.entities.Player;
import com.main.game.Game;
import com.main.managers.GameStateManager;
import com.main.managers.JukeBox;

public class PlayState extends GameState {

	private SpriteBatch sb;
	private ShapeRenderer sr;

	private BitmapFont font;
	private Player hudPlayer;

	private Player player;
	private ArrayList <Bullet> bullets;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Bullet> enemyBullets;

	private FlyingSaucer flyingSaucer;
	private float fsTimer;
	private float fsTime;


	private ArrayList<Particle> particles;

	private int level;
	private int totalAsteroids;
	private int numAsteroidsLeft;

	private float maxDelay;
	private float minDelay;
	private float currentDelay;
	private float bgTimer;
	private boolean playLowPulse;


	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@SuppressWarnings("deprecation")
	public void init() {

		sb = new SpriteBatch();
		sr = new ShapeRenderer();

		// set font
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/Asteroids_Game_ForeignGuy/core/res/fonts/Hyperspace Bold.ttf")
				);
		font = gen.generateFont(20);

		bullets = new ArrayList<Bullet>();

		player = new Player(bullets);

		asteroids = new ArrayList<Asteroid>();

		particles = new ArrayList<Particle> ();

		level = 1;
		spawnAsteroids();
		hudPlayer = new Player(null);

		fsTimer = 0;
		fsTime = 5;
		enemyBullets = new ArrayList<Bullet>();


		// set up bg music
		maxDelay = 1;
		minDelay = 0.25f;
		currentDelay = maxDelay;
		bgTimer = maxDelay;
		playLowPulse = true;

	}

	private void createParticles(float x, float y){
		for (int i = 0; i < 6; i++) {
			particles.add(new Particle(x,y));
		}
	}

	private void spliAsteroids(Asteroid a){
		createParticles(a.getx(),a.gety());
		numAsteroidsLeft --;
		currentDelay = ((maxDelay - minDelay) * numAsteroidsLeft / totalAsteroids) + minDelay; 
		if(a.getType() == Asteroid.LARGE){
			asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.MEDIUM));	
			asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.MEDIUM));	

		}
		if(a.getType() == Asteroid.MEDIUM){
			asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.SMALL));	
			asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.SMALL));	
		}



	}

	private void spawnAsteroids(){
		asteroids.clear();

		int numToSpawn = 4 + level - 1;
		totalAsteroids = numToSpawn * 7;
		numAsteroidsLeft = totalAsteroids ;
		currentDelay = maxDelay;

		for (int i = 0; i < numToSpawn; i++) {
			float x = MathUtils.random(Game.WIDTH);
			float y = MathUtils.random(Game.HEIGHT);

			float directionx = x - player.getx();
			float directiony = y - player.gety();
			float distance = (float) Math.sqrt(directionx*directionx + directiony + directiony); 

			while (distance < 100){
				x = MathUtils.random(Game.WIDTH);
				y = MathUtils.random(Game.HEIGHT);	
				directionx = x - player.getx();
				directiony = y - player.gety();
				distance = (float) Math.sqrt(directionx*directionx + directiony + directiony); 

			}
			asteroids.add(new Asteroid(x,y,Asteroid.LARGE));

		}


	}

	public void update(float dt) {
		// get user input
		handleInput();

		//player is dead 
		if(asteroids.size()==0){
			level++;
			spawnAsteroids();
		}

		// update player
		player.update(dt);
		if(player.isDead()){
			player.reset();
			player.loseLife();
			flyingSaucer = null;
			JukeBox.stop("smallsaucer");
			JukeBox.stop("largesaucer");

			if(player.getLives() ==0){
				JukeBox.stopAll();
				gsm.setState(GameStateManager.GameOver);
				System.out.println(player.getScore());
				player.resetScore();

			}
			return;
		}

		// update player bullets
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(dt);
			if(bullets.get(i).shouldRemove()){
				bullets.remove(i);
				i--;

			}
			//update flying saucers
			if (flyingSaucer == null ){
				fsTimer += dt;
				if(fsTimer >= fsTime){
					fsTimer = 0;
					int type = MathUtils.random() < 0.5 ?
							FlyingSaucer.SMALL : FlyingSaucer.LARGE;
					int direction = MathUtils.random() < 0.5 ? 
							FlyingSaucer.RIGHT : FlyingSaucer.LEFT;
					flyingSaucer = new FlyingSaucer(type,direction,player,enemyBullets);

				}
			}
			else {
				flyingSaucer.update(dt);
				if(flyingSaucer.shouldRemove()){
					flyingSaucer = null;
					JukeBox.stop("smallsaucer");
					JukeBox.stop("largesaucer");

				}
			}
			// update flyingSaucerBullet
			for (int j = 0; j < enemyBullets.size(); j++) {
				enemyBullets.get(j).update(dt);
				if(enemyBullets.get(j).shouldRemove()){
					enemyBullets.remove(j);
					j--;
				}

			}


		}
		// update asteroids 
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update(dt);
			if(asteroids.get(i).shouldRemove()){
				asteroids.remove(i);
				i--;

			}
		}
		for (int i = 0; i <particles.size() ; i++) {
			particles.get(i).update(dt);
			if(particles.get(i).shouldRemove()){
				particles.remove(i);
				i--;
			}
		}



		// check collision
		checkCollision();

		// play bg music
		bgTimer += dt;
		if(!player.isHit() && bgTimer >=currentDelay ){
			if(playLowPulse)
				JukeBox.play("pulselow");
			else 
				JukeBox.play("pulsehigh");
			playLowPulse = !playLowPulse;
			bgTimer = 0;
		}

	}
	private void checkCollision(){

		//player-asteroid collison
		if(!player.isHit())
			for (int i = 0; i < asteroids.size(); i++) {
				Asteroid a = asteroids.get(i);
				//if player intersect asteroids 
				if(a.intersects(player)){
					player.hit();
					asteroids.remove(i);
					i--;
					spliAsteroids(a);
					JukeBox.play("explode");

				}
			}

		// bullets - asteroids collsion
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			for (int j = 0; j < asteroids.size(); j++) {
				Asteroid a = asteroids.get(j);
				// if asteroids contains point b (bullets)
				if(a.contains(b.getx(), b.gety())){
					bullets.remove(i);
					i--;
					asteroids.remove(j);
					j--;
					spliAsteroids(a);
					// increment Player Score
					player.incrementScore(a.getScore());
					JukeBox.play("explode");

					break;
				}

			}
		}
		// player - flying saucer collison 
		if(flyingSaucer != null){
			if(player.intersects(flyingSaucer)){
				player.hit();
				createParticles(player.getx(), player.gety());
				createParticles(flyingSaucer.getx(), flyingSaucer.gety());
				flyingSaucer = null;
				JukeBox.stop("smallsaucer");
				JukeBox.stop("largesaucer");
				JukeBox.play("explode");
			}

		}
		// bullet- flying saucer collision
		if(flyingSaucer != null){
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				if(flyingSaucer.contains(b.getx(), b.gety())){
					bullets.remove(i);
					i--;
					createParticles(flyingSaucer.getx(), flyingSaucer.gety());
					player.incrementScore(flyingSaucer.getScore());
					flyingSaucer = null;
					JukeBox.stop("smallsaucer");
					JukeBox.stop("largesaucer");
					JukeBox.play("explode");
					break;
				}

			}
		}
		// player- enemy bullets collison 
		if(!player.isHit()){
			for (int j = 0; j < enemyBullets.size(); j++) {
				Bullet a = enemyBullets.get(j);
				if(player.contains(a.getx(), a.gety())){
					player.hit();
					enemyBullets.remove(j);
					j --;
					JukeBox.play("explode");
					break;

				}
			}
		}

		//flying saucer -asteroid collison 
		if(flyingSaucer != null){
			for (int i = 0; i < asteroids.size(); i++) {
				Asteroid b = asteroids.get(i);
				if(b.intersects(flyingSaucer)){
					asteroids.remove(i);
					i--;
					spliAsteroids(b);
					createParticles(b.getx(), b.gety());
					createParticles(flyingSaucer.getx(),flyingSaucer.gety());
					flyingSaucer = null;
					JukeBox.stop("smallsaucer");
					JukeBox.stop("largesaucer");
					JukeBox.play("explode");
					break;
				}
			}
		}
		
		// asteroid - enemy bullet collison 
		for (int i = 0; i < enemyBullets.size(); i++) {
			Bullet b = enemyBullets.get(i);
			for (int j = 0; j < asteroids.size(); j++) {
				Asteroid a = asteroids.get(j);
				if (a.contains(b.getx(), b.gety())){
					asteroids.remove(j);
					j--;
					spliAsteroids(a);
					enemyBullets.remove(i);
					i--;
					createParticles(a.getx(), a.gety());
					JukeBox.play("explode");
					break;
					
				}
				
				
			}
			
			
		}






	}


	public void draw() {

		player.draw(sr);

		// draw bullets 
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(sr);

		}
		// draw FlyingSaucer
		if(flyingSaucer !=null){
			flyingSaucer.draw(sr);

		}
		// draw fyingSaucerBullet
		for (int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).draw(sr);

		}


		// draw asteroids
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(sr);

		}
		// draw particles
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(sr);
		}

		// draw Score
		sb.setColor(1,1,1,1);
		sb.begin();
		font.draw(sb,Long.toString(player.getScore()),40,390);

		sb.end();

		// draw lives
		for (int i = 0; i < player.getLives(); i++) {
			hudPlayer.setPosition(40 + i * 15 ,360);
			hudPlayer.draw(sr);
			if(player.getLives() < 1){
				hudPlayer.setPosition(40 + i * 15 ,360);
				hudPlayer.draw(sr);
			}
		}
	}


	public void handleInput() {
		player.setleft(Gdx.input.isKeyPressed(Keys.LEFT));
		player.setRight(Gdx.input.isKeyPressed(Keys.RIGHT));
		player.setUp(Gdx.input.isKeyPressed(Keys.UP));
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			player.shoot();
		}
		if(Gdx.input.isKeyJustPressed(Keys.R))
			gsm.setState(GameStateManager.MENU);

	}



	public void dispose() {}

}
