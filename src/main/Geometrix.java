package main;
import java.net.InetAddress;
import java.net.UnknownHostException;



import control.GeometrixController;
import processing.core.PApplet;

public class Geometrix extends PApplet {

	
	private int screen;
	private GeometrixController controller;
	private InetAddress ip;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Geometrix");
	}
	
	
	
	public void settings() {
		size(1280,600);
		
		
		
	}
	
	public void setup() {
		screen = 0;
		controller = new GeometrixController(this);
		try {
			ip = InetAddress.getLocalHost();
			System.out.println(ip.toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw() {
		
		text(ip.toString(), 250, 250);
	}
}
