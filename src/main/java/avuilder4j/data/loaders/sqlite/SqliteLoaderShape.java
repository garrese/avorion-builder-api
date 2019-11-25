package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.Shape;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderShape extends SqliteLoader<Integer, Shape> {

	public static final String TABLE_NAME = "Shape";

	@Override
	public Map<Integer, Shape> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<Integer, Shape> onSqliteDataMapping(ResultSet r) throws Exception {
		Integer i = r.getInt("idx");
		String n = r.getString("name");
		Double v = r.getDouble("volumeMod");
		Shape shape = new Shape(i, n, v);
		return new SimpleEntry<Integer, Shape>(shape.getIndex(), shape);
	}

}
