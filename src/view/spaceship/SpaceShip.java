package view.spaceship;

import java.util.ArrayList;
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
	protected float posx;
	protected float posy;
	protected float nPosx;
	protected float nPosy;
	protected boolean player;// True player 1, false player2
	protected ArrayList<Bullet> bullets;
	protected PImage inGame, inSelect, myBullet;
	protected float speed;
	protected float fireRate;
	protected float nextFire;
	protected float superRate;
	protected float nextSuper;
	protected PImage superShoot;
	protected String type;

	// 0.08 caso default

	public SpaceShip(boolean player, String nickName, PImage inGame, PImage inSelection, PApplet app, int vida,
			PImage mybullet, PImage superShoot) {

		this.player = player;
		this.superShoot = superShoot;
		this.app = app;
		this.myBullet = mybullet;
		this.inGame = inGame;
		this.inSelect = inSelection;
		this.nickName = nickName;
		this.vida = vida;
		bullets = new ArrayList<Bullet>();
		vidaTotal = vida;
		posy = app.height / 2;
		nPosy=posy;
		chagePosx();
		type = "nothing";
		speed = 10;
		nextFire = 0;
		fireRate = 0;
		nextSuper = 0;
		superRate = 0;
		dano = 0;
		danoSuper = 0;
		
	}

	public void chagePosx() {
		if (player) {
			posx = (app.width / 4) * 1;
			nPosx=posx;
		} else {
			posx = (app.width / 4) * 3;
			nPosx=posx;
		}
	}

	public void drawInSelection() {
		
		switch (type) {
		case "tringletrix":
			app.fill(5,246,246);
			break;
		case "cricletrex":
			app.fill(158,0,255);
			break;
		case "diamondrox":
			app.fill(252,117,57);
			break;
		case "squarlux":
			app.fill(80,252,0);
			break;
		}
		app.image(inSelect, posx-(inSelect.width/2), 185);
		app.textSize(35);
		app.text(nickName, posx-(inSelect.width/2), 520);
		app.textSize(20);

		

	}

	public void drawInGame() {

		
		posx = PApplet.lerp(posx, nPosx, 0.02f);
		posy = PApplet.lerp(posy,nPosy,0.02f);

		app.image(inGame, posx, posy);

		if (app.frameCount % 10 == 0) {
			nextFire--;

		}

		if (app.frameCount % 40 == 0) {

			nextSuper--;

		}

		app.textSize(25);

		if (player) {

			app.text(nickName, 20, 25);
			app.rect(20, 40, (float) ((vida * 158) / vidaTotal), 20, 8);

		} else {
			app.text(nickName, 1220, 20);
			app.rect(1250 - (float) ((vida * 158) / vidaTotal), 40, (float) ((vida * 158) / vidaTotal), 20, 8);
		}
		drawLifeBars();
		drawBullets();

	}

	protected void drawLifeBars() {
		switch (type) {
		case "tringletrix":
			app.fill(5,246,246);
			break;
		case "cricletrex":
			app.fill(158,0,255);
			break;
		case "diamondrox":
			app.fill(252,117,57);
			break;
		case "squarlux":
			app.fill(80,252,0);
			break;
		}
		if (player) {

			app.text(nickName, 20, 25);
			app.rect(20, 40, (float) ((vida * 158) / vidaTotal), 20, 8);

		} else {
			app.text(nickName, 1250, 20);
			app.rect(1250 - (float) ((vida * 158) / vidaTotal), 40, (float) ((vida * 158) / vidaTotal), 20, 8);
		}
	}

	private void drawBullets() {
		for (int i = 0; i < bullets.size(); i++) {

			bullets.get(i).DrawBullet(player);
		}
	}

	public void moveSpaceship(int direc) {

		switch (direc) {
		case 2:
			if(nPosy-speed>=0) {
			nPosy = this.posy -= speed;
			// System.out.println("posy " + posy);
			}
			break;
		case -2:
			if(nPosy+speed>app.width) {
			nPosy = this.posy += speed;
			// System.out.println("posy " + posy);
			}
			break;
		case 1:
			if(nPosx+speed<app.width) {
			nPosx = this.posx += speed;
			// System.out.println("posx " + posx);
			}
			break;
		case -1:
			
			if(nPosx-speed>=0) {
			nPosx = this.posx -= speed;
			// System.out.println("posx " + posx);
			}
			break;

		default:
			// System.out.println("default");
			break;

		}
	}

	public void newBullet(boolean superShoot) {

		if (superShoot) {
			if (nextSuper <= 0) {
				Bullet superBullet = new Bullet(this.posx + inGame.width, this.posy + (inGame.height / 2),
						this.superShoot, app, danoSuper);
				bullets.add(superBullet);
				nextSuper = superRate;
			}
		}

		else {

			if (nextFire <= 0) {
				Bullet bullet = new Bullet(this.posx + inGame.width, this.posy + (inGame.height / 2), this.myBullet,
						app, dano);
				bullets.add(bullet);
				nextFire = fireRate;

			}

		}

	}
public void drawWin() {
		
		app.image(inSelect, (app.width/2)-(inSelect.width/2), 150);
		app.text(nickName,(app.width/2)-(inSelect.width/2) , 500);

	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida -= vida;
	}

	public int getVidaTotal() {
		return vidaTotal;
	}

	public void setVidaTotal(int vidaTotal) {
		this.vidaTotal = vidaTotal;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getDanoSuper() {
		return danoSuper;
	}

	public void setDanoSuper(int danoSuper) {
		this.danoSuper = danoSuper;
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

	public float getnPosx() {
		return nPosx;
	}

	public void setnPosx(float nPosx) {
		this.nPosx = nPosx;
	}

	public float getnPosy() {
		return nPosy;
	}

	public void setnPosy(float nPosy) {
		this.nPosy = nPosy;
	}

	public boolean isPlayer() {
		return player;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public PImage getInGame() {
		return inGame;
	}

	public void setInGame(PImage inGame) {
		this.inGame = inGame;
	}

	public PImage getInSelect() {
		return inSelect;
	}

	public void setInSelect(PImage inSelect) {
		this.inSelect = inSelect;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public PImage getMyBullet() {
		return myBullet;
	}

	public void setMyBullet(PImage myBullet) {
		this.myBullet = myBullet;
	}

	

}
