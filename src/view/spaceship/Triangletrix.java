package view.spaceship;

import processing.core.PApplet;
import processing.core.PImage;

public class Triangletrix extends SpaceShip{

	public Triangletrix(boolean player, String nickName, PImage inGame, PApplet app, int vida, PImage mybullet,PImage superShoot) {
		super(player, nickName, inGame, app, vida, mybullet, superShoot);
		fireRate=1f;
		superRate=4f;
		dano=20;
		danoSuper=50;
		speed=10;
	}

	

	

	

	
	
	
}
