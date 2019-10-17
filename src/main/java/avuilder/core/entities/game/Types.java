package avuilder.core.entities.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Types {
	public static final int INTEGRITY_FIELD_GENERATOR = 0;
	
	protected Map<Integer, TypeBlock> typeBlocks;
	
	public Types() {
		Map<Integer, TypeBlock> m = new HashMap<Integer, TypeBlock>();
		m.put(INTEGRITY_FIELD_GENERATOR,	new TypeBlock(INTEGRITY_FIELD_GENERATOR,	1.0,	1.0,	1.0,	1.0,	1.0,	1.0));
		this.typeBlocks = Collections.unmodifiableMap(m);
	}
	
	public Map<Integer, TypeBlock> getAllMaterials(){
		return typeBlocks;
	}
	
	public TypeBlock getMaterial(Integer typeIndex){
		return typeBlocks.get(typeIndex);
	}
	
}
