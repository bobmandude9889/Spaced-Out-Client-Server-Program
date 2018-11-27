package packet;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class QueryResultPacket extends Packet {

	ResultSet result;
	UUID id;
	
	public QueryResultPacket(ResultSet result, UUID id) {
		this.result = result;
		this.id = id;
	}
	
	@Override
	public void onReceive(Socket socket) {
		try {
			result.next();
			System.out.println(result.getMetaData().getColumnCount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
