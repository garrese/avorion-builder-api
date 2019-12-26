package avuilder4j.design;

import avuilder4j.design.base.BlockPlanIndexerGeneric;

public class BlockPlanIndexer extends BlockPlanIndexerGeneric<BlockPlan, StructurePlan, BlockPlanIndexer> {

	@Override
	protected BlockPlan getBlockInstance() { return new BlockPlan(); }

	@Override
	protected StructurePlan getStructureInstance() { return new StructurePlan(); }

	@Override
	public BlockPlanIndexer chain() {
		return this;
	}

}
