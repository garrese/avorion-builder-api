package avuilder4j.values.loaders.sqlite;

import java.sql.ResultSet;

import avuilder4j.dtos.map.MapIndex;
import avuilder4j.dtos.map.Material;
import avuilder4j.dtos.map.MaterialParams;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.EntityMap;
import avuilder4j.values.loaders.MaterialLoader;

public class SqliteLoaderMaterial extends SqliteLoader<Material> implements MaterialLoader {

	public static final String TABLE_NAME = "Material";

	@Override
	public EntityMap<Material> loadAll() throws Avuilder4jException {
		return super.doConnectedAction(TABLE_NAME);
	}

	@Override
	public Material onSqliteDataMapping(ResultSet r) throws Exception {
		MapIndex<Integer> idx = new MapIndex<Integer>(r.getInt("idx"));
		MaterialParams p = new MaterialParams(idx);
		p.setName(r.getString("name"));
		p.setDensity(r.getDouble("density"));
		p.setDurability(r.getDouble("durability"));
		p.setCreditCost(r.getDouble("creditCost"));
		p.setMaterialCost(r.getDouble("materialCost"));
		return new Material(p);
	}

}
