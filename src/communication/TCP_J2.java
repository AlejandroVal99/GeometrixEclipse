package communication;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import events.OnMessageListener;
import model.Direction;
import model.Generic;
import model.Shoot;
import model.User;

public class TCP_J2 extends Thread{

	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private OnMessageListener observer;
	
	private static TCP_J2 instanceJ2;
	
	private TCP_J2() {
		
	}
	
	public static TCP_J2 getInstance() {
		if(instanceJ2 == null) {
			instanceJ2 = new TCP_J2();
			instanceJ2.start();
		}
		return instanceJ2;
	}
	public void setObserver(OnMessageListener observer) {
		
		this.observer = observer;
		
	}
	public void run() {
		
		try {
			ServerSocket server = new ServerSocket(5001);
			System.out.println("Esperando conexion");
			socket = server.accept();
			observer.PlayerConnected("Player2");
			
			System.out.println("Cliente conectado");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			reader = new BufferedReader(isr);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			writer = new BufferedWriter(osw);
			
			Gson gson = new Gson();
			while(true) {
				String line = reader.readLine();
				//System.out.println("Recibido: " + line);
				//observer.OnMessage(line);
				
				Generic generic = gson.fromJson(line, Generic.class);
				//System.out.println(generic.getType());
				
				switch(generic.getType()) {
				
				case "Shoot":
					
					Shoot tempShoot=gson.fromJson(line, Shoot.class);
					observer.OnShootReceived("player2",tempShoot.isSuperShoot());
					
					break;
				case "User":
					
					User user = gson.fromJson(line,User.class);
					observer.OnUserReceived("player2", user);
					break;
					
				case "Direction":
					
					Direction dir = gson.fromJson(line, Direction.class);
					observer.OnDirectionReceived("player2", dir);
					
					break;
				
					
				
					
				}
				
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		new Thread(
				()->{
					try {
						writer.write(msg+"\n");
						writer.flush();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				).start();
		
		
	}
}
