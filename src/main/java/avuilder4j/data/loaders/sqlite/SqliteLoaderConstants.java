package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.dtos.Constant;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderConstants extends SqliteLoader<String, Constant> {

	public static final String TABLE_NAME = "Constant";

	@Override
	public Map<String, Constant> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<String, Constant> onSqliteDataMapping(ResultSet r) throws Exception {
		String n = r.getString("name");
		String g = r.getString("category");
		Double nv = SqliteLoader.<Double>getWrapper(r, "value");

		Constant k = new Constant(n, g, nv);
		return new SimpleEntry<String, Constant>(k.getName(), k);
	}
}
