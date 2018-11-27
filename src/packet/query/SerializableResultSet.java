package packet.query;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SerializableResultSet implements Serializable {
	private static final long serialVersionUID = 6939281298656343979L;
	
	protected List<HashMap<String, Object>> rows;
	private int pointer;
	
	public SerializableResultSet(ResultSet resultSet) {
		rows = new ArrayList<>();
		pointer = -1;
		ResultSetMetaData meta;
		try {
			meta = resultSet.getMetaData();
			while (resultSet.next()) {
				HashMap<String, Object> row = new HashMap<>();
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					row.put(meta.getColumnName(i), resultSet.getObject(i));
				}
				rows.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean next() {
		if (pointer++ < rows.size() - 1) {
			return true;
		}
		return false;
	}
	
	public Object get(String column) {
		return rows.get(pointer).get(column.toUpperCase());
	}
	
}
