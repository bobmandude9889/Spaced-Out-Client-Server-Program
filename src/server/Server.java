package server;

public class Server {
	
	public static void main(String[] args) {
		System.out.println("Starting server...");
		StreamManager.init();
		ConnectionManager.init();
	}
	
}