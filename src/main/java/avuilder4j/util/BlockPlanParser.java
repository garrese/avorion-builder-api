package avuilder4j.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import avuilder4j.design.base.BlockPlanInterfaceExporter;
import avuilder4j.design.base.BlockPlanInterfaceImporter;

public class BlockPlanParser {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends BlockPlanInterfaceImporter> List<T> parse(
			List<? extends BlockPlanInterfaceExporter> listToParse, Supplier<T> supplier) {

		HashMap<Integer, BlockPlanInterfaceImporter> blocksMap = new LinkedHashMap<>();
		HashMap<Integer, Integer> parentsMap = new HashMap<>();

		for (BlockPlanInterfaceExporter exporter : listToParse) {
			BlockPlanInterfaceImporter importer = supplier.get();

			importer.setIndex(exporter.getIndex());
			importer.setXL(exporter.getXL());
			importer.setXU(exporter.getXU());
			importer.setYL(exporter.getYL());
			importer.setYU(exporter.getYU());
			importer.setZL(exporter.getZL());
			importer.setZU(exporter.getZU());
			importer.setTypeIndex(exporter.getTypeIndex());
			importer.setMaterialIndex(exporter.getMaterialIndex());
			importer.setLook(exporter.getLook());
			importer.setUp(exporter.getUp());
			importer.setColor(exporter.getColor());

			parentsMap.put(exporter.getIndex(), exporter.getParentIndex());
			blocksMap.put(exporter.getIndex(), importer);
		}

		for (Map.Entry<Integer, Integer> parentRegistry : parentsMap.entrySet()) {

			BlockPlanInterfaceImporter block = blocksMap.get(parentRegistry.getKey());
			BlockPlanInterfaceImporter parent = blocksMap.get(parentRegistry.getValue());
			block.setParent(parent);
		}

		return blocksMap.values().stream().collect(Collectors.toList());

	}
}
