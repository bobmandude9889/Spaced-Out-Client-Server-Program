package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseManager {

	static Connection db;
	
	public static void init() throws SQLException, FileNotFoundException {
		System.out.println("Starting database manager...");
		try {
			db = DriverManager.getConnection("jdbc:derby:database;username=admin;password=root");
		} catch (SQLException e) {
			System.out.println("Could not connect to database. Trying to create one...");
			db = DriverManager.getConnection("jdbc:derby:database;create=true;username=admin;password=root");
			File sqlScript = new File(DatabaseManager.class.getResource("database.sql").getFile());
			Scanner in = new Scanner(sqlScript);	
			String script = "";
			while (in.hasNextLine()) {
				script += in.nextLine() + "\n";
			}
			System.out.println("Running script...");
			String[] commands = script.split(";");
			for (int i = 0; i < commands.length - 1; i++) {
				String command = commands[i];
				Statement stmt = db.createStatement();
				System.out.println(command);
				stmt.execute(command);
			}
			in.close();
		}
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
		executeStatement(statement, null);
	}
	
	public static void executeStatement(String statement, StatementResultCallback callback) {
		runAsync(() -> {
			Statement stmt;
			try {
				stmt = db.createStatement();
				stmt.execute(statement);
				stmt.close();
				if (callback != null)
					callback.statementExecuted(true, "");
				System.out.println("Executed " + statement);
			} catch (SQLException e) {
				System.out.println("Statement failed: " + statement);
				e.printStackTrace();
				if (callback != null)
					callback.statementExecuted(false, e.getMessage());
			}
		});
	}
	
	public interface QueryResultCallback {

		public void resultReceived(ResultSet result);
		
	}

	public interface StatementResultCallback {
		
		public void statementExecuted(boolean success, String error);
		
	}
	
}