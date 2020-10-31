package view.spaceship;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class Squarlux extends SpaceShip{

	public Squarlux(boolean player, String nickName, PImage inGame, PImage inSelection, PApplet app, int vida,
			PImage mybullet, PImage superShoot,SoundFile shootSound) {
		super(player, nickName, inGame, inSelection, app, vida, mybullet, superShoot,shootSound);
		// TODO Auto-generated constructor stub
		fireRate=3f;
		superRate=4f;
		dano=10;
		danoSuper=15;
		type = "squarlux";
		speed=6;
	}

	

	

	

	

	

	
	
}
