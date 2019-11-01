package avuilder4j.values;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import avuilder4j.formulae.MaterialStats;

public class Mats {

	public static final int IRON = 0;
	public static final int TITANIUM = 1;
	public static final int NAONITE = 2;
	public static final int TRINIUM = 3;
	public static final int XANION = 4;
	public static final int OGONITE = 5;
	public static final int AVORION = 6;

	protected Map<Integer, MaterialStats> materials;

	public Mats() {
		Map<Integer, MaterialStats> m = new LinkedHashMap<Integer, MaterialStats>();
		// @formatter:off
		m.put(IRON, 	new MaterialStats(IRON		,"Iron"		, 51, 4.000	, 11.120, 5));
		m.put(TITANIUM,	new MaterialStats(TITANIUM	,"Titanium"	, 30, 6.000	, 15.010, 5));
		m.put(NAONITE, 	new MaterialStats(NAONITE	,"Naonite"	, 33, 9.000	, 20.260, 5));
		m.put(TRINIUM, 	new MaterialStats(TRINIUM	,"Trinium"	, 21, 13.500, 27.340, 5));
		m.put(XANION, 	new MaterialStats(XANION		,"Xanion"	, 27, 20.250, 36.910, 5));
		m.put(OGONITE, 	new MaterialStats(OGONITE	,"Ogonite"	, 45, 30.375, 49.830, 5));
		m.put(AVORION, 	new MaterialStats(AVORION	,"Avorion"	, 36, 45.563, 67.270, 5));
		// @formatter:on
		this.materials = Collections.unmodifiableMap(m);
	}

	public Map<Integer, MaterialStats> getAllMaterials() {
		return materials;
	}

	public MaterialStats getMaterial(Integer matIndex) {
		return materials.get(matIndex);
	}

}
