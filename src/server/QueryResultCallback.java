package server;

import java.sql.ResultSet;

public interface QueryResultCallback {

	public void resultReceived(ResultSet result);
	
}
