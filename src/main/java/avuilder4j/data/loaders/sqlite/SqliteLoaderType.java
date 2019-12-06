package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.dtos.Type;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderType extends SqliteLoader<Integer, Type> {

	public static final String TABLE_NAME = "Type";

	@Override
	public Map<Integer, Type> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<Integer, Type> onSqliteDataMapping(ResultSet r) throws Exception {
		Integer idx = SqliteLoader.<Integer>getWrapper(r, "idx");
		Integer typeModelIndex = SqliteLoader.<Integer>getWrapper(r, "typeModelIdx");
		Integer shapeIdx = SqliteLoader.<Integer>getWrapper(r, "shapeIdx");
		Type type = new Type(idx, typeModelIndex, shapeIdx);
		return new SimpleEntry<Integer, Type>(type.getIndex(), type);
	}

}
