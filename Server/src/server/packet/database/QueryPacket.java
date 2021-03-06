package server.packet.database;

import java.net.Socket;

import server.DatabaseManager;
import server.connection.StreamManager;
import server.packet.Packet;
import server.packet.callback.PacketCallback;

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
