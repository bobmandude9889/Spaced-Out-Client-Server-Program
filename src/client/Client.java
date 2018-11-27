package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import client.connection.StreamManager;
import packet.callback.CallbackManager;
import packet.database.QueryPacket;
import packet.database.StatementPacket;

public class Client {

	static Socket socket;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Starting client...");
		try {
			socket = new Socket(InetAddress.getLocalHost(), 4321);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StreamManager.init();
		StreamManager.add(socket);
		CallbackManager.init();
		
		Scanner in = new Scanner(System.in);
		while (true) {
			String command = in.nextLine();
			String[] splitCmd = command.split(" ");
			switch (splitCmd[0]) {
			case "t":
				StreamManager.sendPacket(new QueryPacket("SELECT * FROM test", result -> {
					while (result.next()) {
						System.out.println(result.get("test") + " : " + result.get("test1"));
					}
				}), socket);
				break;
			case "i":
				StreamManager.sendPacket(new StatementPacket(String.format("INSERT INTO test (test, test1) VALUES (\'%s\', %s)", splitCmd[1], splitCmd[2]), result -> {
					if (result.success)
						System.out.println("Succesful");
					else
						System.out.println("Error: " + result.error);
				}), socket);
				break;
			default:
				System.out.println("Unknown command.");
			}
		}
	}
	
}
