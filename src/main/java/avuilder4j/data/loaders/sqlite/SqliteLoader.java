package avuilder4j.data.loaders.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import avuilder4j.data.loaders.base.Loader;
import avuilder4j.error.Avuilder4jException;

public abstract class SqliteLoader<K, V> implements Loader<K, V> {

	protected String dbFilePath = "db/avorion-db.sqlite3";

	protected Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:" + getClass().getClassLoader().getResource(dbFilePath));

		return connection;
	}

	public String getDbFilePath() { return dbFilePath; }

	public void setDbFilePath(String dbFilePath) { this.dbFilePath = dbFilePath; }

	public Map<K, V> connectAndLoadAll(String tableName) throws Avuilder4jException {
		Connection c = null;
		Map<K, V> map = new HashMap<>();
		ResultSet r = null;
		try {

			c = getConnection();
			String select = "SELECT * FROM " + tableName;
			PreparedStatement st = c.prepareStatement(select);
			r = st.executeQuery();

			while (r.next()) {
				AbstractMap.SimpleEntry<K, V> entry = onSqliteDataMapping(r);
				map.put(entry.getKey(), entry.getValue());
			}

		} catch (Exception e) {
			throw new Avuilder4jException(e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Avuilder4jException(e);
			}
		}
		return map;
	}

	public abstract AbstractMap.SimpleEntry<K, V> onSqliteDataMapping(ResultSet r) throws Exception;

}
