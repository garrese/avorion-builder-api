package avuilder.core.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder.core.entities.game.Block;

/**
 * Para tener todos bloques ya almacenados. <br>
 * Y llevar un index de manera natural y en tiempo real. <br>
 * De todos modos al intentar construir se volverá a checkear todo.
 *
 * Crea bloques y los va indexando.<br>
 * Permite adquirir bloques y reindexarlos coherentemente. <br>
 * Al importar un bloque comprueba si éste va
 */
public class StructureBuilder {

	private List<Block> blocks = new ArrayList<Block>();

	private int indexCount;

	public StructureBuilder() {
	}

	public void indexBlock(Block block) {
		indexCount++;
		block.setIndex(indexCount);
		blocks.add(block);
	}

	public Block createBlock() {
		Block b = new Block();
		indexBlock(b);
		return b;
	}

	public Block createBlock(Block parent) {
		Block b = createBlock();
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

	public void reviseIndexes() {

	}



}
