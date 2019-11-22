package avuilder4j.values.loaders;

import avuilder4j.dtos.map.Material;
import avuilder4j.dtos.map.Shape;
import avuilder4j.dtos.map.Type;
import avuilder4j.dtos.map.TypeModel;
import avuilder4j.dtos.map.TypeModelByMaterial;

public class GeneralDataMap {

	protected EntityMap<Material> materialMap;
	protected EntityMap<Shape> shapeMap;
	protected EntityMap<Type> typeMap;
	protected EntityMap<TypeModel> typeModelMap;
	protected EntityMap<TypeModelByMaterial> typeModelByMaterialMap;

	public EntityMap<Material> getMaterialMap() { return materialMap; }

	public void setMaterialMap(EntityMap<Material> materialMap) { this.materialMap = materialMap; }

	public EntityMap<Shape> getShapeMap() { return shapeMap; }

	public void setShapeMap(EntityMap<Shape> shapeMap) { this.shapeMap = shapeMap; }

	public EntityMap<Type> getTypeMap() { return typeMap; }

	public void setTypeMap(EntityMap<Type> typeMap) { this.typeMap = typeMap; }

	public EntityMap<TypeModel> getTypeModelMap() { return typeModelMap; }

	public void setTypeModelMap(EntityMap<TypeModel> typeModelMap) { this.typeModelMap = typeModelMap; }

	public EntityMap<TypeModelByMaterial> getTypeModelByMaterialMap() { return typeModelByMaterialMap; }

	public void setTypeModelByMaterialMap(EntityMap<TypeModelByMaterial> typeModelByMaterialMap) {
		this.typeModelByMaterialMap = typeModelByMaterialMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralDataMap START >>>\n");
		builder.append("materialMap = " + materialMap + ",\n");
		builder.append("typeModelMap = " + typeModelMap + ",\n");
		builder.append("typeModelByMaterialMap = " + typeModelByMaterialMap + ",\n");
		builder.append("typeMap = " + typeMap + ",\n");
		builder.append("shapeMap = " + shapeMap + "\n");
		builder.append("<<< GeneralDataMap END");
		return builder.toString();
	}

}
