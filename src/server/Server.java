package server;

import java.sql.SQLException;

public class Server {
	
	public static void main(String[] args) throws SQLException {
		System.out.println("Starting server...");
		StreamManager.init();
		ConnectionManager.init();
		DatabaseManager.init();
	}
	
}
