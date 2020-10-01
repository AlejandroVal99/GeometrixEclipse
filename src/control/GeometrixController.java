package control;
import communication.TCP_J1;
import communication.TCP_J2;
import events.OnMessageListener;
import processing.core.PApplet;
import view.player.Player;

public class GeometrixController implements OnMessageListener{
	
	private PApplet app;
	
	private Player p1;
	private Player p2;
	private TCP_J1 tcpJ1;
	private TCP_J2 tcpJ2;
	
	public GeometrixController(PApplet app) {
		
		this.app = app;
		tcpJ1 = TCP_J1.getInstance();
		tcpJ2 = TCP_J2.getInstance();
		
	}
	

	@Override
	public void OnMessage(String msg) {
		
		
	}


	@Override
	public void OnShootReceived(String player) {
		
		switch(player) {
		
		case "player1":
			break;
			
		case "player2":
			break;
		}
		
	}

}
