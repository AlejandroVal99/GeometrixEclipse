package view.spaceship;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.NA;

import processing.core.PApplet;
import processing.core.PImage;
import view.bullet.Bullet;

public abstract class SpaceShip {
	protected PApplet app;
	protected String nickName;
	protected int vida;
	protected int vidaTotal;
	protected int dano;
	protected int danoSuper;
	protected int posx;
	protected int posy;
	protected boolean player;//True player 1, false player2
	protected ArrayList<Bullet> bullets;
	protected PImage inGame,inSelect;
	protected float speed;
	
	
	public SpaceShip(boolean player,String nickName, PImage inGame, PImage inSelect, PApplet app) {
		
		this.player = player;
		this.app = app;
		this.inGame = inGame;
		this.inSelect = inSelect;
		bullets = new ArrayList<Bullet>();
		vidaTotal = vida;
		posy = app.height/2;
		chagePosx();
		
		
	}
	
	protected void chagePosx() {
		if(player) {
			posx = (app.width/4) *1;
		}else {
			posx = (app.width/4) *3;
		}
	}
	protected void drawInSelection() {
		
		if(player){
			
		}else {
			
		}
		
	}
	
	protected void drawInGame() {
		
		app.image(inGame, posx, posy);
		if(player) {
			app.text(nickName,20 , 20);
			app.rect(20,40,(float)((vida*158)/vidaTotal),7);
		}else {
			app.text(nickName,1250,20);
			app.rect(1250-(float)((vida*158)/vidaTotal),40,(float)((vida*158)/vidaTotal),7);
		}
		
		
	}
	protected void moveSpaceship(int direc) {
		
		switch(direc) {
		case 2:

			this.posy -=speed;
			System.out.println("posy " + posy);
		break;		
		case -2: 
			this.posy += speed;
			System.out.println("posy " + posy);
		break;
		case 1:
			
			this.posx += speed;
			System.out.println("posx " + posx);
		break;	
		case -1:
			this.posx -= speed;
			System.out.println("posx " + posx);
		break;
		
		}
	}
	
}
