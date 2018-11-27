package server;

import java.sql.SQLException;

import server.connection.ConnectionManager;
import server.connection.StreamManager;

public class Server {
	
	public static void main(String[] args) throws SQLException {
		System.out.println("Starting server...");
		DatabaseManager.init();
		//DatabaseManager.executeStatement("CREATE TABLE test (test varchar(30), test1 int)");
		//DatabaseManager.executeStatement("INSERT INTO test (test, test1) VALUES ('Hello world', 69)");
		StreamManager.init();
		ConnectionManager.init();
	}
	
}
