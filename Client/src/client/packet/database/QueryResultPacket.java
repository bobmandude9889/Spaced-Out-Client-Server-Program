package client.packet.database;

import java.net.Socket;
import java.util.UUID;

import client.packet.Packet;

public class QueryResultPacket extends Packet {
	private static final long serialVersionUID = 1846948960000090348L;
	
	public SerializableResultSet result;
	
	public QueryResultPacket(SerializableResultSet result, UUID id) {
		this.result = result;
		this.id = id;
	}
	
	@Override
	public void onReceive(Socket socket) {
	}
	
	public Object getResult() {
		return result;
	}
	
}
