package avuilder4j.stats.values;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.stats.dtos.MaterialStats;
import avuilder4j.structural.values.Mats;

public class MaterialStatsCompilation {
	protected Map<Integer, MaterialStats> materials = new LinkedHashMap<Integer, MaterialStats>();

	public MaterialStatsCompilation() {
		// @formatter:off
		
		//		 :index			:name		:dens	:dur	:credits 	:mats
		addToMap(Mats.IRON		,"Iron"		, 51, 	4.000, 	11.120, 	5);
		addToMap(Mats.TITANIUM	,"Titanium"	, 30, 	6.000, 	15.010, 	5);
		addToMap(Mats.NAONITE	,"Naonite"	, 33, 	9.000, 	20.260, 	5);
		addToMap(Mats.TRINIUM	,"Trinium"	, 21, 	13.500, 27.340, 	5);
		addToMap(Mats.XANION	,"Xanion"	, 27, 	20.250, 36.910, 	5);
		addToMap(Mats.OGONITE	,"Ogonite"	, 45, 	30.375, 49.830, 	5);
		addToMap(Mats.AVORION	,"Avorion"	, 36, 	45.563, 67.270,		5);
		
		// @formatter:on
		this.materials = Collections.unmodifiableMap(materials);
	}

	private void addToMap(int index, String name, double baseDensity, double baseDurability, double baseCreditCost,
			double baseMaterialCost) {
		materials.put(index, new MaterialStats(index, name, baseDensity, baseDurability, baseCreditCost,
				baseMaterialCost));
	}

	public Map<Integer, MaterialStats> getStatsMap() { return materials; }

	public MaterialStats getStats(Integer matIndex) throws Avuilder4jException {
		MaterialStats stats = materials.get(matIndex);
		if (stats != null)
			return stats;
		else
			throw new Avuilder4jException(AvErrors.NOT_STATS_REGISTERED_MATERIAL + matIndex + ".");
	}

}
