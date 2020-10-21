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
	
	protected boolean player;//True player 1, false player2
	protected ArrayList<Bullet> bullets;
	protected PImage inGame,inSelect,myBullet;
	protected float speed;
	
	//0.08 caso default
	
	public SpaceShip(boolean player,String nickName, PImage inGame, PApplet app,int vida,PImage mybullet) {
		
		this.player = player;
		this.app = app;
		this.myBullet=mybullet;
		this.inGame = inGame;
		this.inSelect = inSelect;
		this.nickName = nickName;
		this.vida=vida;
		bullets = new ArrayList<Bullet>();
		vidaTotal = vida;
		posy = app.height/2;
		chagePosx();
		speed = 15;
		
		
	}
	
	

	
	public void chagePosx() {
		if(player) {
			posx = (app.width/4) *1;
		}else {
			posx = (app.width/4) *3;
		}
	}
	public void drawInSelection() {
		
		if(player){
			
		}else {
			
		}
		
	}
	
	public void drawInGame() {
		
		posx = app.lerp(posx, nPosx, (float) 0.08);
		posy = app.lerp(posy,nPosy,(float) 0.08);
		app.image(inGame, posx, posy);
		
		app.textSize(25);
		
		if(player) {
			
			app.text(nickName,20 , 25);
			app.rect(20,40,(float)((vida*158)/vidaTotal),7);
			
		}/*else {
			app.text(nickName,1250,20);
			app.rect(1250-(float)((vida*158)/vidaTotal),40,(float)((vida*158)/vidaTotal),7);
		}
		*/
		for(int i=0;i<bullets.size();i++) {
			
			bullets.get(i).DrawBullet(player);
		}
		
	}
	public void moveSpaceship(int direc) {
		
		switch(direc) {
		case 2:

			nPosy =this.posy -=speed;
			//System.out.println("posy " + posy);
		break;		
		case -2: 
			nPosy = this.posy += speed;
			//System.out.println("posy " + posy);
		break;
		case 1:
			
			nPosx =this.posx += speed;
			//System.out.println("posx " + posx);
		break;	
		case -1:
			nPosx = this.posx -= speed;
			//System.out.println("posx " + posx);
		break;
		
		default:
			//System.out.println("default");
			break;
		
		}
	}
	
	public void newBullet() {
		
		Bullet bullet = new Bullet(this.posx+inGame.width,this.posy+(inGame.height/2),this.myBullet,app);
		bullets.add(bullet);
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
		this.vida = vida;
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
