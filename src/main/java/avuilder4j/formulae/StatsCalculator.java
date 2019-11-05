package avuilder4j.formulae;

import avuilder4j.game.Block;
import avuilder4j.spatial.Cuboid;
import avuilder4j.spatial.CuboidalStructure;

public class StatsCalculator {

	public static double getVolume(CuboidalStructure<? extends Cuboid> cuboidStructure) {
		for (Cuboid cuboid : cuboidStructure) {
			cuboid.validateCuboid();
		}
		double total = 0;
		for (Cuboid cuboid : cuboidStructure) {
			total += cuboid.getVolume();
		}
		return total;
	}

	public static double getMass(Block block) {

		return 0;
	}

}
