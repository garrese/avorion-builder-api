package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.Map;

import avuilder4j.data.Material;
import avuilder4j.data.MaterialParams;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderMaterial extends SqliteLoader<Integer, Material> {

	public static final String TABLE_NAME = "Material";

	@Override
	public Map<Integer, Material> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public AbstractMap.SimpleEntry<Integer, Material> onSqliteDataMapping(ResultSet r) throws Exception {
		MaterialParams p = new MaterialParams(r.getInt("idx"));
		p.setName(r.getString("name"));
		p.setDensity(r.getDouble("density"));
		p.setDurability(r.getDouble("durability"));
		p.setCreditCost(r.getDouble("creditCost"));
		p.setMaterialCost(r.getDouble("materialCost"));
		Material m = new Material(p);
		return new AbstractMap.SimpleEntry<Integer, Material>(m.getIndex(), m);
	}

}
