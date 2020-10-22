package control;

import com.google.gson.Gson;

import communication.TCP_J1;
import communication.TCP_J2;
import events.OnMessageListener;
import main.Geometrix;
import model.Confirmation;
import model.Direction;
import model.User;
import model.Vibration;
import processing.core.PApplet;
import processing.core.PImage;
import view.player.Player;
import view.spaceship.Circletlex;
import view.spaceship.Diamondrox;
import view.spaceship.Squarlux;
import view.spaceship.Triangletrix;

public class GeometrixController implements OnMessageListener {

	private PApplet app;
	private PImage p1imageN0, p1imageN1,p1imageN2,p1imageN3;
	private PImage p2imageN0, p2imageN1,p2imageN2,p2imageN3;
	private PImage bulletOrange1;

	private Player player1, player2;

	private Geometrix mainView;

	private TCP_J1 tcpJ1;
	private TCP_J2 tcpJ2;
	private Gson gson;

	public GeometrixController(PApplet app, Geometrix geometrix) {

		this.app = app;
		tcpJ1 = TCP_J1.getInstance();
		tcpJ1.setObserver(this);
		tcpJ2 = TCP_J2.getInstance();
		tcpJ2.setObserver(this);
		//ball = new Ball(app,app.width/2,app.height/2);
		gson = new Gson();
		cargarRecursos();
		mainView = geometrix;

	}

	@Override
	public void OnShootReceived(String player, boolean superShoot) {

		switch (player) {

		case "player1":
			
			player1.getShip().newBullet();
			System.out.println("Disparo 1");
			break;

		case "player2":
			if (superShoot) {

				System.out.println("super Disparo el 2");
			} else {

				System.out.println("Disparo el 2");

			}

			break;
		}

	}

	@Override
	public void OnDirectionReceived(String player, Direction dir) {


		switch (player) {

		case "player1":
			
			player1.getShip().moveSpaceship(dir.getDirection());

			break;

		case "player2":
			
			player2.getShip().moveSpaceship(dir.getDirection());
			
			break;
		}

	}
	public void drawSpace() {
		player1.getShip().drawInGame();
		//player2.getShip().drawInGame();
	}
	
	public void shootImpact(){
		
	}

	
	public void Impact() {

		Vibration vibration = new Vibration(true);
		String message = gson.toJson(vibration);
		tcpJ2.sendMessage(message);

	}

	@Override
	public void PlayerConnected(String player) {

		switch (player) {
		case "Player1":
			mainView.setConnectionsJ1();
			break;

		case "Player2":
			//mainView.setConnectionsJ2();
			//mainView.setConnectionsJ1();
			break;
		}

	}

	@Override
	public void OnUserReceived(String player, User user) {
		
		System.out.println(user.getTspaceship());
		switch (player) {
		
		case "player1":
			
			switch(user.getTspaceship()) {
			
			case 0:
				player1 = new Player(user.getName(), new Triangletrix(true,user.getName(),p1imageN0,app,100,bulletOrange1));
				break;
			case 1:
				player1 = new Player(user.getName(), new Diamondrox(true,user.getName(),p1imageN1,app,50,bulletOrange1));
				
				
				break;
			case 2:
				player1 = new Player(user.getName(), new Circletlex(true,user.getName(),p1imageN2,app,30,bulletOrange1));
				break;
			case 3:
				player1 = new Player(user.getName(), new Squarlux(true,user.getName(),p1imageN3,app,80,bulletOrange1));
				break;
				
			}

			break;

		case "player2":
			switch(user.getTspaceship()) {
			case 0:
				player2 = new Player(user.getName(), new Triangletrix(false,user.getName(),p2imageN0,app,80,bulletOrange1));
				break;
			case 1:
				player2 = new Player(user.getName(), new Diamondrox(false,user.getName(),p2imageN1,app,100,bulletOrange1));
				break;
			case 2:
				player2 = new Player(user.getName(), new Circletlex(false,user.getName(),p2imageN2,app,100,bulletOrange1));
				break;
			case 3:
				player2 = new Player(user.getName(), new Squarlux(false,user.getName(),p2imageN3,app,100,bulletOrange1));
				break;				
			}
			break;
		}

	}

	private void cargarRecursos() {
		
		 p1imageN0 = app.loadImage("../resources/images/spaceships/nave0p1.png");
		 p1imageN1 = app.loadImage("../resources/images/spaceships/nave1p1.png");
		 p1imageN2 = app.loadImage("../resources/images/spaceships/nave2p1.png");
		 p1imageN3 = app.loadImage("../resources/images/spaceships/nave3p1.png");
		 p2imageN0 = app.loadImage("../resources/images/spaceships/nave0p2.png");
		 p2imageN1 = app.loadImage("../resources/images/spaceships/nave1p2.png");
		 p2imageN2 = app.loadImage("../resources/images/spaceships/nave2p2.png");
		 p2imageN3 = app.loadImage("../resources/images/spaceships/nave3p2.png");
		 bulletOrange1 = app.loadImage("../resources/images/bullets/bala_naranja.png");
		

	}

	public void SendConfirmation() {
		// TODO Auto-generated method stub
		
		Confirmation confirmation = new Confirmation(true);
		String message = gson.toJson(confirmation);
		tcpJ1.sendMessage(message);
		//tcpJ2.sendMessage(message);
		
		
	}
}
