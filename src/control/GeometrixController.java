package control;
import com.google.gson.Gson;

import communication.TCP_J1;
import communication.TCP_J2;
import events.OnMessageListener;
import model.Ball;
import model.Direction;
import model.User;
import model.Vibration;
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
	private Gson gson;
	
	public GeometrixController(PApplet app) {
		
		this.app = app;
		tcpJ1 = TCP_J1.getInstance();
		tcpJ2 = TCP_J2.getInstance();
		tcpJ2.setObserver(this);
		ball = new Ball(app,200,200);
		gson= new Gson();
		
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
	
	

	@Override
	public void OnDirectionReceived(String player, Direction dir) {
		
			ball.moverBolita(dir.getDirection());
			System.out.println(dir.getDirection());
		
			
	}
	
	
	public void pintarBall() {
		ball.pintarBolita();
	}
	
	public void Impact() {
		
		Vibration vibration= new Vibration(true);
		String message= gson.toJson(vibration);
		tcpJ2.sendMessage(message);
		
	}





	@Override
	public void PlayerConnected(String player) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void OnUserReceived(String player, User user) {
		// TODO Auto-generated method stub
		
	}

}
