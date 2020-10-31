package view.spaceship;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class Triangletrix extends SpaceShip{

	public Triangletrix(boolean player, String nickName, PImage inGame, PImage inSelection, PApplet app, int vida,
			PImage mybullet, PImage superShoot,SoundFile shootSound) {
		super(player, nickName, inGame, inSelection, app, vida, mybullet, superShoot,shootSound);
		// TODO Auto-generated constructor stub
		fireRate=1f;
		superRate=4f;
		dano=6;
		danoSuper=15;
		speed=10;
		type = "tringletrix";

	}





	

	

	
	
	
}
