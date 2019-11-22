package avuilder4j.dtos.map;

public class MaterialParams extends MaterialFields {

	public MaterialParams() {}

	public MaterialParams(MapIndex<Integer> index) {
		this.index = index;
	}

	public void setIndex(MapIndex<Integer> index) { this.index = index; }

	public void setName(String name) { this.name = name; }

	public void setDensity(Double density) { this.density = density; }

	public void setDurability(Double durability) { this.durability = durability; }

	public void setCreditCost(Double creditCost) { this.creditCost = creditCost; }

	public void setMaterialCost(Double materialCost) { this.materialCost = materialCost; }

}
