package view.spaceship;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class Circletlex extends SpaceShip{

	public Circletlex(boolean player, String nickName, PImage inGame, PImage inSelection, PApplet app, int vida,
			PImage mybullet, PImage superShoot,SoundFile shootSound) {
		super(player, nickName, inGame, inSelection, app, vida, mybullet, superShoot, shootSound);
		// TODO Auto-generated constructor stub
		type = "circletlex";
		dano=7;
		fireRate=2f;
		superRate=4f;
		danoSuper=15;
		speed=7;
	}






	

 

	
	
	
	
}
