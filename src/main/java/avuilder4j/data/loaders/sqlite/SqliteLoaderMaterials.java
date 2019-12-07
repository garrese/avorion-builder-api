package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.Map;

import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.MaterialParams;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderMaterials extends SqliteLoader<Integer, Material> {

	public static final String TABLE_NAME = "Material";

	@Override
	public Map<Integer, Material> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public AbstractMap.SimpleEntry<Integer, Material> onSqliteDataMapping(ResultSet r) throws Exception {
		MaterialParams p = new MaterialParams(SqliteLoader.<Integer>getWrapper(r, "idx"));
		p.setName(r.getString("name"));
		p.setDensity(SqliteLoader.<Double>getWrapper(r, "density"));
		p.setDurability(SqliteLoader.<Double>getWrapper(r, "durability"));
		p.setCreditCost(SqliteLoader.<Double>getWrapper(r, "creditCost"));
		p.setMaterialCost(SqliteLoader.<Double>getWrapper(r, "materialCost"));
		Material m = new Material(p);
		return new AbstractMap.SimpleEntry<Integer, Material>(m.getIndex(), m);
	}

}
