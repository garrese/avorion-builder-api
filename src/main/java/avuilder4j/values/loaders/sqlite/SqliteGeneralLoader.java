package avuilder4j.values.loaders.sqlite;

import avuilder4j.values.loaders.GeneralLoader;

public class SqliteGeneralLoader extends GeneralLoader {

	@Override
	public void instantiateLoaders() {
		setMaterialLoader(new SqliteLoaderMaterial());
		setShapeLoader(new SqliteLoaderShape());
		setTypeLoader(new SqliteLoaderType());
		setTypeLoader(new SqliteLoaderType());
		setTypeModelLoader(new SqliteLoaderTypeModel());
		setTypeModelByMaterialLoader(new SqliteLoaderTypeModelByMaterial());
	}

}
