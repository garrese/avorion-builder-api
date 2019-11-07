package avuilder4j.entities;

import avuilder4j.dtos.MaterialStats;
import avuilder4j.dtos.TypeStats;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.generics.BlockGeneric;
import avuilder4j.generics.StructureGeneric;
import avuilder4j.values.MaterialStatsCompilation;
import avuilder4j.values.TypeStatsCompilation;

@SuppressWarnings("rawtypes")
public class StatsCalculator {

	protected MaterialStatsCompilation mats = new MaterialStatsCompilation();
	protected TypeStatsCompilation types = new TypeStatsCompilation();

	public StatsCalculator() {}

	public double getVolume(BlockGeneric block) throws Avuilder4jException {
		block.validateBlock();
		TypeStats type = types.getStats(block.getType());

		double vol = block.getVolume() * type.getShapeVolMod();
		return vol;
	}

	public double getVolume(StructureGeneric<? extends BlockGeneric> structure) throws Avuilder4jException {
		double total = 0;
		for (BlockGeneric cuboid : structure) {
			total += getVolume(cuboid);
		}
		return total;
	}

	public double getMass(BlockGeneric block) throws Avuilder4jException {
		MaterialStats mat = mats.getStats(block.getMaterial());
		TypeStats type = types.getStats(block.getType());

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

}
