package avuilder4j.structural;

public class BlockIndexer extends BlockIndexerGeneric<Block, Structure> {

	@Override
	protected Block getCuboidInstance() { return new Block(); }

	@Override
	protected Structure getStructureInstance() { return new Structure(); }

}
