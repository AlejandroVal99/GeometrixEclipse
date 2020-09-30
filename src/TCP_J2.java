import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
			instanceJ2.run();
		}
		return instanceJ2;
	}
	public void setObserver(OnMessageListener observer) {
		
		this.observer = observer;
		
	}
	public void run() {
		
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando conexion");
			socket = server.accept();
			System.out.println("Cliente conectado");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			reader = new BufferedReader(isr);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			writer = new BufferedWriter(osw);
			
			while(true) {
				String line = reader.readLine();
				System.out.println("Recibido: " + line);
				observer.OnMessage(line);
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
