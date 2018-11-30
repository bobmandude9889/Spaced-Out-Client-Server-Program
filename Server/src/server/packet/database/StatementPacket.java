package server.packet.database;

import java.net.Socket;

import server.DatabaseManager;
import server.connection.StreamManager;
import server.packet.Packet;
import server.packet.callback.PacketCallback;

public class StatementPacket extends Packet {
	private static final long serialVersionUID = -6632728115371884641L;

	String statement;
	
	public StatementPacket(String statement, PacketCallback<StatementResultPacket> callback) {
		super(callback);
		this.statement = statement;
	}
	
	@Override
	public void onReceive(Socket socket) {
		DatabaseManager.executeStatement(statement, (success, error) -> {
			StreamManager.sendPacket(new StatementResultPacket(success, error, id), socket);
		});
	}

}
