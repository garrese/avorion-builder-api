package avuilder4j.design;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.spatial.auxs.Lengths;
import avuilder4j.structural.BlockGeneric;
import avuilder4j.structural.StructureGeneric;
import avuilder4j.structural.dtos.TypeLook;
import avuilder4j.structural.values.Colors;
import avuilder4j.structural.values.Mats;
import avuilder4j.structural.values.Types;

/**
 * Para tener todos bloques ya almacenados. <br>
 * Y llevar un index de manera natural y en tiempo real. <br>
 * De todos modos al intentar construir se volverá a checkear todo. Crea bloques y los va indexando.<br>
 * Permite adquirir bloques y reindexarlos coherentemente. <br>
 * Al importar un bloque comprueba si éste va
 */
public abstract class DesignIndexerGeneric<T extends BlockGeneric> {

	private StructureGeneric<T> structure = new StructureGeneric<T>();
	private int indexCount;

	public String defaultColor = Colors.MATERIAL_00_IRON;
	public Integer defaultMaterial = Mats.IRON;
	public Integer defaultType = Types.BLANK_HULL;
	public Lengths defaultLengths = new Lengths(2, 2, 2);
	public TypeLook defaultOrientation = new TypeLook();

	public DesignIndexerGeneric() {}

	public void indexBlock(T block) {
		indexCount++;
		block.setIndex(indexCount);
		structure.add(block);
	}

	public abstract T createBlockBlank();
//	public T createBlockBlank() {
//		T b = T;
//		indexBlock(b);
//		return b;
//	}

	public T createBlock() {
		T b = createBlockBlank();
		indexBlock(b);
		b.setColor(defaultColor);
		b.setMaterial(defaultMaterial);
		b.setType(defaultType);
		b.setLengths(defaultLengths);
		b.setTypeLook(defaultOrientation);
		return b;
	}

	public T createBlock(T parent) {
		T b = createBlockBlank();
		indexBlock(b);
		b.setParent(parent);
		return b;
	}

	public void importBlocks(List<BlockGeneric> blocks) {
		Collections.sort(blocks, (o1, o2) -> o1.getIndex().compareTo(o2.getIndex()));
		System.out.println("blocks sort: " + blocks);
		for (BlockGeneric block : blocks) {
			indexImportedBlock(block);
		}
	}

	public void importBlock(BlockGeneric block) {
		indexImportedBlock(block);
	}

	public boolean remove(BlockGeneric block) {
		return structure.remove(block);
	}

	public int remove(List<BlockGeneric> blocks) {
		int removed = 0;
		for (BlockGeneric block : blocks) {
			if (this.structure.remove(block))
				removed++;
		}
		return removed;
	}

	public void clearBlocks() {
		structure.clear();
		indexCount = 0;
	}

	protected void indexImportedBlock(BlockGeneric block) {
		if (block != null) {
			if (block.getIndex() == null || block.getIndex() <= indexCount) {
				indexBlock(block);
			} else {
				indexCount = block.getIndex();
				structure.add(block);
			}
		}
	}

	public List<BlockGeneric> getStructure() { return structure; }

	public int getIndexCount() { return indexCount; }

	public String getDesignReport() {
		String report = "";
		for (int i = 0; i < structure.size(); i++) {
			report += structure.get(i) + "\n";
		}
		report += "Total blocks= " + structure.size();
		return report;
	}

	public void getIndexesMap() {
		Map<Integer, Integer> indexes = new HashMap<>();
		for (BlockGeneric block : structure) {
			Integer index = block.getIndex();
			if (index != null)
				indexes.put(index, index);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StructureBlocksProvider [indexCount=" + indexCount + ", defaultColor=" + defaultColor
				+ ", defaultMaterial=" + defaultMaterial + ", defaultType=" + defaultType + ", defaultLengths="
				+ defaultLengths + ", defaultOrientation=" + defaultOrientation + "]";
	}

}
