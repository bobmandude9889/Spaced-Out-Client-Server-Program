package packet.query;

import java.net.Socket;

import packet.Packet;
import server.DatabaseManager;

public class StatementPacket extends Packet {
	private static final long serialVersionUID = -6632728115371884641L;

	String statement;
	
	public StatementPacket(String statement) {
		this.statement = statement;
	}
	
	@Override
	public void onReceive(Socket socket) {
		DatabaseManager.executeStatement(statement);
	}

}
