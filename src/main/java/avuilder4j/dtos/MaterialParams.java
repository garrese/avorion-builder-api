package avuilder4j.dtos;

import avuilder4j.dtos.base.MaterialFields;

class MaterialParams extends MaterialFields {

	public MaterialParams(int index) {
		this.index = index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public void setDurability(double durability) {
		this.durability = durability;
	}

	public void setMaterialCost(double materialCost) {
		this.materialCost = materialCost;
	}

}
