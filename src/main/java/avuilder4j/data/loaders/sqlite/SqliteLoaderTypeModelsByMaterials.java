package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.data.dtos.TypeModelByMaterial.MapIndex;
import avuilder4j.error.Avuilder4jException;

public final class SqliteLoaderTypeModelsByMaterials
		extends SqliteLoader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> {

	public static final String TABLE_NAME = "TypeModelByMaterial";

	@Override
	public Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<TypeModelByMaterial.MapIndex, TypeModelByMaterial> onSqliteDataMapping(ResultSet r)
			throws Exception {
		Integer typeModelIndex = SqliteLoader.<Integer>getWrapper(r, "typeModelIdx");
		Integer materialIndex = SqliteLoader.<Integer>getWrapper(r, "materialIdx");
		Double creditCostMod = SqliteLoader.<Double>getWrapper(r, "creditCostMod");
		Double meta1 = SqliteLoader.<Double>getWrapper(r, "effect");
		Double meta2 = SqliteLoader.<Double>getWrapper(r, "effect2");
		TypeModelByMaterial.MapIndex idx = new MapIndex(typeModelIndex, materialIndex);
		TypeModelByMaterial tmbm = new TypeModelByMaterial(idx, creditCostMod, meta1, meta2);
		return new SimpleEntry<TypeModelByMaterial.MapIndex, TypeModelByMaterial>(tmbm.getMapIndex(), tmbm);
	}

}
