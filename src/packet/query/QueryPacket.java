package packet.query;

import java.net.Socket;

import packet.Packet;
import packet.callback.PacketCallback;
import server.DatabaseManager;
import server.connection.StreamManager;

public class QueryPacket extends Packet {
	private static final long serialVersionUID = 6288078334643665219L;
	
	String query;
	
	public QueryPacket(String query, PacketCallback<SerializableResultSet> callback) {
		super(callback);
		this.query = query;
	}
	
	@Override
	public void onReceive(Socket socket) {
		DatabaseManager.executeQuery(this.query, result -> {
			SerializableResultSet resultSet = new SerializableResultSet(result);
			System.out.println("Query: " + this.query);
			System.out.println("Result: " + resultSet.rows);
			StreamManager.sendPacket(new QueryResultPacket(resultSet, id), socket);
		});
	}

}
