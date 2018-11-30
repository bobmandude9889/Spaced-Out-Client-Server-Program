package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import client.connection.StreamManager;
import client.packet.callback.CallbackManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{

	public static Socket serverSocket;
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Project.fxml"));	
		
		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Project"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
		stage.setOnCloseRequest(WindowEvent -> {
			System.exit(0);
		});
	}
	
	public static void main(String[] args) {
		System.out.println("Starting client...");
		try {
			serverSocket = new Socket(InetAddress.getLocalHost(), 4321);
			
			StreamManager.init();
			StreamManager.add(serverSocket);
		} catch (IOException e) {
			System.out.println("Could not connect to server");
		}
		
		CallbackManager.init();
		launch(args);
//		
//		Scanner in = new Scanner(System.in);
//		while (true) {
//			String command = in.nextLine();
//			String[] splitCmd = command.split(" ");
//			switch (splitCmd[0]) {
//			case "t":
//				StreamManager.sendPacket(new QueryPacket("SELECT * FROM test", result -> {
//					while (result.next()) {
//						System.out.println(result.get("test") + " : " + result.get("test1"));
//					}
//				}), socket);
//				break;
//			case "i":
//				StreamManager.sendPacket(new StatementPacket(String.format("INSERT INTO test (test, test1) VALUES (\'%s\', %s)", splitCmd[1], splitCmd[2]), result -> {
//					if (result.success)
//						System.out.println("Succesful");
//					else
//						System.out.println("Error: " + result.error);
//				}), socket);
//				break;
//			default:
//				System.out.println("Unknown command.");
//			}
//		}
	}
	
}
