package events;

public interface OnMessageListener {
	
	void OnMessage(String msg);
	
	void OnShootReceived(String player);
}
