package avuilder4j.design;

import avuilder4j.design.base.BlockFunctionalIndexerGeneric;

public class BlockIndexer extends BlockFunctionalIndexerGeneric<Block, Structure, BlockIndexer> {

	@Override
	protected Block getBlockInstance() { return new Block(); }

	@Override
	protected Structure getStructureInstance() { return new Structure(); }

	@Override
	public BlockIndexer chain() {
		return this;
	}

}
