package avuilder.core.managers;

import java.util.ArrayList;
import java.util.List;

import avuilder.core.entities.game.Block;

/**
 * Para tener todos bloques ya almacenados. Y llevar un index de manera natural
 * y en tiempo real. De todos modos al intentar construir se volverá a checkear
 * todo.
 *
 */
public class StructureBuilder {

	private List<Block> blocks = new ArrayList<Block>();

	private int indexCount;

	public StructureBuilder() {
	}

	public Block createBlock() {
		Block b = new Block();
		b.setIndex(indexCount);
		blocks.add(b);
		indexCount++;
		return b;
	}

	public Block createBlock(Block parent) {
		Block b = createBlock();
		b.setParent(parent);
		return b;
	}

	public void importBlocks(List<Block> blocks) {
		for (Block block : blocks) {
			reviseIndexCount(block);
		}
		for (Block block : blocks) {
			reviseBlockIndex(block);
			this.blocks.add(block);
		}
	}

	public void importBlock(Block block) {
		reviseIndexCount(block);
		reviseBlockIndex(block);
		blocks.add(block);
	}

	public void clearBlocks() {
		blocks.clear();
		indexCount = 0;
	}

	protected void reviseIndexCount(Block block) {
		if (block != null && block.getIndex() != null) {
			if (block.getIndex() > indexCount) {
				indexCount = block.getIndex() + 1;
			}
		}
	}

	protected void reviseBlockIndex(Block block) {
		if (block != null && block.getIndex() != null) {
			block.setIndex(indexCount);
			indexCount++;
		}
	}

	public List<Block> exportBlocks() {
		List<Block> blocks = new ArrayList<Block>();
		for (Block block : this.blocks) {
			blocks.add(Block.deepCopy(block));
		}
		return blocks;
	}

}
