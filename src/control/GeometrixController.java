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

	private PImage bulletOrange,bulletOrange2,blueBullet,blueBullet2,bulletPurple,bulletGreen
	,bulletGreen2,superBulletNaranja,superBulletNaranja2,superBulletVerde,superBulletVerde2,superBulletAzul,superBulletAzul2,superBulletPurple;

	private PImage n0Sel, n1Sel, n2Sel, n3Sel;


	private Player player1, player2;
	private boolean winPlayer;//variable para pintar ganador true = p1, false = p2

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
		gson = new Gson();
		cargarRecursos();
		mainView = geometrix;

	}

	@Override
	public void OnShootReceived(String player, boolean superShoot) {

		switch (player) {

		case "player1":
			if(superShoot) {
				
				player1.getShip().newBullet(true);

			}
			else {
			player1.getShip().newBullet(false);
			}
			break;

		case "player2":
			if (superShoot) {
				
			player2.getShip().newBullet(true);

			} else {

				player2.getShip().newBullet(false);
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
		player2.getShip().drawInGame();
		shootVerify();
		winVerif();
	}
	
	private void winVerif() {
		//Verification de la vida de los usuarios pra pasar a la siguiente pantalla
		if(player1.getShip().getVida()<= 0 ){
			
			winPlayer = false;
			mainView.setScreen(6);
			
		}else if(player2.getShip().getVida()<= 0) {
			
			winPlayer = true;
			mainView.setScreen(6);
		}
		
	}

	
	public void shootVerify(){
		//Verification de impacto y salida del lienzo
		//Verification shoots player 1
		
		
		for(int p1 = 0; p1<player1.getShip().getBullets().size();p1++) {
			float posXb =  player1.getShip().getBullets().get(p1).getPosx();
			float posYb =  player1.getShip().getBullets().get(p1).getPosy();
			float posXp2 = player2.getShip().getnPosx();
			float posYp2 = player2.getShip().getnPosy()-35;
			
			if(PApplet.dist(posXb,posYb,posXp2,posYp2)<80) {
				
				int danoBullet = player1.getShip().getBullets().get(p1).getDaño();
				player2.getShip().setVida(danoBullet);
				player1.getShip().getBullets().remove(p1);
				Impact(false);
				System.out.println("entré disparo");

				
			}
			if(posXb>=app.width) {
				player1.getShip().getBullets().remove(p1);
			}
		}
		
		//Verification shoots player 2
		for(int p2 = 0; p2<player2.getShip().getBullets().size();p2++) {
			float posXb =  player2.getShip().getBullets().get(p2).getPosx();
			float posYb =  player2.getShip().getBullets().get(p2).getPosy();
			float posXp1 = player1.getShip().getnPosx();
			float posYp1 = player1.getShip().getnPosy()-35;
			if(PApplet.dist(posXb,posYb,posXp1,posYp1)<80) {
				
				int danoBullet = player2.getShip().getBullets().get(p2).getDaño();
				player1.getShip().setVida(danoBullet);
				player2.getShip().getBullets().remove(p2);
				Impact(true);
				System.out.println("entré disparo 2 ");

			}
			
			if(posXb<=0) {
				player2.getShip().getBullets().remove(p2);
			}
		}		
		
	}

	
	public void Impact(boolean player) {
		if(player) {
			Vibration vibration = new Vibration(true);
			String message = gson.toJson(vibration);
			tcpJ1.sendMessage(message);
			
			System.out.println();

		}else {
			
			Vibration vibration = new Vibration(true);
			String message = gson.toJson(vibration);
			tcpJ2.sendMessage(message);
		}

	}

	@Override
	public void PlayerConnected(String player) {

		switch (player) {
		case "Player1":
			mainView.setConnectionsJ1();
			break;

		case "Player2":
			mainView.setConnectionsJ2();
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

				player1 = new Player(user.getName(), new Triangletrix(true,user.getName(),p1imageN0,n0Sel,app,100,blueBullet,superBulletAzul));
				break;
			case 1:
				player1 = new Player(user.getName(), new Diamondrox(true,user.getName(),p1imageN1,n1Sel,app,50,bulletOrange,superBulletNaranja));
				
				break;
			case 2:
				player1 = new Player(user.getName(), new Circletlex(true,user.getName(),p1imageN2,n2Sel,app,30,bulletPurple,superBulletPurple));
				break;
			case 3:
				player1 = new Player(user.getName(), new Squarlux(true,user.getName(),p1imageN3,n3Sel,app,80,bulletGreen,superBulletVerde));
				break;
			
			}

			break;

		case "player2":
			switch(user.getTspaceship()) {
			case 0:

				player2 = new Player(user.getName(), new Triangletrix(false,user.getName(),p2imageN0,n0Sel,app,80,blueBullet2,superBulletAzul2));
				break;
			case 1:
				player2 = new Player(user.getName(), new Diamondrox(false,user.getName(),p2imageN1,n1Sel,app,100,bulletOrange2,superBulletNaranja2));
				break;
			case 2:
				player2 = new Player(user.getName(), new Circletlex(false,user.getName(),p2imageN2,n2Sel,app,100,bulletPurple,superBulletPurple));
				break;
			case 3:
				player2 = new Player(user.getName(), new Squarlux(false,user.getName(),p2imageN3,n3Sel,app,100,bulletGreen2,superBulletVerde2));
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
		 
		 n0Sel=app.loadImage("../resources/images/spaceships/selection/nav0Sel.png");
		 n1Sel=app.loadImage("../resources/images/spaceships/selection/nav1Sel.png");
		 n2Sel=app.loadImage("../resources/images/spaceships/selection/nav2Sel.png");
		 n3Sel=app.loadImage("../resources/images/spaceships/selection/nav3Sel.png");
		 
		 bulletOrange2 = app.loadImage("../resources/images/bullets/bala_naranja2.png");
		 bulletOrange = app.loadImage("../resources/images/bullets/bala_naranja.png");
		 blueBullet = app.loadImage("../resources/images/bullets/bala_azul.png");
		 blueBullet2 = app.loadImage("../resources/images/bullets/bala_azul2.png");
		 bulletPurple = app.loadImage("../resources/images/bullets/bala_morada.png");
		 bulletGreen = app.loadImage("../resources/images/bullets/bala_verde.png");
		 bulletGreen2 = app.loadImage("../resources/images/bullets/bala_verde2.png");
		 superBulletNaranja = app.loadImage("../resources/images/bullets/superBullets/superBulletNaranja.png");
		 superBulletNaranja2 = app.loadImage("../resources/images/bullets/superBullets/superBulletNaranja2.png");
		 superBulletVerde = app.loadImage("../resources/images/bullets/superBullets/superBulletVerde.png");
		 superBulletVerde2 = app.loadImage("../resources/images/bullets/superBullets/superBulletVerde2.png");
		 superBulletAzul = app.loadImage("../resources/images/bullets/superBullets/superBulletAzul.png");
		 superBulletAzul2 = app.loadImage("../resources/images/bullets/superBullets/superBulletAzul2.png");
		 superBulletPurple = app.loadImage("../resources/images/bullets/superBullets/superBulletPurple.png");
	
	}

	public void SendConfirmation() {
		// TODO Auto-generated method stub
		
		Confirmation confirmation = new Confirmation(true);
		String message = gson.toJson(confirmation);
		tcpJ1.sendMessage(message);
		tcpJ2.sendMessage(message);
			
	}

	
	//Pintar el ganardor
	public void drawWin() {

		if(winPlayer) {
			player1.getShip().drawWin();
		}else {
			player2.getShip().drawWin();
		}
		
	}

	public void drawinSelection(){
		if(player1 != null) {
			player1.getShip().drawInSelection();
		}
		if(player2 != null) {
			player2.getShip().drawInSelection();
		}
		if(player1 != null && player2 != null) {
			mainView.setScreen(4);
		}
	}
}
