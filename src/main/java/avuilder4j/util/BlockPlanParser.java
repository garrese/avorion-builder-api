package avuilder4j.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import avuilder4j.design.base.BlockInterfaceExporter;
import avuilder4j.design.base.BlockInterfaceImporter;

public class BlockPlanParser {

	public static <T extends BlockInterfaceImporter<T>> List<T> parse(List<? extends BlockInterfaceExporter> listToParse, Supplier<T> supplier) {

		HashMap<Integer, T> blocksMap = new LinkedHashMap<>();
		HashMap<Integer, Integer> parentsMap = new HashMap<>();

		for (BlockInterfaceExporter exporter : listToParse) {
			T importer = supplier.get();

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

			T block = blocksMap.get(parentRegistry.getKey());
			T parent = blocksMap.get(parentRegistry.getValue());
			block.setParent(parent);
		}

		return blocksMap.values().stream().collect(Collectors.toList());

	}
}
