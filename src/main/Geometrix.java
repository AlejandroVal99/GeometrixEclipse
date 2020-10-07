package main;
import java.net.InetAddress;
import java.net.UnknownHostException;



import control.GeometrixController;
import processing.core.PApplet;

public class Geometrix extends PApplet {

	
	private int screen;
	private GeometrixController controller;
	//private InetAddress inetAddress;
	String ip;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Geometrix");
	}
	
	
	
	public void settings() {
		size(1280,600);	
	}
	
	public void setup() {
		screen = 0;
		new Thread(
				
				()->{
					
					while(true) {
					
						controller = new GeometrixController(this);
					}
				}
				
				).start();
		
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
		background(0);
		text(ip.toString(), 250, 250);
	}
}
