package avuilder.core.entities.avorion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Types {
	public static final int INTEGRITY_FIELD_GENERATOR = 0;
	
	protected Map<Integer, TypeBlock> typeBlocks;
	
	public Types() {
		Map<Integer, TypeBlock> m = new HashMap<Integer, TypeBlock>();
		m.put(INTEGRITY_FIELD_GENERATOR,	new TypeBlock(INTEGRITY_FIELD_GENERATOR,	100.000,	100.000,	1000.000,	100.000,	1.000,	1.000));
		this.typeBlocks = Collections.unmodifiableMap(m);
	}
	
	public Map<Integer, TypeBlock> getAllMaterials(){
		return typeBlocks;
	}
	
	public TypeBlock getMaterial(Integer typeIndex){
		return typeBlocks.get(typeIndex);
	}
	
}
