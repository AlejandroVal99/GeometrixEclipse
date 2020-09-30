import processing.core.PApplet;

public class Geometrix implements OnMessageListener{
	
	private PApplet app;
	
	private Player p1;
	private Player p2;
	private TCP_J1 tcpJ1;
	private TCP_J2 tcpJ2;
	
	public Geometrix(PApplet app) {
		
		this.app = app;
		tcpJ1 = TCP_J1.getInstance();
		tcpJ2 = TCP_J2.getInstance();
		
	}
	

	@Override
	public void OnMessage(String msg) {
		
		
	}

}
