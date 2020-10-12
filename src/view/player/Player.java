package view.player;
import view.spaceship.SpaceShip;

public class Player {
	
	String nickName;
	SpaceShip ship;
	
	 public Player(String nickname, SpaceShip ship) {
		 
		 this.nickName = nickname;
		 this.ship = ship;
	
	 }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public SpaceShip getShip() {
		return ship;
	}

	public void setShip(SpaceShip ship) {
		this.ship = ship;
	}
	 
}
