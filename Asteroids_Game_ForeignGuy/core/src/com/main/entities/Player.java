package com.main.entities;

import java.awt.geom.Line2D;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.main.game.Game;
import com.main.managers.JukeBox;

public class Player extends SpaceObject {
	private final int MAX_BULLETS = 4;
	private ArrayList<Bullet> bullets;


	private float flamePointx[];
	private float flamePointy[];

	//movements
	private boolean left ;
	private boolean right ;
	private boolean up ;

	private float maxSpeed;
	private float acceleration;
	private float deceleration ; 
	private float acceleratingTimer;

	private boolean hit;
	private boolean dead;

	private float hitTimer;
	private float hitTime;
	private Line2D.Float[] hitLines;
	private Point2D.Float [] hitLinesVector;

	private long score;
	private int extraLives;
	private int requiredScore;


	public Player(ArrayList<Bullet> bullets){
		this.bullets = bullets;

		// la X es siempre la posicion del cabron jugador
		// cojo el Game.width y lo divido este 2 para posicionarlo en el medio
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;

		maxSpeed = 300;
		acceleration = 200;
		deceleration = 10;

		// crear la figura del jugador 
		shapePointx = new float [4];
		shapePointy = new float[4];
		flamePointx = new float[3];
		flamePointy = new float[3];



		// facing upward //como le doy facing upward pues es 90 grados // pi/2
		radians = 3.1415f / 2;

		rotationsSpeed = 3;

		hit = false;
		hitTimer = 0;
		hitTime = 2;
		score = 0;
		extraLives = 3;
		requiredScore =10000;
	}

