package avuilder4j.data.loaders.sqlite;

import avuilder4j.data.loaders.base.DataLoader;
import avuilder4j.data.loaders.base.Loader;

public class SqliteDataLoader extends DataLoader {

	@Override
	public void instantiateLoaders() {
		setMaterialLoader(new SqliteLoaderMaterial());
		setMetaValueLoader(new SqliteLoaderMetaValue());
		setShapeLoader(new SqliteLoaderShape());
		setTypeLoader(new SqliteLoaderType());
		setTypeModelLoader(new SqliteLoaderTypeModel());
		setTypeModelByMaterialLoader(new SqliteLoaderTypeModelByMaterial());
	}

	@SuppressWarnings("rawtypes")
	public void setDbFilePath(String dbFilePath) {
		for (Loader loader : getAllLoaders()) {
			((SqliteLoader) loader).setDbFilePath(dbFilePath);
		}
	}

}
