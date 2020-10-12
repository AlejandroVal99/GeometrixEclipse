package events;

import model.Direction;
import model.User;

public interface OnMessageListener {
	
	void PlayerConnected(String player);
	
	void OnUserReceived(String player,User user);
	
	void OnShootReceived(String player,boolean superShoot);
	
	void OnDirectionReceived(String player, Direction dir);
}
