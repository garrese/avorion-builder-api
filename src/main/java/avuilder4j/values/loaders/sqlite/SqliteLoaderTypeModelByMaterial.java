package avuilder4j.values.loaders.sqlite;

import java.sql.ResultSet;

import avuilder4j.dtos.map.TypeModelByMaterial;
import avuilder4j.dtos.map.TypeModelByMaterial.TypeByMaterialMapIndex;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.EntityMap;
import avuilder4j.values.loaders.TypeModelByMaterialLoader;

public final class SqliteLoaderTypeModelByMaterial extends SqliteLoader<TypeModelByMaterial>
		implements TypeModelByMaterialLoader {

	public static final String TABLE_NAME = "TypeModelByMaterial";

	@Override
	public EntityMap<TypeModelByMaterial> loadAll() throws Avuilder4jException {
		return super.doConnectedAction(TABLE_NAME);
	}

	@Override
	public TypeModelByMaterial onSqliteDataMapping(ResultSet r) throws Exception {
		int typeModelIndex = r.getInt("typeModelIdx");
		int materialIndex = r.getInt("materialIdx");
		Double creditCostMod = r.getDouble("creditCostMod");
		TypeModelByMaterial.TypeByMaterialMapIndex idx = new TypeByMaterialMapIndex(typeModelIndex, materialIndex);
		TypeModelByMaterial tmbm = new TypeModelByMaterial(idx, creditCostMod);
		return tmbm;
	}

}
