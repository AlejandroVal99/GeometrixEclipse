package events;

import model.Direction;

public interface OnMessageListener {
	
	void OnMessage(String msg);
	
	void OnShootReceived(String player,boolean superShoot);
	
	void OnDirectionReceived(String player, Direction dir);
}
