package view.spaceship;

import processing.core.PApplet;
import processing.core.PImage;

public class Squarlux extends SpaceShip{

	public Squarlux(boolean player, String nickName, PImage inGame, PImage inSelection, PApplet app, int vida,
			PImage mybullet, PImage superShoot) {
		super(player, nickName, inGame, inSelection, app, vida, mybullet, superShoot);
		// TODO Auto-generated constructor stub
		fireRate=1f;
		superRate=4f;
		dano=20;
		danoSuper=50;
		type = "squarlux";
		speed=10;
	}

	

	

	

	

	

	
	
}
