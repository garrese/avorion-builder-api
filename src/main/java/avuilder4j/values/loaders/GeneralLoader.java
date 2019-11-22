package avuilder4j.values.loaders;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.error.Avuilder4jException;

public abstract class GeneralLoader {

	protected MaterialLoader materialLoader;
	protected ShapeLoader shapeLoader;
	protected TypeLoader typeLoader;
	protected TypeModelLoader typeModelLoader;
	protected TypeModelByMaterialLoader typeModelByMaterialLoader;

	public GeneralLoader() {
		instantiateLoaders();
	}

	public abstract void instantiateLoaders();

	public MaterialLoader getMaterialLoader() { return materialLoader; }

	public void setMaterialLoader(MaterialLoader materialLoader) { this.materialLoader = materialLoader; }

	public ShapeLoader getShapeLoader() { return shapeLoader; }

	public void setShapeLoader(ShapeLoader shapeLoader) { this.shapeLoader = shapeLoader; }

	public TypeLoader getTypeLoader() { return typeLoader; }

	public void setTypeLoader(TypeLoader typeLoader) { this.typeLoader = typeLoader; }

	public TypeModelLoader getTypeModelLoader() { return typeModelLoader; }

	public void setTypeModelLoader(TypeModelLoader typeModelLoader) { this.typeModelLoader = typeModelLoader; }

	public TypeModelByMaterialLoader getTypeModelByMaterialLoader() { return typeModelByMaterialLoader; }

	public void setTypeModelByMaterialLoader(TypeModelByMaterialLoader typeModelByMaterialLoader) {
		this.typeModelByMaterialLoader = typeModelByMaterialLoader;
	}

	@SuppressWarnings("rawtypes")
	public List<Loader> getAllLoaders() {
		List<Loader> loaders = new ArrayList<>();
		loaders.add(materialLoader);
		loaders.add(shapeLoader);
		loaders.add(typeLoader);
		loaders.add(typeModelLoader);
		loaders.add(typeModelByMaterialLoader);
		return loaders;
	}

	public GeneralDataMap loadAll() throws Avuilder4jException {
		GeneralDataMap map = new GeneralDataMap();
		map.setMaterialMap(materialLoader.loadAll());
		map.setShapeMap(shapeLoader.loadAll());
		map.setTypeMap(typeLoader.loadAll());
		map.setTypeModelMap(typeModelLoader.loadAll());
		map.setTypeModelByMaterialMap(typeModelByMaterialLoader.loadAll());
		return map;

	}

}
