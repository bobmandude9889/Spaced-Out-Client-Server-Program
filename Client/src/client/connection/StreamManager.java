package client.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import client.packet.Packet;
import client.packet.callback.CallbackManager;
import client.packet.callback.PacketCallback;

public class StreamManager {

	static HashMap<Socket, ObjectOutputStream> outputs;
	static HashMap<Socket, ObjectInputStream> inputs;
	
	public static void init() {
		outputs = new HashMap<>();
		inputs = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public static void add(final Socket socket) {
		try {
			outputs.put(socket, new ObjectOutputStream(socket.getOutputStream()));
			
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			new Thread(() -> {
				while (true) {
					try {
						Packet packet = (Packet) input.readObject();
						packet.onReceive(socket);
						PacketCallback<Object> callback = (PacketCallback<Object>) CallbackManager.getCallback(packet);
						callback.resultReceived(packet.getResult());
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("Error in packet reading. Connection closed to " + socket.getInetAddress().getHostAddress());
						break;
					}
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void sendPacket(Packet packet, Socket socket) {
		ObjectOutputStream out = outputs.get(socket);
		try {
			out.writeObject(packet);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
