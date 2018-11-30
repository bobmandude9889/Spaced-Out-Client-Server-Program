package server;

import server.connection.ConnectionManager;
import server.connection.StreamManager;

public class Server {
	
	public static void main(String[] args) {
		System.out.println("Starting server...");
		try {
			DatabaseManager.init();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		//DatabaseManager.executeStatement("CREATE TABLE test (test varchar(30), test1 int)");
		//DatabaseManager.executeStatement("INSERT INTO test (test, test1) VALUES ('Hello world', 69)");
		StreamManager.init();
		ConnectionManager.init();
		System.out.println("Server started");
	}
	
}
