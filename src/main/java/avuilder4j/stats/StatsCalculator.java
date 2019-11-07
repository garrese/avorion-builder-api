package avuilder4j.stats;

import avuilder4j.error.Avuilder4jException;
import avuilder4j.stats.dtos.MaterialStats;
import avuilder4j.stats.dtos.TypeStats;
import avuilder4j.stats.values.MaterialStatsCompilation;
import avuilder4j.stats.values.TypeStatsCompilation;
import avuilder4j.structural.BlockGeneric;
import avuilder4j.structural.StructureGeneric;

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
