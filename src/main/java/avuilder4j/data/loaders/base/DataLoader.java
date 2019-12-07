package avuilder4j.data.loaders.base;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.Constant;
import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.Shape;
import avuilder4j.data.dtos.Type;
import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.error.Avuilder4jException;

public abstract class DataLoader {

	protected Loader<Integer, Material> materialsLoader;
	protected Loader<String, Constant> constantsLoader;
	protected Loader<Integer, Shape> shapesLoader;
	protected Loader<Integer, Type> typesLoader;
	protected Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelsByMaterialsLoader;
	protected Loader<Integer, TypeModel> typeModelsLoader;

	public DataLoader() {
		instantiateLoaders();
	}

	@SuppressWarnings("rawtypes")
	public List<Loader> getAllLoaders() {
		List<Loader> loaders = new ArrayList<>();
		loaders.add(materialsLoader);
		loaders.add(constantsLoader);
		loaders.add(shapesLoader);
		loaders.add(typesLoader);
		loaders.add(typeModelsLoader);
		loaders.add(typeModelsByMaterialsLoader);
		return loaders;
	}

	public Loader<Integer, Material> getMaterialsLoader() { return materialsLoader; }

	public Loader<String, Constant> getConstantsLoader() { return constantsLoader; }

	public Loader<Integer, Shape> getShapesLoader() { return shapesLoader; }

	public Loader<Integer, Type> getTypesLoader() { return typesLoader; }

	public Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> getTypeModelsByMaterialsLoader() {
		return typeModelsByMaterialsLoader;
	}

	public Loader<Integer, TypeModel> getTypeModelsLoader() { return typeModelsLoader; }

	public abstract void instantiateLoaders();

	public void loadAll() throws Avuilder4jException {
		DataMaps.setMaterialsMap(materialsLoader.loadAll());
		DataMaps.setConstantsMap(constantsLoader.loadAll());
		DataMaps.setShapesMap(shapesLoader.loadAll());
		DataMaps.setTypesMap(typesLoader.loadAll());
		DataMaps.setTypeModelsMap(typeModelsLoader.loadAll());
		DataMaps.setTypeModelsByMaterialsMap(typeModelsByMaterialsLoader.loadAll());

	}

	protected void setMaterialsLoader(Loader<Integer, Material> materialsLoader) {
		this.materialsLoader = materialsLoader;
	}

	protected void setConstantsLoader(Loader<String, Constant> constantsLoader) {
		this.constantsLoader = constantsLoader;
	}

	protected void setShapesLoader(Loader<Integer, Shape> shapesLoader) { this.shapesLoader = shapesLoader; }

	protected void setTypesLoader(Loader<Integer, Type> typesLoader) { this.typesLoader = typesLoader; }

	protected void setTypeModelsByMaterialsLoader(
			Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelsByMaterialsLoader) {
		this.typeModelsByMaterialsLoader = typeModelsByMaterialsLoader;
	}

	protected void setTypeModelsLoader(Loader<Integer, TypeModel> typeModelsLoader) {
		this.typeModelsLoader = typeModelsLoader;
	}

}
