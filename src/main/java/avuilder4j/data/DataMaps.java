package avuilder4j.data;

import java.util.Map;

public class DataMaps {

	protected static Map<Integer, Material> materialMap;
	protected static Map<String, MetaValue> metaValueMap;
	protected static Map<Integer, Shape> shapeMap;
	protected static Map<Integer, Type> typeMap;
	protected static Map<Integer, TypeModel> typeModelMap;
	protected static Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialMap;

	public static Map<Integer, Material> getMaterialMap() { return materialMap; }

	public static void setMaterialMap(Map<Integer, Material> materialMap) { DataMaps.materialMap = materialMap; }

	public static Map<String, MetaValue> getMetaValueMap() { return metaValueMap; }

	public static void setMetaValueMap(Map<String, MetaValue> metaValueMap) { DataMaps.metaValueMap = metaValueMap; }

	public static Map<Integer, Shape> getShapeMap() { return shapeMap; }

	public static void setShapeMap(Map<Integer, Shape> shapeMap) { DataMaps.shapeMap = shapeMap; }

	public static Map<Integer, Type> getTypeMap() { return typeMap; }

	public static void setTypeMap(Map<Integer, Type> typeMap) { DataMaps.typeMap = typeMap; }

	public static Map<Integer, TypeModel> getTypeModelMap() { return typeModelMap; }

	public static void setTypeModelMap(Map<Integer, TypeModel> typeModelMap) { DataMaps.typeModelMap = typeModelMap; }

	public static Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> getTypeModelByMaterialMap() {
		return typeModelByMaterialMap;
	}

	public static void setTypeModelByMaterialMap(
			Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialMap) {
		DataMaps.typeModelByMaterialMap = typeModelByMaterialMap;
	}

	public static String getReport() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataMap START >>>\n");
		builder.append("materialMap = " + formatMapString(materialMap) + ",\n");
		builder.append("metaValueMap = " + formatMapString(metaValueMap) + ",\n");
		builder.append("typeModelMap = " + formatMapString(typeModelMap) + ",\n");
		builder.append("typeModelByMaterialMap = " + formatMapString(typeModelByMaterialMap) + ",\n");
		builder.append("typeMap = " + formatMapString(typeMap) + ",\n");
		builder.append("shapeMap = " + formatMapString(shapeMap) + "\n");
		builder.append("<<< DataMap END");
		return builder.toString();
	}

	@SuppressWarnings("rawtypes")
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
