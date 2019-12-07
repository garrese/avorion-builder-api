package avuilder4j.data;

import java.util.Map;

import avuilder4j.data.dtos.Constant;
import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.Shape;
import avuilder4j.data.dtos.Type;
import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.util.java.NullSafe;

public class DataMaps {

	protected static Map<Integer, Material> materialMap;
	protected static Map<String, Constant> ConstantsMap;
	protected static Map<Integer, Shape> shapesMap;
	protected static Map<Integer, Type> typesMap;
	protected static Map<Integer, TypeModel> typeModelsMap;
	protected static Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelsByMaterialsMap;

	public static Map<Integer, Material> getMaterialsMap() { return materialMap; }

	public static void setMaterialsMap(Map<Integer, Material> materialMap) { DataMaps.materialMap = materialMap; }

	public static Material getMaterial(Object key) {
		return NullSafe.get(() -> getMaterialsMap().get(key));
	}

	public static Map<String, Constant> getConstantsMap() { return ConstantsMap; }

	public static Constant getConstant(Object key) {
		return NullSafe.get(() -> getConstantsMap().get(key));
	}

	public static Double getConstantValue(Object key) {
		return NullSafe.get(() -> getConstant(key).getValue());
	}

	public static void setConstantsMap(Map<String, Constant> metaValueMap) { DataMaps.ConstantsMap = metaValueMap; }

	public static Map<Integer, Shape> getShapesMap() { return shapesMap; }

	public static Shape getShape(Object key) {
		return NullSafe.get(() -> getShapesMap().get(key));
	}

	public static void setShapesMap(Map<Integer, Shape> shapeMap) { DataMaps.shapesMap = shapeMap; }

	public static Map<Integer, Type> getTypesMap() { return typesMap; }

	public static Type getType(Object key) {
		return NullSafe.get(() -> getTypesMap().get(key));
	}

	public static void setTypesMap(Map<Integer, Type> typeMap) { DataMaps.typesMap = typeMap; }

	public static Map<Integer, TypeModel> getTypeModelsMap() { return typeModelsMap; }

	public static TypeModel getTypeModel(Object key) {
		return NullSafe.get(() -> getTypeModelsMap().get(key));
	}

	public static void setTypeModelsMap(Map<Integer, TypeModel> typeModelMap) { DataMaps.typeModelsMap = typeModelMap; }

	public static Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> getTypeModelsByMaterialsMap() {
		return typeModelsByMaterialsMap;
	}

	public static TypeModelByMaterial getTypeModelByMaterial(Object key) {
		return NullSafe.get(() -> getTypeModelsByMaterialsMap().get(key));
	}

	public static void setTypeModelsByMaterialsMap(
			Map<TypeModelByMaterial.MapIndex, TypeModelByMaterial> typeModelByMaterialMap) {
		DataMaps.typeModelsByMaterialsMap = typeModelByMaterialMap;
	}

	public static String getReport() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataMap START >>>\n");
		builder.append("materialsMap = " + formatMapString(materialMap) + ",\n");
		builder.append("metaValuesMap = " + formatMapString(ConstantsMap) + ",\n");
		builder.append("typeModelsMap = " + formatMapString(typeModelsMap) + ",\n");
		builder.append("typeModelsByMaterialsMap = " + formatMapString(typeModelsByMaterialsMap) + ",\n");
		builder.append("typesMap = " + formatMapString(typesMap) + ",\n");
		builder.append("shapesMap = " + formatMapString(shapesMap) + "\n");
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
