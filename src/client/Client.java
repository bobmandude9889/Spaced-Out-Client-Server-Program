package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import packet.TestPacket;

public class Client {

	static Socket socket;
	
	public static void main(String[] args) {
		System.out.println("Starting client...");
		try {
			socket = new Socket(InetAddress.getLocalHost(), 4321);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StreamManager.init();
		StreamManager.add(socket);
		StreamManager.sendPacket(new TestPacket(), socket);
		System.out.println("Sent packet");
	}
	
}
