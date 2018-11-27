package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	static Connection db;
	
	public static void init() throws SQLException {
		db = DriverManager.getConnection("jdbc:derby:database;username=admin;password=root");
	}

	private static void runAsync(Runnable run) {
		new Thread(run).start();
	}
	
	public static void executeQuery(String query, QueryResultCallback callback) {
		runAsync(() -> {
			try {
				Statement stmt = db.createStatement();
				ResultSet result = stmt.executeQuery(query);
				callback.resultReceived(result);
			} catch (SQLException e) {
				System.out.println("Query failed: " + query);
				e.printStackTrace();
			}
		});
	}
	
	public static void executeStatement(String statement) {
		runAsync(() -> {
			Statement stmt;
			try {
				stmt = db.createStatement();
				stmt.execute(statement);
				stmt.close();
				System.out.println("Executed " + statement);
			} catch (SQLException e) {
				System.out.println("Statement failed: " + statement);
				e.printStackTrace();
			}
		});
	}
	
}
