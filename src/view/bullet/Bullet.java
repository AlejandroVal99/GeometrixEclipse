package view.bullet;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
	
	private PApplet app;
	private PImage shoot;
	private float posx;
	private float posy;
	private float speed;
	private int da�o;
	
	public Bullet(float posx, float posy, PImage shoot,PApplet app,int da�o) {
		
		this.posx = posx;
		this.posy = posy;
		this.shoot = shoot;
		this.app = app;
		speed=5;
		this.da�o=da�o;
	}
	
	
	public void DrawBullet(boolean jugador) {
		
		if(jugador) {
			app.image(shoot, posx, posy);
			this.posx+=speed;
			
		}
		
		else {
			app.image(shoot, posx, posy);
			this.posx-=speed;
		}
		
		
	}


	public PImage getShoot() {
		return shoot;
	}


	public void setShoot(PImage shoot) {
		this.shoot = shoot;
	}


	public float getPosx() {
		return posx;
	}


	public void setPosx(float posx) {
		this.posx = posx;
	}


	public float getPosy() {
		return posy;
	}


	public void setPosy(float posy) {
		this.posy = posy;
	}


	public float getSpeed() {
		return speed;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}


	public int getDa�o() {
		return da�o;
	}


	public void setDa�o(int da�o) {
		this.da�o = da�o;
	}
	
	
	 

}
