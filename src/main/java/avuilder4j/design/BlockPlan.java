package avuilder4j.design;

import avuilder4j.design.base.BlockPlanGeneric;

public class BlockPlan extends BlockPlanGeneric<BlockPlan> {
	private static final long serialVersionUID = -6081513872185469071L;

	@Override
	public BlockPlan chain() {
		return this;
	}

	@Override
	public BlockPlan getNewInstance() { return new BlockPlan(); }

}
