package control;
import communication.TCP_J1;
import communication.TCP_J2;
import events.OnMessageListener;
import model.Ball;
import model.Direction;
import processing.core.PApplet;
import processing.core.PImage;
import view.player.Player;


public class GeometrixController implements OnMessageListener{
	
	private PApplet app;
	private PImage imagen, selection;
	private Ball ball;
	
	private Player p1;
	private Player p2;
	private TCP_J1 tcpJ1;
	private TCP_J2 tcpJ2;
	
	public GeometrixController(PApplet app) {
		
		this.app = app;
		tcpJ1 = TCP_J1.getInstance();
		tcpJ2 = TCP_J2.getInstance();
		tcpJ2.setObserver(this);
		ball = new Ball(app, 250, 350);
		
	}
	

	@Override
	public void OnMessage(String msg) {
		
		
	}


	@Override
	public void OnShootReceived(String player,boolean superShoot) {
		
		switch(player) {
		
		case "player1":
			System.out.println("Disparo el 1");
			break;
			
		case "player2":
			if(superShoot) {
				
				System.out.println("super Disparo el 2");	
			}
			else {
				
				System.out.println("Disparo el 2");

			}
			

			break;
		}
		
	}
	
	public void moverBola(int letra) {
		ball.moverBolita(letra);
	}


	@Override
	public void OnDirectionReceived(String player, Direction dir) {
		if(ball != null) {
			ball.moverBolita(dir.getDirection());
		}
		
			
	}
	
	
	public void pintarBall(int posx, int posy) {
		ball.pintarBolita(posx,posy);
	}

}
