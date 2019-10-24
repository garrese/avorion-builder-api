package avuilder4j.entities.game.base;

import avuilder4j.entities.game.Material;

public class MaterialBase extends Material {

	protected String name;

	public MaterialBase(int index) {
		super(index);
	}

	public MaterialBase(int index, String name) {
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
		return "MaterialBase [index=" + index + ", name=" + name + "]";
	}

}
