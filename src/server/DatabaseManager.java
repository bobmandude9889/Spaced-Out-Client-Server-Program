package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

public class DatabaseManager {

	static Connection db;
	
	static Thread thread;
	static Queue<Runnable> queryTasks;
	
	public static void init() throws SQLException {
		db = DriverManager.getConnection("jdbc:derby:database;create=true;username=admin;password=root");
		
		queryTasks = new LinkedList<>();
		thread = new Thread(() -> {
			while (true) {
				queryTasks.remove().run();
			}
		});
	}

	public static void executeQuery(String query, QueryResultCallback callback) {
		queryTasks.add(() -> {
			try {
				Statement stmt = db.createStatement();
				ResultSet result = stmt.executeQuery(query);
				callback.resultReceived(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void executeStatement(String statement) {
		queryTasks.add(() -> {
			Statement stmt;
			try {
				stmt = db.createStatement();
				stmt.execute(statement);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
	
}
