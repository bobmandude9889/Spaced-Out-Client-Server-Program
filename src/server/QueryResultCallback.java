package server;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryResultCallback {

	public void resultReceived(ResultSet result);
	
}
