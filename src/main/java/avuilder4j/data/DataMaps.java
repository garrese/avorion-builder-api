package avuilder4j.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import avuilder4j.data.beans.BeanConstant;
import avuilder4j.data.beans.BeanCrew;
import avuilder4j.data.beans.BeanCrewCommand;
import avuilder4j.data.beans.BeanEffect;
import avuilder4j.data.beans.BeanMaterial;
import avuilder4j.data.beans.BeanShape;
import avuilder4j.data.beans.BeanType;
import avuilder4j.data.beans.BeanTypeModel;
import avuilder4j.data.beans.BeanTypeModelByMaterial;
import avuilder4j.data.dtos.BlockArchetype;
import avuilder4j.util.java.Nullable;

public class DataMaps {

	protected static Map<BlockArchetype.MapIndex, BlockArchetype> blockArchetypesMap;
	protected static Map<String, BeanConstant> constantsMap;
	protected static Map<BeanCrewCommand.MapIndex, BeanCrewCommand> crewCommandMap;
	protected static Map<Integer, BeanCrew> crewMap;
	protected static Map<BeanEffect.MapIndex, BeanEffect> effectsMap;
	protected static Map<Integer, BeanMaterial> materialsMap;
	protected static Map<Integer, BeanShape> shapesMap;
	protected static Map<BeanTypeModelByMaterial.MapIndex, BeanTypeModelByMaterial> typeModelsByMaterialsMap;
	protected static Map<Integer, BeanTypeModel> typeModelsMap;
	protected static Map<Integer, BeanType> typesMap;

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

	public static BlockArchetype getBlockArchetype(Integer typeIndex, Integer materialIndex) {
		BlockArchetype.MapIndex index = new BlockArchetype.MapIndex(typeIndex, materialIndex);
		return Nullable.m(() -> getBlockArchetypesMap().get(index));
	}

	public static Map<BlockArchetype.MapIndex, BlockArchetype> getBlockArchetypesMap() { return blockArchetypesMap; }

	public static BeanConstant getConstant(String constantName) {
		return Nullable.m(() -> getConstantsMap().get(constantName));
	}

	public static Map<String, BeanConstant> getConstantsMap() { return constantsMap; }

	public static Double getConstantValue(String constantName) {
		return Nullable.m(() -> getConstant(constantName).getValue());
	}

	public static BeanCrew getCrew(Integer crewIndex) {
		return Nullable.m(() -> crewMap.get(crewIndex));
	}

	public static BeanCrewCommand getCrewCommand(BeanCrewCommand.MapIndex index) {
		return Nullable.m(() -> crewCommandMap.get(index));
	}

	public static BeanCrewCommand getCrewCommand(Integer commander, Integer commanded) {
		BeanCrewCommand.MapIndex index = new BeanCrewCommand.MapIndex(commander, commanded);
		return Nullable.m(() -> crewCommandMap.get(index));
	}

	public static List<BeanCrewCommand> getCrewCommandListByCommander(Integer commander) {
		return getCrewCommandMap().values().stream().filter((c) -> Nullable.m(() -> c.getIndex().getCommander().equals(commander), false)).collect(Collectors.toList());
	}

	public static Map<BeanCrewCommand.MapIndex, BeanCrewCommand> getCrewCommandMap() { return crewCommandMap; }

	public static Map<Integer, BeanCrew> getCrewMap() { return crewMap; }

	public static BeanEffect getEffect(BeanEffect.MapIndex mapIndex) {
		return Nullable.m(() -> getEffectsMap().get(mapIndex));
	}

	public static BeanEffect getEffect(Integer typeModelIndex, Integer materialIndex, Integer n) {
		BeanEffect.MapIndex index = new BeanEffect.MapIndex(typeModelIndex, materialIndex, n);
		return getEffect(index);
	}

	public static List<BeanEffect> getEffects(Integer typeModelIndex, Integer materialIndex) {
		return getEffectsMap().values().stream().filter((e) -> Nullable.m(() -> {
			boolean type = e.getIndex().getTypeModelIndex().equals(typeModelIndex);
			boolean material = e.getIndex().getMaterialIndex().equals(materialIndex);
			return type && material;
		}, false)).collect(Collectors.toList());
	}

	public static Map<BeanEffect.MapIndex, BeanEffect> getEffectsMap() { return effectsMap; }

	public static List<Double> getEffectsValue(Integer typeModelIndex, Integer materialIndex) {
		return DataMaps.getEffects(typeModelIndex, materialIndex).stream().map(BeanEffect::getValue).collect(Collectors.toList());
	}

	public static Double getEffectValue(BeanEffect.MapIndex mapIndex) {
		return Nullable.m(() -> getEffectsMap().get(mapIndex).getValue());
	}

