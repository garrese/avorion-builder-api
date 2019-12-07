package avuilder4j.data.loaders.sqlite;

import avuilder4j.data.loaders.base.DataLoader;
import avuilder4j.data.loaders.base.Loader;

public class SqliteDataLoader extends DataLoader {

	@Override
	public void instantiateLoaders() {
		setMaterialsLoader(new SqliteLoaderMaterials());
		setConstantsLoader(new SqliteLoaderConstants());
		setShapesLoader(new SqliteLoaderShapes());
		setTypesLoader(new SqliteLoaderTypes());
		setTypeModelsLoader(new SqliteLoaderTypeModels());
		setTypeModelsByMaterialsLoader(new SqliteLoaderTypeModelsByMaterials());
	}

	@SuppressWarnings("rawtypes")
	public void setDbFilePath(String dbFilePath) {
		for (Loader loader : getAllLoaders()) {
			((SqliteLoader) loader).setDbFilePath(dbFilePath);
		}
	}

}
