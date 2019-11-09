package avuilder4j.entities;

import avuilder4j.dtos.MaterialStats;
import avuilder4j.dtos.Shape;
import avuilder4j.dtos.TypeStats;
import avuilder4j.entities.base.BlockGeneric;
import avuilder4j.entities.base.StructureGeneric;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.Mats;
import avuilder4j.values.loaders.Shapes;
import avuilder4j.values.loaders.Types;

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

	public double getVolume(BlockGeneric block) {
		block.validateBlock();
		TypeStats type = types.get(block.getType());
		Shape shape = shapes.get(type.getShape());

		double vol = block.getVolume() * type.getVolumeMod() * shape.getVolumeMod();
		return vol;
	}

	public double getVolume(StructureGeneric<? extends BlockGeneric> structure) {
		double total = 0;
		for (BlockGeneric cuboid : structure) {
			total += getVolume(cuboid);
		}
		return total;
	}

	public double getMass(BlockGeneric block) {
		MaterialStats mat = mats.get(block.getMaterial());
		TypeStats type = types.get(block.getType());

		double mass = getVolume(block) * mat.getBaseDensity() * type.getDensityMod();
		return mass;
	}

	public double getMass(StructureGeneric<? extends BlockGeneric> structure) throws Avuilder4jException {
		double totalMass = 0;
		for (BlockGeneric block : structure) {
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
