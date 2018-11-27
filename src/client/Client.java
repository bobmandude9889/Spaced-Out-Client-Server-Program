package client;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

import packet.CallbackManager;
import packet.Packet;
import packet.QueryPacket;

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
		CallbackManager.init();

		
		
//		StreamManager.sendPacket(new QueryPacket("SELECT * FROM test", result -> {
//			try {
//				result.next();
//				System.out.println(result.getString("test"));
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}), socket);
		
		Scanner in = new Scanner(System.in);
		while (true) {
			String command = in.nextLine();
			String[] cmdArgs = command.substring(command.indexOf(" ") + 1).split(" ");
			command = command.substring(0, command.indexOf(" "));
			
			try {
				Class<?> packetClass = Class.forName("packet." + command);
				Constructor<?>[] constructors = packetClass.getConstructors();
				System.out.println(constructors[0].getParameterCount());
				Packet packet = (Packet) constructors[0].newInstance(cmdArgs);
				StreamManager.sendPacket(packet, socket);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
}
