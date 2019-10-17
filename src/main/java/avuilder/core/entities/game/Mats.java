package avuilder.core.entities.game;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Mats {

	public static final int IRON = 0;
	public static final int TITANIUM = 1;
	public static final int NAONITE = 2;
	public static final int TRINIUM = 3;
	public static final int XANION = 4;
	public static final int OGONITE = 5;
	public static final int AVORION = 6;
	
	protected Map<Integer, Material> materials;
	
	public Mats() {
		Map<Integer, Material> m = new LinkedHashMap<Integer, Material>();
		m.put(IRON, 	new Material(IRON		,"Iron"		, 51, 4.000	, 11.120, 5));
		m.put(TITANIUM,	new Material(TITANIUM	,"Titanium"	, 30, 6.000	, 15.010, 5));
		m.put(NAONITE, 	new Material(NAONITE	,"Naonite"	, 33, 9.000	, 20.260, 5));
		m.put(TRINIUM, 	new Material(TRINIUM	,"Trinium"	, 21, 13.500, 27.340, 5));
		m.put(XANION, 	new Material(XANION		,"Xanion"	, 27, 20.250, 36.910, 5));
		m.put(OGONITE, 	new Material(OGONITE	,"Ogonite"	, 45, 30.375, 49.830, 5));
		m.put(AVORION, 	new Material(AVORION	,"Avorion"	, 36, 45.563, 67.270, 5));
		this.materials = Collections.unmodifiableMap(m);
	}
	
	public Map<Integer, Material> getAllMaterials(){
		return materials;
	}
	
	public Material getMaterial(Integer matIndex){
		return materials.get(matIndex);
	}
	
}
