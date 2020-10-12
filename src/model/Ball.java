package model;

import processing.core.PApplet;

public class Ball {
	
	private int posx, posy;
	private PApplet app;
	
	public Ball(PApplet app, int posx, int posy) {
		this.app = app;
		this.posx = posx;
		this.posy = posy;
	}
	
	public void moverBolita(int direction) {
		
		if(direction == 2) {

			this.posy -= 5;
			System.out.println("posy " + posy);
				
		}else if(direction == -2) {
			this.posy += 5;
			System.out.println("posy " + posy);
		
		}else if(direction ==1) {
			
			this.posx += 5;
			System.out.println("posx " + posx);
			
		}else if(direction == -1) {
			this.posx -= 5;
			System.out.println("posx " + posx);
		}
	}
	
	public void pintarBolita(int posx, int posy) {
		app.fill(255,0,0);
		this.posx = posx;
		this.posy = posy;
		app.ellipse(posx, posy, 50, 50);
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}
	
	
	
	

}
