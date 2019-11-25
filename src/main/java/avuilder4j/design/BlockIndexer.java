package avuilder4j.design;

import avuilder4j.data.DataMaps;
import avuilder4j.design.base.BlockFunctionalIndexerGeneric;

public class BlockIndexer extends BlockFunctionalIndexerGeneric<Block, Structure> {

	public BlockIndexer(DataMaps dataMaps) {
		super(dataMaps);
	}

	@Override
	protected Block getBlockInstance() { return new Block(getDataMaps()); }

	@Override
	protected Structure getStructureInstance() { return new Structure(); }

}
