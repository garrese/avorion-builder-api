package avuilder4j.stats.values;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.stats.dtos.TypeStats;
import avuilder4j.structural.values.Types;

public class TypeStatsCompilation {
	protected Map<Integer, TypeStats> typeStats;

	public TypeStatsCompilation() {
		typeStats = new HashMap<Integer, TypeStats>();
		// @formatter:off

		addToMap(Types.BLANK_HULL, Shapes.FILLED, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);

		// @formatter:on
		this.typeStats = Collections.unmodifiableMap(typeStats);
	}

	private void addToMap(int index, double shapeVolMod, Double densityMod, Double durabilityMod, Double creditCostMod,
			Double materialCostMod, Double mechanics, Double engineers) {
		typeStats.put(index, new TypeStats(index, shapeVolMod, densityMod, durabilityMod, creditCostMod,
				materialCostMod, mechanics, engineers));
	}

	private void addShapeVariantToMap(int index, double shapeVolMod, int referenceIndex) {
		TypeStats full = typeStats.get(referenceIndex);
		typeStats.put(index, new TypeStats(index, shapeVolMod, full.getDensityMod(), full.getDurabilityMod(),
				full.getCreditCostMod(), full.getMaterialCostMod(), full.getMechanics(), full.getEngineers()));
	}

	public Map<Integer, TypeStats> getAllStats() { return typeStats; }

	public TypeStats getStats(Integer typeIndex) throws Avuilder4jException {
		TypeStats stats = typeStats.get(typeIndex);
		if (stats != null)
			return stats;
		else
			throw new Avuilder4jException(AvErrors.NOT_STATS_REGISTERED_MATERIAL + typeIndex + ".");
	}
}
