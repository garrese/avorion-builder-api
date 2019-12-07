package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelParams;
import avuilder4j.error.Avuilder4jException;

public class SqliteLoaderTypeModels extends SqliteLoader<Integer, TypeModel> {

	public static final String TABLE_NAME = "TypeModel";

	@Override
	public Map<Integer, TypeModel> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<Integer, TypeModel> onSqliteDataMapping(ResultSet r) throws Exception {
		TypeModelParams p = new TypeModelParams(SqliteLoader.<Integer>getWrapper(r, "idx"));
		p.setName(r.getString("name"));
		p.setDensityMod(SqliteLoader.<Double>getWrapper(r, "densityMod"));
		p.setDurabilityMod(SqliteLoader.<Double>getWrapper(r, "durabilityMod"));
		p.setMaterialCostMod(SqliteLoader.<Double>getWrapper(r, "materialCostMod"));
		p.setMechanics(SqliteLoader.<Double>getWrapper(r, "mechanics"));
		p.setEngineers(r.getDouble("engineers"));
		p.setProcess(SqliteLoader.getBoolean(r, "process"));
		p.setHasVolume(SqliteLoader.getBoolean(r, "hasVolume"));
		p.setCollisionReduction(SqliteLoader.<Double>getWrapper(r, "collisionReduction"));
		p.setComment(r.getString("comment"));

		TypeModel tm = new TypeModel(p);
		return new SimpleEntry<Integer, TypeModel>(tm.getIndex(), tm);
	}

}
