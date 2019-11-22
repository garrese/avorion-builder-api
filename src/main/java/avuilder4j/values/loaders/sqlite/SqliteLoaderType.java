package avuilder4j.values.loaders.sqlite;

import java.sql.ResultSet;

import avuilder4j.dtos.map.MapIndex;
import avuilder4j.dtos.map.Type;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.EntityMap;
import avuilder4j.values.loaders.TypeLoader;

public class SqliteLoaderType extends SqliteLoader<Type> implements TypeLoader {

	public static final String TABLE_NAME = "Type";

	@Override
	public EntityMap<Type> loadAll() throws Avuilder4jException {
		return super.doConnectedAction(TABLE_NAME);
	}

	@Override
	public Type onSqliteDataMapping(ResultSet r) throws Exception {
		MapIndex<Integer> idx = new MapIndex<Integer>(r.getInt("idx"));
		int typeModelIndex = r.getInt("typeModelIdx");
		int shapeIdx = r.getInt("shapeIdx");
		Type type = new Type(idx, typeModelIndex, shapeIdx);
		return type;
	}

}
