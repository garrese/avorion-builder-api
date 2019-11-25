package avuilder4j.data.loaders.base;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.data.DataMaps;
import avuilder4j.data.Material;
import avuilder4j.data.Shape;
import avuilder4j.data.Type;
import avuilder4j.data.TypeModel;
import avuilder4j.data.TypeModelByMaterial;
import avuilder4j.error.Avuilder4jException;

public abstract class DataLoader {

	protected Loader<Integer, Material> materialLoader;
	protected Loader<Integer, Shape> shapeLoader;
	protected Loader<Integer, Type> typeLoader;
	protected Loader<Integer, TypeModel> typeModelLoader;
	protected Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialLoader;

	public DataLoader() {
		instantiateLoaders();
	}

	public abstract void instantiateLoaders();

	public Loader<Integer, Material> getMaterialLoader() { return materialLoader; }

	public void setMaterialLoader(Loader<Integer, Material> materialLoader) { this.materialLoader = materialLoader; }

	public Loader<Integer, Shape> getShapeLoader() { return shapeLoader; }

	public void setShapeLoader(Loader<Integer, Shape> shapeLoader) { this.shapeLoader = shapeLoader; }

	public Loader<Integer, Type> getTypeLoader() { return typeLoader; }

	public void setTypeLoader(Loader<Integer, Type> typeLoader) { this.typeLoader = typeLoader; }

	public Loader<Integer, TypeModel> getTypeModelLoader() { return typeModelLoader; }

	public void setTypeModelLoader(Loader<Integer, TypeModel> typeModelLoader) {
		this.typeModelLoader = typeModelLoader;
	}

	public Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> getTypeModelByMaterialLoader() {
		return typeModelByMaterialLoader;
	}

	public void setTypeModelByMaterialLoader(
			Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialLoader) {
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

	public DataMaps loadAll() throws Avuilder4jException {
		DataMaps map = new DataMaps();
		map.setMaterialMap(materialLoader.loadAll());
		map.setShapeMap(shapeLoader.loadAll());
		map.setTypeMap(typeLoader.loadAll());
		map.setTypeModelMap(typeModelLoader.loadAll());
		map.setTypeModelByMaterialMap(typeModelByMaterialLoader.loadAll());
		return map;

	}

}
