package avuilder4j.design;

import java.util.Collection;

import avuilder4j.design.base.BlockPlanStructureGeneric;

public class StructurePlan extends BlockPlanStructureGeneric<BlockPlan, StructurePlan> {
	private static final long serialVersionUID = -3678922648149746004L;

	public StructurePlan() {
		super();
	}

	public StructurePlan(Collection<? extends BlockPlan> c) {
		super(c);
	}

	public StructurePlan(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public StructurePlan chain() {
		return this;
	}

}
