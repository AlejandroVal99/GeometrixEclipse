package model;

import processing.core.PApplet;

public class Ball {
	private float posx, posy;
	private PApplet app;
	private float speed=0.3f;
	private float newposx, newposy;
	
	public Ball(PApplet app,int posx,int posy) {
		this.app = app;
		this.posx = posx;
		this.posy = posy;
		newposx = posx;
		newposy = posy;
	}
	
	public void moverBolita(int direction) {
		
		if(direction == 2) {
			if (posy <= 0 || posy >= app.height) {

			} else {
			newposy = this.posy -=speed;
			}
			//System.out.println("posy " + posy);
				
		}else if(direction == -2) {
			if (posy <= 0 || posy >= app.height) {

			} else {
			newposy =	this.posy += speed;
			}
			
			//System.out.println("posy " + posy);
		
		}else if(direction ==1) {
			if (posx <= 0 || posx >= app.width) {

			} else {
			newposx = this.posx -= speed;
			}
			//System.out.println("posx " + posx);
			
		}else if(direction == -1) {
			if (posx <= 0 || posx >= app.width) {

			} else {
				
			newposx = this.posx += speed;
			}
			//System.out.println("posx " + posx);
		}
	}
	
	public void pintarBolita() {
		app.fill(255,0,0);
		posx = app.lerp(posx, newposx, (float) 0.1);
		posy = app.lerp(posy,newposy,(float) 0.1);
		app.ellipse(posx, posy, 50, 50);
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

	public void setApp(PApplet app) {
		this.app = app;
	}
	
	
	
	

}
