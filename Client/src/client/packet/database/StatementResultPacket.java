package client.packet.database;

import java.net.Socket;
import java.util.UUID;

import client.packet.Packet;

public class StatementResultPacket extends Packet {
	private static final long serialVersionUID = 7326063493578713061L;
	
	public boolean success;
	public String error;
	
	StatementResultPacket(boolean success, String error, UUID id) {
		this.success = success;
		this.error = error;
		this.id = id;
	}
	
	@Override
	public void onReceive(Socket socket) {
	}

	@Override
	public Object getResult() {
		return this;
	}
	
}
