package view.spaceship;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class Diamondrox extends SpaceShip{

	public Diamondrox(boolean player, String nickName, PImage inGame, PImage inSelection, PApplet app, int vida,
			PImage mybullet, PImage superShoot,SoundFile shootSound) {
		super(player, nickName, inGame, inSelection, app, vida, mybullet, superShoot,shootSound);
		// TODO Auto-generated constructor stub
		fireRate=2f;
		superRate=5f;
		dano=8;
		danoSuper=25;
		speed=9;
		type = "diamondrox";
	}



	
	
	
	
}
