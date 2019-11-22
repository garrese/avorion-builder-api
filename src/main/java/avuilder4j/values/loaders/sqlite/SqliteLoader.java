package avuilder4j.values.loaders.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import avuilder4j.dtos.map.Indexable;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.EntityMap;

public abstract class SqliteLoader<T extends Indexable> {

	protected String dbFilePath = "db/avorion-db.sqlite3";

	protected Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:" + getClass().getClassLoader().getResource(dbFilePath));

		return connection;
	}

	public String getDbFilePath() { return dbFilePath; }

	public void setDbFilePath(String dbFilePath) { this.dbFilePath = dbFilePath; }

	public EntityMap<T> doConnectedAction(String tableName) throws Avuilder4jException {
		Connection c = null;
		EntityMap<T> map = new EntityMap<>();
		ResultSet r = null;
		try {

			c = getConnection();
			String select = "SELECT * FROM " + tableName;
			PreparedStatement st = c.prepareStatement(select);
			r = st.executeQuery();

			while (r.next()) {
				T t = onSqliteDataMapping(r);
				map.add(t);
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

	public abstract T onSqliteDataMapping(ResultSet r) throws Exception;

}
