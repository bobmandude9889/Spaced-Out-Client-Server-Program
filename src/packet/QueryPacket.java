package packet;

import java.net.Socket;

import server.DatabaseManager;
import server.StreamManager;

public class QueryPacket implements Packet {

	String query;
	
	public QueryPacket(String query) {
		this.query = query;
	}
	
	@Override
	public void onReceive(Socket socket) {
		DatabaseManager.runQuery(this.query, result -> {
			StreamManager.sendPacket(new QueryResultPacket(result), socket);
		});
	}

}
