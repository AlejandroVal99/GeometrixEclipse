package main;
import control.GeometrixController;
import processing.core.PApplet;

public class Geometrix extends PApplet {

	
	private int screen;
	private GeometrixController controller;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Geometrix");
	}
	
	
	
	public void settings() {
		size(1280,600);
		screen = 0;
		
		
	}
	
	public void setup() {
		
		controller = new GeometrixController(this);
	}
	
	public void draw() {
		
		
	}
}
