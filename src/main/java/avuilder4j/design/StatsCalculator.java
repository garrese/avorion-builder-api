package avuilder4j.design;

import avuilder4j.design.base.BlockPlanGeneric;
import avuilder4j.design.base.StructurePlanGeneric;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.util.values.Mats;
import avuilder4j.util.values.Shapes;
import avuilder4j.util.values.Types;

@SuppressWarnings("rawtypes")
public class StatsCalculator {

	protected Mats mats;
	protected Shapes shapes;
	protected Types types;

	public StatsCalculator() {
		mats = new Mats();
		types = new Types();
		shapes = new Shapes();
	}

	public double getVolume(BlockPlanGeneric block) {
//		block.validateBlock();
//		TypeStats type = types.get(block.getType());
//		Shape shape = shapes.get(type.getShape());
//
//		double vol = block.getVolume() * type.getVolumeMod() * shape.getVolumeMod();
//		return vol;
		return 0;
	}

	public double getVolume(StructurePlanGeneric<? extends BlockPlanGeneric> structure) {
		double total = 0;
		for (BlockPlanGeneric cuboid : structure) {
			total += getVolume(cuboid);
		}
		return total;
	}

	public double getMass(BlockPlanGeneric block) {
//		MaterialStats mat = mats.get(block.getMaterial());
//		TypeStats type = types.get(block.getType());
//
//		double mass = getVolume(block) * mat.getBaseDensity() * type.getDensityMod();
//		return mass;

		return 0;
	}

	public double getMass(StructurePlanGeneric<? extends BlockPlanGeneric> structure) throws Avuilder4jException {
		double totalMass = 0;
		for (BlockPlanGeneric block : structure) {
			totalMass += getMass(block);
		}
		return totalMass;
	}

	/**
	 * Gets the {@link #mats}.
	 * 
	 * @return the {@link #mats}.
	 */
	public Mats getMats() { return mats; }

	/**
	 * Sets the {@link #mats}.
	 * 
	 * @param mats the {@link #mats} to set.
	 */
	public void setMats(Mats mats) { this.mats = mats; }

	/**
	 * Gets the {@link #shapes}.
	 * 
	 * @return the {@link #shapes}.
	 */
	public Shapes getShapes() { return shapes; }

	/**
	 * Sets the {@link #shapes}.
	 * 
	 * @param shapes the {@link #shapes} to set.
	 */
	public void setShapes(Shapes shapes) { this.shapes = shapes; }

	/**
	 * Gets the {@link #types}.
	 * 
	 * @return the {@link #types}.
	 */
	public Types getTypes() { return types; }

	/**
	 * Sets the {@link #types}.
	 * 
	 * @param types the {@link #types} to set.
	 */
	public void setTypes(Types types) { this.types = types; }

}
