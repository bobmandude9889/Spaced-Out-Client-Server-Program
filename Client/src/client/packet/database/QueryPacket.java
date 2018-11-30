package client.packet.database;

import java.net.Socket;

import client.packet.Packet;
import client.packet.callback.PacketCallback;

public class QueryPacket extends Packet {
	private static final long serialVersionUID = 6288078334643665219L;
	
	String query;
	
	public QueryPacket(String query, PacketCallback<SerializableResultSet> callback) {
		super(callback);
		this.query = query;
	}
	
	@Override
	public void onReceive(Socket socket) {
	}

}
