package main;
import java.net.InetAddress;
import java.net.UnknownHostException;
import control.GeometrixController;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.sound.*;

public class Geometrix extends PApplet {

	//Screens
	private int screen;
	private GeometrixController controller;
	private PFont font;
	private boolean continueConnection, connectionJ1, connectionJ2;
	private boolean feedStart, feedConnection;
	private int tamX;
	private int startTime;
	
	
	//Imagenes
	private PImage startScreen, startFeedScreen;
	private PImage connectScreen,connectJ1,connectJ2,connectBtn,connectFeedBtn;
	private PImage selectScreen;
	private PImage instructionsScreen;
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
		tamX = 0;
		
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
		case 0://Start Screen
			//scale(-1,0);
			image(startScreen,0,0);
			tamX++;
			noStroke();
			fill(158,0,225);
			rect(width/2-225,450,tamX,50,10);
			if(frameCount % 300 ==0) {
				screen=2;
			}
		
			break;
		case 2://Conection Screen
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
				screen++;	
			}
			
			break;
			
		case 3://Selection Screen
			image(selectScreen,0,0);
			startTime = millis();
			controller.drawinSelection();
			break;
			
		case 4://Instructions Screen
			image(instructionsScreen,0,0);
			int finishTime = millis();

			int seg = 11 - (int) (finishTime - startTime)/1000;
			textFont(font);
			fill(158,0,225);
			text(seg, 594, 235);
			
			if((finishTime - startTime)>11000) {
				controller.SendConfirmation();
				screen =5;

			}
		 
			 break;
			 
			
		case 5://Game Screen
			image(gameScreen,0,0);
			controller.drawSpace();
			
			break;
		case 6://Win Screen
			image(winScreen,0,0);
			controller.drawWin();
			break;
		}
		
		//background(0);
			
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
		selectScreen = loadImage("../resources/images/backgrounds/selectionScreen.png");
		gameScreen =  loadImage("../resources/images/backgrounds/gameScreen.png");
		instructionsScreen = loadImage("../resources/images/backgrounds/instructionsScreen.png");
		winScreen = loadImage("../resources/images/backgrounds/winScreen.png");


	}
	
	public void setConnectionsJ1() {
		connectionJ1 = true;
	}
	public void setConnectionsJ2() {
		connectionJ2 = true;
		
	}
	
	public void setScreen(int nScreen) {
		screen = nScreen;
	}
	
}
