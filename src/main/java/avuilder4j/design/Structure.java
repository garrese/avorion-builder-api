package avuilder4j.design;

import avuilder4j.design.base.BlockFunctionalStructureGeneric;
import avuilder4j.design.base.BlockPlanInterface;
import avuilder4j.design.base.BlockPlanStructureGeneric;
import avuilder4j.design.sub.dimensional.Orientation;

public class Structure extends BlockFunctionalStructureGeneric<Block> {
	private static final long serialVersionUID = -7289127183511354727L;

	public Structure() {}

	public Structure(BlockPlanStructureGeneric<? extends BlockPlanInterface> s) {

		for (BlockPlanInterface plan : s) {
			Block b = new Block();

			b.setIndexInStructure(plan.getIndexInStructure());
			b.setBlockArchetype(plan.getTypeIndex(), plan.getMaterialIndex());
			b.getAxisX().setLowerEnd(plan.getLX());
			b.getAxisX().setUpperEnd(plan.getUX());
			b.getAxisY().setLowerEnd(plan.getLY());
			b.getAxisY().setUpperEnd(plan.getUY());
			b.getAxisZ().setLowerEnd(plan.getLZ());
			b.getAxisZ().setUpperEnd(plan.getUZ());
			b.setColor(plan.getColor());
			b.setOrientation(new Orientation(plan.getLook(), plan.getUp()));

			add(b);
		}

		for (BlockPlanInterface plan : s) {
			Integer index = plan.getIndexInStructure();
			Integer parent = plan.getParentIndex();
			s.findByIndex(index).setParent(s.findByIndex(parent));
		}

	}
}
