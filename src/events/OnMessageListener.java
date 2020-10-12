package events;

import model.Direction;

public interface OnMessageListener {
	
	void PlayerConnected(String player);
	
	void OnShootReceived(String player,boolean superShoot);
	
	void OnDirectionReceived(String player, Direction dir);
}
