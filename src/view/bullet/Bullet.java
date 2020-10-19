package view.bullet;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
	
	private PApplet app;
	private PImage shoot;
	private float posx;
	private float posy;
	
	
	public Bullet(float posx, float posy, PImage shoot,PApplet app) {
		
		this.posx = posx;
		this.posy = posy;
		this.shoot = shoot;
		this.app = app;
		
	}
	
	 

}
