package avuilder4j.entities.game.base;

import avuilder4j.entities.game.TypeBlock;

public class TypeBlockBase extends TypeBlock {

	protected String name;

	public TypeBlockBase(int index) {
		super(index);
	}

	public TypeBlockBase(int index, String name) {
		super(index);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TypeBlockBase [index=" + index + ", name=" + name + "]";
	}

}
