package avuilder4j.values.loaders.sqlite;

import java.sql.ResultSet;

import avuilder4j.dtos.map.MapIndex;
import avuilder4j.dtos.map.TypeModel;
import avuilder4j.dtos.map.TypeModelParams;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.EntityMap;
import avuilder4j.values.loaders.TypeModelLoader;

public class SqliteLoaderTypeModel extends SqliteLoader<TypeModel> implements TypeModelLoader {

	public static final String TABLE_NAME = "TypeModel";

	@Override
	public EntityMap<avuilder4j.dtos.map.TypeModel> loadAll() throws Avuilder4jException {
		return super.doConnectedAction(TABLE_NAME);
	}

	@Override
	public TypeModel onSqliteDataMapping(ResultSet r) throws Exception {
		MapIndex<Integer> idx = new MapIndex<Integer>(r.getInt("idx"));
		TypeModelParams p = new TypeModelParams(idx);
		p.setName(r.getString("name"));
		p.setDensityMod(r.getDouble("densityMod"));
		p.setDurabilityMod(r.getDouble("durabilityMod"));
		p.setMaterialCostMod(r.getDouble("materialCostMod"));
		p.setMechanics(r.getDouble("mechanics"));
		p.setEngineers(r.getDouble("engineers"));
		p.setProcessingMod(r.getDouble("processingMod"));
		p.setVolumeStatMod(r.getDouble("volumeStatMod"));
		return new TypeModel(p);
	}

}
