package avuilder4j.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.entities.game.Block;
import avuilder4j.entities.game.Orientation;
import avuilder4j.utils.AvK;

/**
 * Para tener todos bloques ya almacenados. <br>
 * Y llevar un index de manera natural y en tiempo real. <br>
 * De todos modos al intentar construir se volverá a checkear todo.
 *
 * Crea bloques y los va indexando.<br>
 * Permite adquirir bloques y reindexarlos coherentemente. <br>
 * Al importar un bloque comprueba si éste va
 */
public class StructureBlocksProvider {

	private List<Block> blocks = new ArrayList<Block>();

	private int indexCount;
	public String defaultColor = AvK.DEFAULT_COLOR;
	public Integer defaultMaterial = AvK.DEFAULT_MATERIAL;
	public Integer defaultType = AvK.DEFAULT_TYPE;
	public Double defaultLengthX = AvK.DEFAULT_LENGTH;
	public Double defaultLengthY = AvK.DEFAULT_LENGTH;
	public Double defaultLengthZ = AvK.DEFAULT_LENGTH;
	public Orientation defaultOrientation = AvK.ORIENTATION_ZERO;


	public StructureBlocksProvider() {
	}

	public void indexBlock(Block block) {
		indexCount++;
		block.setIndex(indexCount);
		blocks.add(block);
	}

	public Block createBlankBlock() {
		Block b = new Block();
		indexBlock(b);
		return b;
	}

	public Block createBlock() {
		Block b = createBlankBlock();
		b.setColor(defaultColor);
		b.setMaterial(defaultMaterial);
		b.setType(defaultType);
		b.setLengths(defaultLengthX, defaultLengthY, defaultLengthZ);
		b.setOrientation(defaultOrientation);
		return b;
	}

	public Block createBlock(Block parent) {
		Block b = createBlankBlock();
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
		return blocks.remove(block);
	}

	public int remove(List<Block> blocks) {
		int removed = 0;
		for (Block block : blocks) {
			if (this.blocks.remove(block))
				removed++;
		}
		return removed;
	}

	public void clearBlocks() {
		blocks.clear();
		indexCount = 0;
	}

	protected void indexImportedBlock(Block block) {
		if (block != null) {
			if (block.getIndex() == null || block.getIndex() <= indexCount) {
				indexBlock(block);
			} else {
				indexCount = block.getIndex();
				blocks.add(block);
			}
		}
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public int getIndexCount() {
		return indexCount;
	}

	public String getBlocksReport() {
		String report = "";
		for (int i = 0; i < blocks.size(); i++) {
			report += blocks.get(i) + "\n";
		}
		report += "Total blocks= " + blocks.size();
		return report;
	}

	public void getIndexesMap() {
		Map<Integer, Integer> indexes = new HashMap<>();
		for (Block block : blocks) {
			Integer index = block.getIndex();
			if (index != null)
				indexes.put(index, index);
		}
	}


}
