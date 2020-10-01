package view.spaceship;
import java.util.ArrayList;

import view.bullet.Bullet;

public abstract class SpaceShip {
	protected int vida;
	protected int dano;
	protected int danoSuper;
	protected int posx;
	protected int posy;
	protected ArrayList<Bullet> bullets;
	
	public SpaceShip(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
	
	
}