	private void setShapePoint(){
		// primer punto // cos para x y sin para Y
		// lo que eso significa es que desde el centro 
		// el 8 es la distancias - radianns es la direcions // vete en la direccion de radianes y 8 de distancia

		final int DISTANCE = 8;

		shapePointx[0] = x + MathUtils.cos(radians) * DISTANCE ;
		shapePointy[0] = y + MathUtils.sin(radians) * DISTANCE ;		

		// 4 es some angle - 4 es un angulo * 3.14(que es pi) // este es el punto de la iquierda 
		shapePointx[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * DISTANCE;
		shapePointy[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5 )* DISTANCE ;

		// este 5 es la mitad de la figura es el punto medio
		shapePointx[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
		shapePointy[2] = y + MathUtils.sin(radians + 3.1515f)* 5 ;

		// este es el punto de la derecha
		shapePointx[3] =  x + MathUtils.cos(radians + 4 * 3.1415f / 5) * DISTANCE;
		shapePointy[3] =  y + MathUtils.sin(radians + 4 * 3.1415f / 5) * DISTANCE;		
	}
	private void setFlamePoint(){
		// este es el punto de la derecha
		flamePointx[0] =x + MathUtils.cos(radians - 5 * 3.1415f / 6)* 8;
		flamePointy[0] = y + MathUtils.sin(radians - 5 * 3.1415f / 6) * 8;

		//este es el punto del centro// 
		flamePointx[1] = x + MathUtils.cos(radians - 3.1415f ) * (6 + acceleratingTimer * 50);
		flamePointy[1] = y + MathUtils.sin(radians - 3.1415f) * (6 + acceleratingTimer * 50) ;

		// este es el  punto de la izquierda.
		flamePointx[2] = x + MathUtils.cos(radians + 5 * 3.1415f / 6) * 8;
		flamePointy[2] = y + MathUtils.sin(radians + 5 * 3.1415f / 6) * 8;

	}



	public void setleft(boolean b){
		left = b;
	}
	public void setRight(boolean b){
		right = b;
	}
	public void setUp(boolean b){
		if(b && !up && !hit){
			JukeBox.loop("thruster");
		}else if(!b){
			JukeBox.stop("thruster");
		}
		up = b;
	}
	public boolean isHit(){
		return hit;
	}
	public boolean isDead(){
		return dead;
	}
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		setShapePoint();
	}

	public void reset(){
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;
		setShapePoint();
		hit=dead=false;
	}
	public long getScore(){
		return score;
	}
	public int getLives(){
		return extraLives;
	}
	public void loseLife(){
		extraLives--;
	}
	public void incrementScore(long l){
		score +=l;
	}
	public void resetScore(){
		score = 0;
	}

	public void shoot(){
		if(bullets.size() == MAX_BULLETS)
			return;
		if (!isHit()){
			bullets.add(new Bullet(x, y, radians));
		}
		JukeBox.play("shoot");

	}
	public void hit(){
		if(hit)
			return;
		hit = true;
		directionx = directiony = 0;
		left = right = up = false;

		hitLines = new Line2D.Float[4];
		for(int i = 0,j = hitLines.length - 1;i < hitLines.length;j = i++ ){
			hitLines[i] = new Line2D.Float( 
					shapePointx[i],shapePointy[i],shapePointx[j],shapePointy[j]
					);
			hitLinesVector = new Point2D.Float[4];
			hitLinesVector [0] = new Point2D.Float(
					MathUtils.cos(radians + 1.5f),
					MathUtils.sin(radians + 1.5f)
					);
			hitLinesVector [1] = new Point2D.Float(
					MathUtils.cos(radians - 1.5f),
					MathUtils.sin(radians - 1.5f)
					);
			hitLinesVector [2] = new Point2D.Float(
					MathUtils.cos(radians - 2.8f),
					MathUtils.sin(radians - 2.8f)
					);
			hitLinesVector [3] = new Point2D.Float(
					MathUtils.cos(radians + 2.8f),
					MathUtils.sin(radians + 2.8f)
					);

		}

	}


	public void update (float dt){

		if(hit){
			hitTimer +=dt;
			if(hitTimer > hitTime){
				dead = true;
				hitTimer = 0;
			}
			for (int i = 0; i < hitLines.length; i++) {
				hitLines[i].setLine(
						hitLines[i].x1 + hitLinesVector[i].x * 10 * dt,
						hitLines[i].y1 + hitLinesVector[i].y * 10 * dt,
						hitLines[i].x2 + hitLinesVector[i].x * 10 * dt,
						hitLines[i].y2 + hitLinesVector[i].y * 10 * dt
						);

			}
			return;
		}
		// check extra lives 
		if(score >= requiredScore){
			extraLives ++;
			requiredScore +=10000;
			JukeBox.play("extralife");
		}

		//turning //cuando se rote lo que quiero es cambiar la direcion y la direcion del punto son los radianes
		// y es suma pq todos los angulos para la izquierda son positivos y los angulos hacia la derecha son negativos
		if(left){
			radians +=rotationsSpeed * dt; 
		}
		else if (right){
			radians -=rotationsSpeed * dt; 
		}

		// accelerating 
		if(up){
			// esto tiene q
			directionx += MathUtils.cos(radians) * acceleration * dt;
			directiony +=  MathUtils.sin(radians)* acceleration * dt;
			acceleratingTimer += dt;
			if(acceleratingTimer > 0.1f){
				acceleratingTimer = 0;
			}
			else {
				acceleratingTimer = 0;
			}

		}

		//deceleration
		// vect the speed that we are going
		float vec = (float) Math.sqrt(directionx*directionx +directiony * directiony);
		// esto significa que hay velocidad - el player se esta moviendo
		if(vec > 0){
			directionx -= (directionx / vec) * deceleration * dt;
			directiony -= (directiony / vec) * deceleration * dt ;
		}

		if(vec > maxSpeed){
			directionx = (directionx / vec) * maxSpeed;
			directiony = (directiony / vec) * maxSpeed;
		}

		// set positon 

		x +=directionx * dt;
		y +=directiony * dt;


		// set shape point pero va estar leyendo los puntos en un update osea en un thread 
		// los puntos van a estra cambiando constamenete
		setShapePoint();

		if(up){
			setFlamePoint();
		}

		// screen wrap - que el player aparesca por el otro lado de la pantalla
		wrap();




	}

	public void draw (ShapeRenderer sr){
		sr.setColor(1,1,1,1);

		// todo lo de dibujar tiene que estar dentro de begins y ends 
		sr.begin(ShapeType.Line);

		// check if hit
		if(hit){
			for (int i = 0; i < hitLines.length; i++) {
				sr.line( 
						hitLines[i].x1 ,
						hitLines[i].y1,
						hitLines[i].x2,
						hitLines[i].y2
						);
			}
			sr.end();
			return;

		}


		// draw ship
		for(int i = 0, j = shapePointx.length - 1;i< shapePointx.length;j = i++){
			sr.line(shapePointx[i], shapePointy[i], shapePointx[j], shapePointy[j]);;
		}

		// draw flame 
		if(up){
			for(int i = 0, j = flamePointx.length - 1;i< flamePointx.length;j = i++){
				sr.line(flamePointx[i], flamePointy[i], flamePointx[j], flamePointy[j]);;
			}
		}

		sr.end();


	}



}
