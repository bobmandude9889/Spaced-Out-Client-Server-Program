package client.packet.database;

import java.net.Socket;

import client.packet.Packet;
import client.packet.callback.PacketCallback;

public class StatementPacket extends Packet {
	private static final long serialVersionUID = -6632728115371884641L;

	String statement;
	
	public StatementPacket(String statement, PacketCallback<StatementResultPacket> callback) {
		super(callback);
		this.statement = statement;
	}
	
	@Override
	public void onReceive(Socket socket) {
	}

}
