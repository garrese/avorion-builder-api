package avuilder4j.data.loaders.base;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.MetaValue;
import avuilder4j.data.dtos.Shape;
import avuilder4j.data.dtos.Type;
import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.error.Avuilder4jException;

public abstract class DataLoader {

	protected Loader<Integer, Material> materialLoader;
	protected Loader<String, MetaValue> metaValueLoader;
	protected Loader<Integer, Shape> shapeLoader;
	protected Loader<Integer, Type> typeLoader;
	protected Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialLoader;
	protected Loader<Integer, TypeModel> typeModelLoader;

	public DataLoader() {
		instantiateLoaders();
	}

	@SuppressWarnings("rawtypes")
	public List<Loader> getAllLoaders() {
		List<Loader> loaders = new ArrayList<>();
		loaders.add(materialLoader);
		loaders.add(metaValueLoader);
		loaders.add(shapeLoader);
		loaders.add(typeLoader);
		loaders.add(typeModelLoader);
		loaders.add(typeModelByMaterialLoader);
		return loaders;
	}

	public Loader<Integer, Material> getMaterialLoader() { return materialLoader; }

	public Loader<String, MetaValue> getMetaValueLoader() { return metaValueLoader; }

	public Loader<Integer, Shape> getShapeLoader() { return shapeLoader; }

	public Loader<Integer, Type> getTypeLoader() { return typeLoader; }

	public Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> getTypeModelByMaterialLoader() {
		return typeModelByMaterialLoader;
	}

	public Loader<Integer, TypeModel> getTypeModelLoader() { return typeModelLoader; }

	public abstract void instantiateLoaders();

	public void loadAll() throws Avuilder4jException {
		DataMaps.setMaterialMap(materialLoader.loadAll());
		DataMaps.setMetaValueMap(metaValueLoader.loadAll());
		DataMaps.setShapeMap(shapeLoader.loadAll());
		DataMaps.setTypeMap(typeLoader.loadAll());
		DataMaps.setTypeModelMap(typeModelLoader.loadAll());
		DataMaps.setTypeModelByMaterialMap(typeModelByMaterialLoader.loadAll());

	}

	protected void setMaterialLoader(Loader<Integer, Material> materialLoader) { this.materialLoader = materialLoader; }

	protected void setMetaValueLoader(Loader<String, MetaValue> metaValueLoader) {
		this.metaValueLoader = metaValueLoader;
	}

	protected void setShapeLoader(Loader<Integer, Shape> shapeLoader) { this.shapeLoader = shapeLoader; }

	protected void setTypeLoader(Loader<Integer, Type> typeLoader) { this.typeLoader = typeLoader; }

	protected void setTypeModelByMaterialLoader(
			Loader<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialLoader) {
		this.typeModelByMaterialLoader = typeModelByMaterialLoader;
	}

	protected void setTypeModelLoader(Loader<Integer, TypeModel> typeModelLoader) {
		this.typeModelLoader = typeModelLoader;
	}

}
