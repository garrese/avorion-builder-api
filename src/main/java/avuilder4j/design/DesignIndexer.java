package avuilder4j.design;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.game.Block;
import avuilder4j.game.Structure;
import avuilder4j.game.TypeLook;
import avuilder4j.spatial.dtos.Lengths;
import avuilder4j.utils.AvUtils;

/**
 * Para tener todos bloques ya almacenados. <br>
 * Y llevar un index de manera natural y en tiempo real. <br>
 * De todos modos al intentar construir se volverá a checkear todo. Crea bloques y los va indexando.<br>
 * Permite adquirir bloques y reindexarlos coherentemente. <br>
 * Al importar un bloque comprueba si éste va
 */
public class DesignIndexer {

	private Structure structure = new Structure();
	private int indexCount;

	public String defaultColor = AvUtils.DEFAULT_COLOR;
	public Integer defaultMaterial = AvUtils.DEFAULT_MATERIAL;
	public Integer defaultType = AvUtils.DEFAULT_TYPE;
	public Lengths defaultLengths = new Lengths(AvUtils.DEFAULT_LENGTH, AvUtils.DEFAULT_LENGTH, AvUtils.DEFAULT_LENGTH);
	public TypeLook defaultOrientation = AvUtils.DEFAULT_ORIENTATION;

	public DesignIndexer() {}

	public void indexBlock(Block block) {
		indexCount++;
		block.setIndex(indexCount);
		structure.add(block);
	}

	public Block createBlockBlank() {
		Block b = new Block();
		indexBlock(b);
		return b;
	}

	public Block createBlock() {
		Block b = createBlockBlank();
		b.setColor(defaultColor);
		b.setMaterial(defaultMaterial);
		b.setType(defaultType);
		b.setLengths(defaultLengths);
		b.setTypeLook(defaultOrientation);
		return b;
	}

	public Block createBlock(Block parent) {
		Block b = createBlockBlank();
		b.setParent(parent);
		return b;
	}

	public void importBlocks(List<Block> blocks) {
		Collections.sort(blocks, (o1, o2) -> o1.getIndex().compareTo(o2.getIndex()));
		System.out.println("blocks sort: " + blocks);
		for (Block block : blocks) {
			indexImportedBlock(block);
		}
	}

	public void importBlock(Block block) {
		indexImportedBlock(block);
	}

	public boolean remove(Block block) {
		return structure.remove(block);
	}

	public int remove(List<Block> blocks) {
		int removed = 0;
		for (Block block : blocks) {
			if (this.structure.remove(block))
				removed++;
		}
		return removed;
	}

	public void clearBlocks() {
		structure.clear();
		indexCount = 0;
	}

	protected void indexImportedBlock(Block block) {
		if (block != null) {
			if (block.getIndex() == null || block.getIndex() <= indexCount) {
				indexBlock(block);
			} else {
				indexCount = block.getIndex();
				structure.add(block);
			}
		}
	}

	public List<Block> getStructure() { return structure; }

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
		for (Block block : structure) {
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
