package avuilder4j.data;

import java.util.Map;

public class DataMaps {

	protected Map<Integer, Material> materialMap;
	protected Map<Integer, Shape> shapeMap;
	protected Map<Integer, Type> typeMap;
	protected Map<Integer, TypeModel> typeModelMap;
	protected Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialMap;

	public Map<Integer, Material> getMaterialMap() { return materialMap; }

	public void setMaterialMap(Map<Integer, Material> materialMap) { this.materialMap = materialMap; }

	public Map<Integer, Shape> getShapeMap() { return shapeMap; }

	public void setShapeMap(Map<Integer, Shape> shapeMap) { this.shapeMap = shapeMap; }

	public Map<Integer, Type> getTypeMap() { return typeMap; }

	public void setTypeMap(Map<Integer, Type> typeMap) { this.typeMap = typeMap; }

	public Map<Integer, TypeModel> getTypeModelMap() { return typeModelMap; }

	public void setTypeModelMap(Map<Integer, TypeModel> typeModelMap) { this.typeModelMap = typeModelMap; }

	public Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> getTypeModelByMaterialMap() {
		return typeModelByMaterialMap;
	}

	public void setTypeModelByMaterialMap(
			Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialMap) {
		this.typeModelByMaterialMap = typeModelByMaterialMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataMap START >>>\n");
		builder.append("materialMap = " + formatMapString(materialMap) + ",\n");
		builder.append("typeModelMap = " + formatMapString(typeModelMap) + ",\n");
		builder.append("typeModelByMaterialMap = " + formatMapString(typeModelByMaterialMap) + ",\n");
		builder.append("typeMap = " + formatMapString(typeMap) + ",\n");
		builder.append("shapeMap = " + formatMapString(shapeMap) + "\n");
		builder.append("<<< DataMap END");
		return builder.toString();
	}

	public static String formatMapString(Map map) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");

		int i = 0;
		for (Object o : map.values()) {
			i++;
			sb.append("\t").append(i).append("º: ").append(o).append("\n");
		}

		sb.append("}");
		return sb.toString();
	}

}
