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
import model.Generic;

public class TCP_J1 extends Thread{
	
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private OnMessageListener observer;
	
	private static TCP_J1 instanceJ1;
	
	private TCP_J1() {
		
	}
	
	public static TCP_J1 getInstance() {
		if(instanceJ1 == null) {
			instanceJ1 = new TCP_J1();
			instanceJ1.start();
		}
		return instanceJ1;
	}
	
public void setObserver(OnMessageListener observer) {
		
		this.observer = observer;
		
	}

	public void run() {
		
		try {
			ServerSocket server = new ServerSocket(5001);
			System.out.println("Esperando conexion");
			socket = server.accept();
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
				
				System.out.println("Recibido: " + line);
				
				//observer.OnMessage(line);
				//Solo toma el tipo
				
				Generic generic = gson.fromJson(line, Generic.class);
				
				switch(generic.getType()) {
				
				case "Disparo":
					
					observer.OnShootReceived("player1");
					
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