	public static Double getEffectValue(Integer typeModelIndex, Integer materialIndex, Integer n) {
		return Nullable.m(() -> getEffect(typeModelIndex, materialIndex, n).getValue());
	}

	public static BeanMaterial getMaterial(Integer materialIndex) {
		return Nullable.m(() -> getMaterialsMap().get(materialIndex));
	}

	public static Map<Integer, BeanMaterial> getMaterialsMap() { return materialsMap; }

	public static String getReport() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataMap START >>>\n");
		builder.append("materialsMap = " + formatMapString(materialsMap)).append(",\n");
		builder.append("crewMap = " + formatMapString(crewMap)).append(",\n");
		builder.append("crewCommandMap = " + formatMapString(crewCommandMap)).append(",\n");
		builder.append("effectsMap = " + formatMapString(effectsMap)).append(",\n");
		builder.append("metaValuesMap = " + formatMapString(constantsMap)).append(",\n");
		builder.append("typeModelsMap = " + formatMapString(typeModelsMap)).append(",\n");
		builder.append("typeModelsByMaterialsMap = " + formatMapString(typeModelsByMaterialsMap)).append(",\n");
		builder.append("typesMap = " + formatMapString(typesMap)).append(",\n");
		builder.append("shapesMap = " + formatMapString(shapesMap)).append(",\n");
//		builder.append("blockArchetypesMap = " + formatMapString(blockArchetypesMap)).append(",\n");
		builder.append("<<< DataMap END");
		return builder.toString();
	}

	public static BeanShape getShape(Integer shapeIndex) {
		return Nullable.m(() -> getShapesMap().get(shapeIndex));
	}

	public static Map<Integer, BeanShape> getShapesMap() { return shapesMap; }

	public static BeanType getType(Integer typeIndex) {
		return Nullable.m(() -> getTypesMap().get(typeIndex));
	}

	public static BeanTypeModel getTypeModel(Integer typeModelIndex) {
		return Nullable.m(() -> getTypeModelsMap().get(typeModelIndex));
	}

	public static BeanTypeModelByMaterial getTypeModelByMaterial(BeanTypeModelByMaterial.MapIndex mapIndex) {
		return Nullable.m(() -> getTypeModelsByMaterialsMap().get(mapIndex));
	}

	public static BeanTypeModelByMaterial getTypeModelByMaterial(Integer typeModelIndex, Integer materialIndex) {
		BeanTypeModelByMaterial.MapIndex idx = new BeanTypeModelByMaterial.MapIndex(typeModelIndex, materialIndex);
		return getTypeModelByMaterial(idx);
	}

	public static Map<BeanTypeModelByMaterial.MapIndex, BeanTypeModelByMaterial> getTypeModelsByMaterialsMap() { return typeModelsByMaterialsMap; }

	public static Map<Integer, BeanTypeModel> getTypeModelsMap() { return typeModelsMap; }

	public static Map<Integer, BeanType> getTypesMap() { return typesMap; }

	public static void setBlockArchetypesMap(Map<BlockArchetype.MapIndex, BlockArchetype> blockArchetypesMap) { DataMaps.blockArchetypesMap = blockArchetypesMap; }

	public static void setConstantsMap(Map<String, BeanConstant> metaValueMap) { DataMaps.constantsMap = metaValueMap; }

	public static void setCrewCommandMap(Map<BeanCrewCommand.MapIndex, BeanCrewCommand> crewCommandMap) { DataMaps.crewCommandMap = crewCommandMap; }

	public static void setCrewMap(Map<Integer, BeanCrew> crewMap) { DataMaps.crewMap = crewMap; }

	public static void setEffectsMap(Map<BeanEffect.MapIndex, BeanEffect> effectsMap) { DataMaps.effectsMap = effectsMap; }

	public static void setMaterialsMap(Map<Integer, BeanMaterial> materialMap) { DataMaps.materialsMap = materialMap; }

	public static void setShapesMap(Map<Integer, BeanShape> shapeMap) { DataMaps.shapesMap = shapeMap; }

	public static void setTypeModelsByMaterialsEffectsMap(Map<BeanEffect.MapIndex, BeanEffect> typeModelsByMaterialsEffectsMap) { DataMaps.effectsMap = typeModelsByMaterialsEffectsMap; }

	public static void setTypeModelsByMaterialsMap(Map<BeanTypeModelByMaterial.MapIndex, BeanTypeModelByMaterial> typeModelByMaterialMap) { DataMaps.typeModelsByMaterialsMap = typeModelByMaterialMap; }

	public static void setTypeModelsMap(Map<Integer, BeanTypeModel> typeModelMap) { DataMaps.typeModelsMap = typeModelMap; }

	public static void setTypesMap(Map<Integer, BeanType> typeMap) { DataMaps.typesMap = typeMap; }

}
