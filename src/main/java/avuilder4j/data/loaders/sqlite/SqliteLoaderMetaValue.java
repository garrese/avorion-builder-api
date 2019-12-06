package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.dtos.MetaValue;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderMetaValue extends SqliteLoader<String, MetaValue> {

	public static final String TABLE_NAME = "MetaValue";

	@Override
	public Map<String, MetaValue> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<String, MetaValue> onSqliteDataMapping(ResultSet r) throws Exception {
		String n = r.getString("name");
		String g = r.getString("group");
		String tv = r.getString("text");
		Double nv = SqliteLoader.<Double>getWrapper(r, "number");

		MetaValue metaValue = new MetaValue(n, g, tv, nv);
		return new SimpleEntry<String, MetaValue>(metaValue.getName(), metaValue);
	}
}
