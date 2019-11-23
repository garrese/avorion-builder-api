package avuilder4j.data.loaders.sqlite;

import java.sql.ResultSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import avuilder4j.data.TypeModelByMaterial;
import avuilder4j.data.TypeModelByMaterial.MapIndex;
import avuilder4j.data.loaders.base.TypeModelByMaterialLoader;
import avuilder4j.error.Avuilder4jException;

public final class SqliteLoaderTypeModelByMaterial
		extends SqliteLoader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> implements TypeModelByMaterialLoader {

	public static final String TABLE_NAME = "TypeModelByMaterial";

	@Override
	public Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> loadAll() throws Avuilder4jException {
		return super.connectAndLoadAll(TABLE_NAME);
	}

	@Override
	public SimpleEntry<TypeModelByMaterial.MapIndex, TypeModelByMaterial> onSqliteDataMapping(ResultSet r)
			throws Exception {
		int typeModelIndex = r.getInt("typeModelIdx");
		int materialIndex = r.getInt("materialIdx");
		Double creditCostMod = r.getDouble("creditCostMod");
		TypeModelByMaterial.MapIndex idx = new MapIndex(typeModelIndex, materialIndex);
		TypeModelByMaterial tmbm = new TypeModelByMaterial(idx, creditCostMod);
		return new SimpleEntry<TypeModelByMaterial.MapIndex, TypeModelByMaterial>(tmbm.getMapIndex(), tmbm);
	}

}
