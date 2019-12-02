package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelParams;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderTypeModel extends SqliteLoader<Integer, TypeModel> {

	public static final String TABLE_NAME = "TypeModel";

	@Override
	public Map<Integer, TypeModel> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<Integer, TypeModel> onSqliteDataMapping(ResultSet r) throws Exception {
		TypeModelParams p = new TypeModelParams(r.getInt("idx"));
		p.setName(r.getString("name"));
		p.setDensityMod(r.getDouble("densityMod"));
		p.setDurabilityMod(r.getDouble("durabilityMod"));
		p.setMaterialCostMod(r.getDouble("materialCostMod"));
		p.setMechanics(r.getDouble("mechanics"));
		p.setEngineers(r.getDouble("engineers"));
		p.setProcessingMod(r.getDouble("processingMod"));
		p.setVolumeStatMod(r.getDouble("volumeStatMod"));
		TypeModel tm = new TypeModel(p);
		return new SimpleEntry<Integer, TypeModel>(tm.getIndex(), tm);
	}

}
