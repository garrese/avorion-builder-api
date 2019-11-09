package avuilder4j.entities;

import avuilder4j.entities.base.BlockIndexerGeneric;

public class BlockIndexer extends BlockIndexerGeneric<Block, Structure> {

	@Override
	protected Block getBlockInstance() { return new Block(); }

	@Override
	protected Structure getStructureInstance() { return new Structure(); }

}
