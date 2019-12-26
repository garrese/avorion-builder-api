package avuilder4j.design;

import avuilder4j.design.base.BlockFunctionalGeneric;

public class Block extends BlockFunctionalGeneric<Block> {
	private static final long serialVersionUID = -2263839017529377115L;

	@Override
	public Block chain() {
		return this;
	}

	@Override
	public Block getCopy() { return BlockPlan.copy(this, new Block()); }

}
