package packet;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResultPacket implements Packet{

	ResultSet result;
	
	public QueryResultPacket(ResultSet result) {
		this.result = result;
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
