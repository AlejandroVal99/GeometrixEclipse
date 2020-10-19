package main;
import java.net.InetAddress;
import java.net.UnknownHostException;



import control.GeometrixController;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import view.player.Player;

public class Geometrix extends PApplet {

	
	private int screen;
	private GeometrixController controller;
	private PFont font;
	private boolean continueConnection, connectionJ1, connectionJ2;
	private boolean feedStart, feedConnection;
	
	
	//Imagenes
	private PImage startScreen, startFeedScreen;
	private PImage connectScreen,connectJ1,connectJ2,connectBtn,connectFeedBtn;
	private PImage gameScreen;
	private PImage winScreen;
	//private InetAddress inetAddress;
	String ip;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Geometrix");
	}
	
	
	
	public void settings() {
		size(1280,640);	
	}
	
	public void setup() {
		screen = 0;
		cargarRecursos();
		connectionJ1  = false;
		connectionJ2  = false;
		
		
		controller = new GeometrixController(this,this);
		
					
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
			ip=inetAddress.getHostAddress();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	


	public void draw() {
		
		switch(screen) {
		case 0:
			//scale(-1,0);
			image(startScreen,0,0);
			if(feedStart) {
				image(startFeedScreen,0,0);
			}
			break;
		case 2:
			image(connectScreen,0,0);
			textFont(font);
			fill(158,0,225);
			text(ip.toString(), 432, 179);
			
			if(connectionJ1) {
				image(connectJ1,0,0);
			}
			if(connectionJ2) {
				image(connectJ2,0,0);
			}
			if(connectionJ1 && connectionJ2) {
				image(connectBtn,0,0);
				if(feedConnection) {
					image(connectFeedBtn,0,0);
				}
			}
			
			break;
		case 3:
			image(gameScreen,0,0);
			
			break;
		case 4:
			image(winScreen,0,0);
			
			break;
		}
		
		//background(0);
		
		
		controller.pintarBall();
		
	}
	
	public void mousePressed() {
		System.out.println(" X "+mouseX+" Y "+mouseY);
		
		switch(screen) {
		case 0:
			if(mouseX > 511 && mouseX < 766 && mouseY > 418 && mouseY < 485) {
				screen = 2;
			}
			break;
		case 2:
			if(connectionJ1 && connectionJ2) {
				if(mouseX > 510 && mouseX < 769 && mouseY > 502 && mouseY < 570) {
					screen = 3;
				}
			}
			break;
		case 3:
			break;
		}
		
	//controller.Impact();
	}
	
	public void mouseMoved() {
		//System.out.println("entre");
		switch(screen) {
		case 0:
			if(mouseX > 511 && mouseX < 766 && mouseY > 418 && mouseY < 485) {
				feedStart = true;
			}else {
				feedStart = false;
			}
			break;
		case 2:
			if(mouseX > 510 && mouseX < 769 && mouseY > 502 && mouseY < 570) {
				feedConnection = true;
			}else {
				feedConnection = false;
			}
			break;
		}
		
	}
	
	private void cargarRecursos() {
		font = createFont("../resources/font/spaceage.ttf",70);		
		startScreen = loadImage("../resources/images/backgrounds/startScreen.png");
		startFeedScreen = loadImage("../resources/images/backgrounds/startFeedScreen.png");
		connectScreen = loadImage("../resources/images/backgrounds/connectScreen.png");
		connectJ1 = loadImage("../resources/images/backgrounds/connectJ1.png");
		connectJ2 = loadImage("../resources/images/backgrounds/connectJ2.png");
		connectBtn = loadImage("../resources/images/backgrounds/connectBtn.png");
		connectFeedBtn = loadImage("../resources/images/backgrounds/connectFeedBtn.png");
		gameScreen =  loadImage("../resources/images/backgrounds/gameScreen.png");

	}
	
	public void setConnectionsJ1() {
		connectionJ1 = true;
	}
	public void setConnectionsJ2() {
		connectionJ2 = true;
		
	}
}
