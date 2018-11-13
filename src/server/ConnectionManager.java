package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

	static ServerSocket server;
	
	static List<Socket> sockets;
	
	public static void init() {
		sockets = new ArrayList<>();
		try {
			server = new ServerSocket(4321);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket socket = server.accept();
				StreamManager.add(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
