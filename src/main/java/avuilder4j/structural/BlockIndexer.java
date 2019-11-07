package avuilder4j.structural;

public class BlockIndexer extends BlockIndexerGeneric<Block, Structure> {

	@Override
	protected Block getBlockInstance() { return new Block(); }

	@Override
	protected Structure getStructureInstance() { return new Structure(); }

}
