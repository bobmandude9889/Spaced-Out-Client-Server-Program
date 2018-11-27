package packet;

import java.net.Socket;
import java.sql.ResultSet;

import server.DatabaseManager;
import server.StreamManager;

public class QueryPacket extends Packet {

	String query;
	
	public QueryPacket(String query, PacketCallback<ResultSet> callback) {
		super(callback);
		this.query = query;
	}
	
	@Override
	public void onReceive(Socket socket) {
		DatabaseManager.executeQuery(this.query, result -> {
			StreamManager.sendPacket(new QueryResultPacket(result, id), socket);
		});
	}

}
